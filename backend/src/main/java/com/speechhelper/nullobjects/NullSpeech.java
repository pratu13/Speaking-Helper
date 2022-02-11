//@Author Christian Dummer
package com.speechhelper.nullobjects;

import java.io.File;

import com.speechhelper.constants.Constants;
import com.speechhelper.speechtotext.Speech;

public class NullSpeech extends Speech{

	//Initializes all fields of speech to be empty to avoid null pointer exceptions; 
	public NullSpeech() {
		this.speechFile = new File("");
		this.report = new NullSpeechToTextReport();
		this.speechToText = "";
		this.speechId = Constants.NULL_SPEECH_ID;
	}
}
