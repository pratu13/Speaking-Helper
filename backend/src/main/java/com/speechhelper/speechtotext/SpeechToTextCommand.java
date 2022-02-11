//@Author Christian Dummer
package com.speechhelper.speechtotext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.speechhelper.command.Command;
import com.speechhelper.main.Main;
import com.speechhelper.model.Model;
import com.speechhelper.nullobjects.NullSpeechToTextReport;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

//TODO builder pattern for 
public class SpeechToTextCommand implements Command{  
	private Model model;
	private Configuration config;
	private File speech;
	private String input = "";
	private String text = "";
	private Speech speechContainer;
	
	public SpeechToTextCommand(Model m) {
		this.model = m;
        setConfig();
	}
	
	public SpeechToTextCommand(Model m, File speech) {
		this.speech = speech;
		this.model = m;
		setConfig();
	}
	
	public SpeechToTextCommand(Model m, URI filepath) {
		this.speech = new File(filepath);
		this.model = m;
		setConfig();
	}
	
	public SpeechToTextCommand(Model m, File speech, String input) {
		this.speech = speech;
		this.model = m;
		this.input = input;
		setConfig();
	}
	
	public SpeechToTextCommand(Model m, Speech speech) {
		this.model = m;
		this.speechContainer = speech;
		this.speech = speechContainer.getSpeechFile();
		this.input = "text";
		setConfig();
	}
	
	public void setConfig() {
		//Default configuration for speech to text, using Sphinx models and dictionaries.
		config = new Configuration();
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
	    config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		if(!input.equals("")) {
			//If input, customize the dictionary
			customizeDictionary();
		}
		else {
			//Otherwise, use the default dictionary
			config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		}
	}
	
	public void customizeDictionary() {
	//TODO clean this method up a bit.
		ArrayList<String> customDict = new ArrayList<String>();
		File fullDictionary = new File("");
		File fillerWords = new File("");
		try {
			fullDictionary = new File(Main.class.getClassLoader().getResource("speechHelper.dict").toURI());
			fillerWords = new File(Main.class.getClassLoader().getResource("fillerWords.txt").toURI());
		}
		catch(Exception ex) {
			
		}
		
		String tempInput = input;
		
		//Reads through fillerWords file and adds those words to the input
		try(Scanner fileReader = new Scanner(fillerWords)){
			while(fileReader.hasNext()) {
				String line = fileReader.nextLine();
				List<String> fillers = Arrays.asList(line.split(","));
				for(String f: fillers) {
					tempInput = tempInput + " " + f;
				}
			}
		}
		catch(FileNotFoundException ex) {
			
		}
		
		//Gets the input text and puts all of its words into a list
		List<String> inputWords = Arrays.asList(tempInput.split(" "));
		

	
		
		
		///Reads through the full dictionary and adds the line to the custom dict if the word is in the input
		try(Scanner fileReader = new Scanner(fullDictionary)){
			while(fileReader.hasNext()) {
				String nextLine = fileReader.nextLine();
				String[] words = nextLine.split(" ");
				if(inputWords.contains(words[0])) {
					customDict.add(nextLine);
				}
			}
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex);
		}
		
		

		//Finally, write the custom dictionary file and set the configs dictionary to that file
		try(PrintWriter writer = new PrintWriter("custom.dict")){
			for(String word: customDict) {
				writer.println(word);
			System.out.println(word);
			}
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex);
		}
		
		config.setDictionaryPath("custom.dict");
		
	}
	
	//Getters and Setters
	public File getSpeech() {
		return this.speech;
	}
	
	public Speech getSpeechObject() {
		return this.speechContainer;
	}
	
	public void setSpeech(File newSpeech) {
		this.speech = newSpeech;
	}
	
	public String getText() {
		return this.text;
	}
	
	//Command interface methods
	public void execute() {
		/* TODO, implement below code in live speech recognizer
		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
		// Start recognition process pruning previously cached data.
		recognizer.startRecognition(true);
		SpeechResult result = recognizer.getResult();
		// Pause recognition process. It can be resumed then with startRecognition(false).
		recognizer.stopRecognition();
		*/
		
		
		
		//Code for speech to text from audio file
		try {
			StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(config);
			InputStream stream = new FileInputStream(speechContainer.getSpeechFile());
			
			//Runs recognition and sets the best result as the given text output
			recognizer.startRecognition(stream);
	        SpeechResult result;
	        while ((result = recognizer.getResult()) != null) {
	            for (String s : result.getNbest(1)) {
	            	//System.out.println(s);
	                text += s;
	            }
	        }
	        //Removes String tags from output
	        text = text.replaceAll("<s>", "");
	        text = text.replaceAll("</s>", "");
	        
	        //Adds it to model
	        speechContainer.setSpeechToText(text);
	        
	        //model.addSpeech(speechContainer);
	        
	        //Println for debugging
	        //System.out.println(text);
	        
	        recognizer.stopRecognition();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void unexecute() {
		model.removeSpeech(speechContainer);
	}

}