package com.speechhelper.nullobjects;

import com.speechhelper.speechtotext.TranscribedSpeechText;

public class NullTranscribedSpeechText extends TranscribedSpeechText{

	//Sets fields of TranscribedSpeechText to be null to avoid null pointer exceptions
	public NullTranscribedSpeechText() {
		this.originalText = "";
		this.parsedText = "";
	}
}
