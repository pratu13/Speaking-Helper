package com.speechhelper.speechtotext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

import com.speechhelper.command.Command;
import com.speechhelper.model.Model;
import com.speechhelper.nullobjects.NullSpeechToTextReport;
import com.speechhelper.utilities.FileDownloadUtility;

//Class to create a speech and store it in the model
public class CreateSpeechCommand implements Command {

	private Model model;
	private File textFile = new File("");
	private File speechFile = new File("");
	private String urlString = "";
	
	public CreateSpeechCommand(Model model, String urlString) {
		this.model = model;
		this.urlString = urlString;
	}
	
	public CreateSpeechCommand(Model model,  File speechFile) {
		this.model = model;
		this.speechFile = speechFile;
	}
	
	public CreateSpeechCommand(Model model, File textFile, File speechFile) {
		this.model = model;
		this.textFile = textFile;
		this.speechFile = speechFile;
	}
	
	public void execute() {
		Speech newSpeech;
		//if (urlString.isEmpty()) {
		//	newSpeech = new Speech(speechFile, new NullSpeechToTextReport());
		//	model.addSpeech(newSpeech);
		//} else {
			try {
			newSpeech = new Speech.Builder().speechFile(speechFile).build();
			//TODO pass user id
			//model.addSpeech(newSpeech, 0);
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
		//}
	}

	public void unexecute() {
		// TODO Auto-generated method stub
		
	}
	
	private String getTextSpeechFrom(String urlString) {
		String speechText = "";
		try {
			speechText = FileDownloadUtility.getFileContents(new URL(urlString), "output.txt");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return speechText;
	}


}
