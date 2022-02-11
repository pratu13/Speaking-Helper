//@Author Christian Dummer
package com.speechhelper.nullobjects;

import com.speechhelper.speechtotext.SpeechToTextReport;

public class NullSpeechToTextReport extends SpeechToTextReport{

	public NullSpeechToTextReport() {
		this.wordFrequency = "";
		this.fillerFrequency = "";
		this.fillerRatio = 0;
		this.speechRate = 0;
		this.feedback = "";
	}
}
