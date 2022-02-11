//@Author Christian Dummer
package com.speechhelper.command;

import java.util.EmptyStackException;
import java.util.Stack;

public class CommandInvoker 
{
	private Stack<Command> commandStack;
	private Stack<Command> undoneCommands;
	
	public CommandInvoker()
	{
		commandStack = new Stack<Command>();
		undoneCommands = new Stack<Command>();
	}
	
	//Receives and executes command. Commands have reference to the model and will call the appropriate method to engage with data there.
	public void receiveCommand(Command c)
	{
		c.execute();
		commandStack.push(c);
	}
	
	
	//Undoes a command.
	public void undo()
	{
		try {
			Command toUndo = commandStack.pop();
			toUndo.unexecute();
			undoneCommands.push(toUndo);
		} 
		catch(EmptyStackException e){
			e.printStackTrace();
		}
	}

	public void redo()
	{
		//TODO
	}
	
}
