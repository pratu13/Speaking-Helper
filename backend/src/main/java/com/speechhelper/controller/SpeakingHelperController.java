//@Author Christian Dummer
package com.speechhelper.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.speechhelper.command.Command;
import com.speechhelper.model.Model;
import com.speechhelper.nullobjects.NullSpeech;
import com.speechhelper.parsetext.ParseSpeechTextCommand;
import com.speechhelper.speechtotext.CreateSpeechCommand;
import com.speechhelper.speechtotext.GenerateReportCommand;
import com.speechhelper.speechtotext.ModifySpeechCommand;
import com.speechhelper.speechtotext.Speech;
import com.speechhelper.speechtotext.SpeechToTextCommand;
import com.speechhelper.speechtotext.SpeechToTextReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


//This is our interface between the frontend and backend.

@CrossOrigin(origins = "https://speechhelper.herokuapp.com/")
@RestController
public class SpeakingHelperController {
	@Autowired
	private Model model;
	
    @Autowired
    private HttpServletRequest request;
	
	
	public SpeakingHelperController() {
		
	}
	
	public SpeakingHelperController(Model m) {
		this.model = m;
	}
	

	//Example mapping for a request from the frontend
	@RequestMapping("/test")
	public String getTest() {
		//System.out.println("Hello World!");
		return "<h1>Hello World!</h1>";
	}
	
public String runPythonScriptHelper(ArrayList<String> PythonArguments) {
		
		String pythonString = "python.exe";
		
		for (int i = 0; i < PythonArguments.size(); i++)
			pythonString += " " + PythonArguments.get(i);
		
		String PythonOutput = "";
		String PythonErrors = "";
		String lines="";
		try {
			Process process = Runtime.getRuntime().exec(pythonString);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			while((lines=reader.readLine())!=null) {
				//PythonOutput += line +"\n\r";
				if(lines.equals("Sentiment analysis for live audio:")) {
					PythonOutput = reader.readLine();
					PythonOutput = PythonOutput.substring(15).trim();
				}
			}
			
			while((lines=errors.readLine())!=null) {
				PythonErrors += lines +"\n\r";
				// Uncomment Below Line to debug Python Script Issue.
				//System.out.println(" Error lines : "+PythonErrors);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PythonOutput;
	}

	public String runPythonScript_liveprediction(String filePath) {

	ArrayList<String> PythonArguments = new ArrayList<String>();
	// Argument 1 - Python Script path
	PythonArguments.add(
			System.getProperty("user.dir")+ "\\src\\main\\resources\\liveAudio.py");
	// Argument 2 - ML model Path
	PythonArguments.add(
			System.getProperty("user.dir")+ "\\src\\main\\resources\\AudioData\\Save_model\\Emotion_Voice_Detection_Model.h5");
	
	// Argument 3 - Input Audio
	PythonArguments.add(filePath);
	
	// Uncomment below statement if audioFilePath is passes as input
	//PythonArguments.add(audioFilePath);

	
	System.out.println("Running python: " + filePath);
	String output = runPythonScriptHelper(PythonArguments);
	System.out.println(output);
	return output;
	
}
	
	/*public String runPythonScript_liveprediction() {
		
		
		String modelPath = System.getProperty("user.dir")+ "\\src\\main\\resources\\AudioData\\Save_model\\Emotion_Voice_Detection_Model.h5";
		String AudioFilePath = 
				System.getProperty("user.dir")+ "\\src\\main\\resources\\fillerDemo.wav";
		ProcessBuilder builder = new ProcessBuilder("python.exe",
				System.getProperty("user.dir")+ "\\src\\main\\resources\\liveAudio.py",""+modelPath,""+AudioFilePath);
		//ProcessBuilder builder = new ProcessBuilder("python.exe",
		//		System.getProperty("user.dir")+ "\\src\\main\\resources\\liveAudio.py");
		String returnLine = "";
		String lines = "";
				
		try {
			Process process = builder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			lines = "";
			while((lines=reader.readLine())!=null) {
				System.out.println(lines);
				if(lines.equals("Sentiment analysis for live audio:")) {
					returnLine = reader.readLine();
					returnLine = returnLine.substring(15).trim();
				}
			}
			
			while((lines=errors.readLine())!=null) {
				System.out.println(" Error lines"+lines);
			}
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Return Value: " + returnLine);
		return returnLine;
		
	}
	
*/
	public void runPythonScript_recordaudio() {
		
		ProcessBuilder builder = new ProcessBuilder("python",
						System.getProperty("user.dir")+ "\\src\\main\\resources\\audioRecord.py");
				
		try {
			System.out.println("Start recording in 4 seconds");
			Process process = builder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String lines = null;
			while((lines=reader.readLine())!=null) {
				System.out.println(lines);
			}
			
			while((lines=errors.readLine())!=null) {
				System.out.println(" Error lines"+lines);
			}
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//This endpoint is currently configured to do the whole process of creating a speech and generating feedback
	@CrossOrigin(origins = "https://speechhelper.herokuapp.com/")
	@Transactional
	@RequestMapping(value="/createSpeech",  method=RequestMethod.POST)
	public Map<String, String> createSpeech(@RequestPart("files") MultipartFile[] files, @RequestParam Long userId) {
		//Need to take file as an input for text file of speech instead of url
		
		Speech testSpeech = new NullSpeech();
		HashMap<String, String> response = new HashMap<>();
		String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
		try {
			if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }
			String textFileOrgName = files[0].getOriginalFilename();
            String textFilePath = realPathtoUploads + textFileOrgName;
            File textFile = new File(textFilePath);
            files[0].transferTo(textFile);
            
			String audioFileOrgName = files[1].getOriginalFilename();
            String audioFilePath = realPathtoUploads + audioFileOrgName;
            File audioFile = new File(audioFilePath);
            files[1].transferTo(audioFile);
            
			testSpeech = new Speech.Builder().speechFile(audioFile)
											 .input(new String(Files.readAllBytes(textFile.toPath())))
											 .build();
		
		System.out.print(userId);
		Long speechId = model.addSpeech(testSpeech, userId);
		//uncomment
		SpeechToTextCommand speechToText = new SpeechToTextCommand(model, testSpeech);
		model.receiveCommand(speechToText);
		//System.out.println(testSpeech.toString());
		ParseSpeechTextCommand parseTextCommand = new ParseSpeechTextCommand(model, testSpeech);
		parseTextCommand.execute();
		String wordFrequency = parseTextCommand.getWordFrequencyCount().toString();
		String fillerFrequecy = parseTextCommand.getFillerFrequency().toString();
		String fillerRatio = parseTextCommand.getFillerRatio() + "";
		String sentiment =  runPythonScript_liveprediction(realPathtoUploads + "/" + files[1].getOriginalFilename());
		double speechRate = parseTextCommand.getSpeechRate();
		int score = parseTextCommand.getScore();
		
		//uncomment
		response.put("WordFrequency", wordFrequency);
		response.put("FillerFrequency", fillerFrequecy);
		response.put("FillerRatio", fillerRatio);
		response.put("SpeechRate", speechRate+"");
		response.put("Score", score+"");
		response.put("Sentiment", sentiment);
		System.out.println("speechId: "+speechId);
		
		//uncomment
		model.addReport(speechId, userId, fillerRatio, speechRate, score, sentiment);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

		//REST Controller converts to json for us, so returning a key value pair will work for our response
		return response;
	}
	
	@CrossOrigin(origins = "https://speechhelper.herokuapp.com/")
	@Transactional
	@RequestMapping(value="/createSpeechWelcomePage",  method=RequestMethod.POST)
	public Map<String, String> createSpeechWelcomepage(@RequestPart("files") MultipartFile[] files) {
		Speech testSpeech;
		HashMap<String, String> response = new HashMap<>();
		String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
		try {
			if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }
			String textFileOrgName = files[0].getOriginalFilename();
            String textFilePath = realPathtoUploads + textFileOrgName;
            File textFile = new File(textFilePath);
            files[0].transferTo(textFile);
            
			String audioFileOrgName = files[1].getOriginalFilename();
            String audioFilePath = realPathtoUploads + audioFileOrgName;
            File audioFile = new File(audioFilePath);
            files[1].transferTo(audioFile);
            
			testSpeech = new Speech.Builder().speechFile(audioFile)
											 .input(new String(Files.readAllBytes(textFile.toPath())))
											 .build();
			SpeechToTextCommand speechToText = new SpeechToTextCommand(model, testSpeech);
			model.receiveCommand(speechToText);
			ParseSpeechTextCommand report = new ParseSpeechTextCommand(model, testSpeech);
			report.execute();
			response = new HashMap<>();

			response.put("FillerRatio", report.getFillerRatio() + "");
			response.put("SpeechRate", report.getSpeechRate() + "");
			response.put("Sentiment", runPythonScript_liveprediction(realPathtoUploads + "/" + files[1].getOriginalFilename()));
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("We had an error: " + ex);
		}
		return response;
	}
	
	//Create a speech from the downloaded file Content
	public void createSpeech(@RequestParam String urlString) {
		Command createSpeechCommand = new CreateSpeechCommand(model, urlString);
		model.receiveCommand(createSpeechCommand);
	}
	
	
	
	//Create a speech from the audio file Content
	public void createSpeech(@RequestParam File file) {
		Command createSpeechCommand = new CreateSpeechCommand(model, file);
		model.receiveCommand(createSpeechCommand);
	}

	//Performs speech to text command
	@RequestMapping("/speechToText")
	public void speechToText(@RequestParam int speechId) {
		Command speechToTextCommand = new SpeechToTextCommand(model, model.getSpeechById(speechId).getSpeechFile());
		model.receiveCommand(speechToTextCommand);
	}
	
	@RequestMapping("/generateReport")
	//Generates feedback report and returns the feedback string
	public String generateReport(@RequestParam int speechId) {
		Command generateReportCommand = new GenerateReportCommand(model, model.getSpeechById(speechId));
		model.receiveCommand(generateReportCommand);
		SpeechToTextReport report = ((GenerateReportCommand)generateReportCommand).getReport();
		return report.getFeedback();
	}
	
	@RequestMapping("/modifySpeech")
	//Modifies the stored speech object
	public void modifySpeech(@RequestParam int speechId) {
		Command modifySpeechCommand = new ModifySpeechCommand(model, model.getSpeechById(speechId));
		model.receiveCommand(modifySpeechCommand);
	}
	
	/*
	@CrossOrigin(origins = "https://speechhelper.herokuapp.com/")
	@RequestMapping("/parseText")
	//Performs content analyzer command
	public Map<String, String> parseText(@RequestParam int speechId) {
		SpeechToTextCommand speechToText = new SpeechToTextCommand(model, model.getSpeechById(speechId));
		model.receiveCommand(speechToText);
		ParseSpeechTextCommand parseTextCommand = new ParseSpeechTextCommand(model, model.getSpeechById(speechId));
		model.receiveCommand(parseTextCommand);
		
		//Prints values to console
		System.out.println(parseTextCommand.getWordFrequencyCount().toString());
		System.out.println(parseTextCommand.getFillerFrequency().toString());
		System.out.printf(parseTextCommand.getFillerRatio());
		System.out.println(parseTextCommand.getSpeechRate());
		
		//REST Controller converts to json for us, so returning a key value pair will work for our response
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("WordFrequency", parseTextCommand.getWordFrequencyCount().toString());
		values.put("FillerFrequency", parseTextCommand.getFillerFrequency().toString());
		values.put("FillerRatio", parseTextCommand.getFillerRatio());
		values.put("SpeechRate", parseTextCommand.getSpeechRate() + "");
		return values;
	}*/
}
