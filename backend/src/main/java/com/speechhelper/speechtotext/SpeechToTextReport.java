//@Author Christian Dummer
package com.speechhelper.speechtotext;

import java.util.HashMap;

//Class to store feedback on a particular speech recording.
public class SpeechToTextReport {
	protected String wordFrequency = "";
	protected String fillerFrequency = "";
	protected int fillerRatio = 0;
	protected double speechRate = 0;
	protected String feedback = "";
	protected int score;

	protected HashMap<String, String> spellingFixes = new HashMap();
	
	public SpeechToTextReport() {
		
	}
	
	protected SpeechToTextReport(Builder sb) {
		this.wordFrequency = sb.wordFrequency;
		this.fillerFrequency = sb.fillerFrequency;
		this.fillerRatio = sb.fillerRatio;
		this.speechRate = sb.speechRate;
		this.feedback = sb.feedback;
	}
	
	//Getter methods
	public String getWordFrequency() {
		return this.wordFrequency;
	}
	
	public String getFillerFrequency() {
		return this.fillerFrequency;
	}
	
	public int getFillerRatio() {
		return this.fillerRatio;
	}
	
	public double getSpeechRate() {
		return this.speechRate;
	}
	
	public String getFeedback() {
		return this.feedback;
	}
	
	public HashMap<String,String> getSpellingFixes(){
		return this.spellingFixes;
	}
	
	//Setter methods
	public void setWordFrequency(String s) {
		this.wordFrequency = s;
	}
	
	public void setFillerFrequency(String s) {
		this.fillerFrequency = s;
	}
	
	public void setFillerRatio(int s) {
		this.fillerRatio = s;
	}
	
	public void setSpeechRate(double d) {
		this.speechRate = d;
	}
	
	public void setFeedback(String s) {
		this.feedback = s;
	}
	
	public void setSpellingFixes(HashMap<String,String> fixes) {
		this.spellingFixes = fixes;
	}
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	
	//Implementation of builder pattern
	public static class Builder{
		protected String wordFrequency = "";
		protected String fillerFrequency = "";
		protected int fillerRatio = 0;
		protected double speechRate = 0;
		protected String feedback = "";
		protected int score = 0;
		protected HashMap<String, String> spellingFixes = new HashMap();
		
		
		public SpeechToTextReport build() {
			return new SpeechToTextReport(this);
		}
		
		public Builder wordFrequency(String s) {
			this.wordFrequency = s;
			return this;
		}
		
		public Builder fillerFrequency(String s) {
			this.fillerFrequency = s;
			return this;
		}
		
		public Builder fillerRatio(int s) {
			this.fillerRatio = s;
			return this;
		}
		
		public Builder speechRate(double d) {
			this.speechRate = d;
			return this;
		}
		 
		public Builder feedback(String s) {
			this.feedback = s;
			return this;
		}
		
		public Builder spellingFixes(HashMap<String,String> h) {
			this.spellingFixes = h;
			return this;
		}
		
		public Builder score(int score) {
			this.score = score;
			return this;
		}
	
	}
}
