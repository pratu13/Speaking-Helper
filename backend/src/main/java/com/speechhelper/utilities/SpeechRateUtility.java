package com.speechhelper.utilities;

public class SpeechRateUtility {
	
	private int speechRate;
	public static SpeechRateUtility sharedInstance = getInstance();
	
	public SpeechRateUtility(){
		speechRate = 0;
	}
	
	private static SpeechRateUtility getInstance() {
		if (sharedInstance == null) {
			sharedInstance = new SpeechRateUtility();
		}
		return sharedInstance;
	}
	
	public int getSpeechRate(Integer totalWords, double speechTime) {
		speechRate = (int) (totalWords/speechTime);
		return speechRate;
	}
	
}