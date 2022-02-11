//@Author Christian Dummer
package com.speechhelper.speechtotext;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.speechhelper.constants.Constants;
import com.speechhelper.nullobjects.NullSpeechToTextReport;


//This is a container class for speech objects, the text transcription, and the feedback.
//This will be the main thing we store in our database.
public class Speech {
	protected File speechFile;
	protected SpeechToTextReport report;
	protected String speechToText;
	protected String input;
	protected int speechId;
	
	protected Speech() {
		
	}
	protected Speech(Builder sb) {
		this.speechFile = sb.speechFile;
		this.report = sb.report;
		this.speechToText = sb.speechToText;
		this.input = sb.input;
		this.speechId = sb.speechId;
	}
	
	public String getText() {
		return this.speechToText;
	}
	
	public File getSpeechFile() {
		return this.speechFile;
	}
	
	public SpeechToTextReport getReport() {
		return this.report;
	}
	
	public void setSpeechToTextReport(SpeechToTextReport s) {
		this.report = s;
	}
	
	public int getId() {
		return this.speechId;
	}
	
	public void setId(int id) {
		this.speechId = id;
	}
	
	public String getInput() {
		return this.input;
	}

	public void setSpeechToText(String s) {
		this.speechToText = s;
	}
	
	@Override
	public String toString() {
		return "Speech File: " + speechFile.toString() + "\n Report: TBD" + "\n Text: " + speechToText + "\n Text Input: " + input;
	}
	
	public double getSpeechlength() {
		AudioInputStream audioInputStream;
		long frames = 0;
		double durationInSeconds = 0;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(speechFile);
			AudioFormat format = audioInputStream.getFormat();
			frames = audioInputStream.getFrameLength();
			durationInSeconds = (frames+0.0) / format.getFrameRate(); 
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return durationInSeconds/60;
	}
	
	
	//Implementation of builder pattern
	public static class Builder{
		protected File speechFile = new File("");
		protected SpeechToTextReport report = new NullSpeechToTextReport();
		protected String speechToText = "";
		protected String input = "";
		protected int speechId = Constants.NULL_SPEECH_ID;
		
		
		public Speech build() {
			return new Speech(this);
		}
		
		public Builder speechFile(File s) {
			System.out.println("inside the speech class"+s);
			this.speechFile = s;
			return this;
		}
		 
		public Builder report(SpeechToTextReport r) {
			this.report = r;
			return this;
		}
		
		public Builder speechToText(String s) {
			this.speechToText = s;
			return this;
		}
		
		public Builder input(String s) {
			this.input = s;
			return this;
		}
		
		public Builder id(int i) {
			this.speechId = i;
			return this;
		}
	}
	
}
