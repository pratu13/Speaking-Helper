//@Author Christian Dummer
package com.speechhelper.command;

public interface Command {
	
	public void execute();
	
	public void unexecute();
}
