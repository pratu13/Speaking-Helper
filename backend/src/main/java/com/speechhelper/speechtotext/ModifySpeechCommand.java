//@Author Christian Dummer
package com.speechhelper.speechtotext;

import com.speechhelper.command.Command;
import com.speechhelper.model.Model;
import com.speechhelper.nullobjects.NullSpeech;

//TODO more intuitive names, having copies
public class ModifySpeechCommand implements Command {
	private Model model;
	private Speech originalSpeech;
	private Speech swappedSpeech;
	public ModifySpeechCommand(Model m, Speech s) {
		this.model = m;
		this.originalSpeech = s;
		this.swappedSpeech = new NullSpeech();
	}
	public void execute() {
		// TODO Auto-generated method stub
		swappedSpeech = model.getSpeech(originalSpeech);
		
		model.modifySpeech(this.originalSpeech);
	}

	public void unexecute() {
		if(!(swappedSpeech instanceof NullSpeech)) {
			originalSpeech = swappedSpeech;
			swappedSpeech = model.getSpeech(swappedSpeech);
			model.modifySpeech(originalSpeech);
		}
	}

}
