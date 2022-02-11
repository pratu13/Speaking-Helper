//@Author Christian Dummer
package com.speechhelper.model;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.speechhelper.command.Command;
import com.speechhelper.command.CommandInvoker;
import com.speechhelper.controller.ReportDatabaseController;
import com.speechhelper.controller.SpeechDatabaseController;
import com.speechhelper.controller.UserDatabaseController;
import com.speechhelper.databasemanager.SpeechEntity;
import com.speechhelper.databasemanager.UserEntity;
import com.speechhelper.databasemanager.UserRepository;
import com.speechhelper.main.Main;
import com.speechhelper.nullobjects.NullSpeech;
import com.speechhelper.nullobjects.NullSpeechToTextReport;
import com.speechhelper.speechtotext.Speech;
import com.speechhelper.speechtotext.SpeechToTextReport;

//Basic model with an arraylist of speeches. See speechtotext.Speech for all that class contains.
@Repository
public class Model {
	
	private ArrayList<Speech> speeches;
	private CommandInvoker commandInvoker;
	private int currentId = 0;
	
	@Autowired
	private UserDatabaseController userDatabaseController;
	
	@Autowired
	private SpeechDatabaseController speechDatabaseController;
	
	@Autowired
	private ReportDatabaseController reportDatabaseController;
	
	public Model() {
		speeches = new ArrayList<Speech>();
		this.commandInvoker = new CommandInvoker();
	}
	
	public Long addUser(UserEntity user) {
		String username = user.getUsername();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String password = user.getPassword();
		String email = user.getEmail();
		userDatabaseController.addNewUser(firstName, lastName, username, password, email);
		return user.getUserId();
	}
	
	public UserEntity getUserByUserId(Long UserId) {
		return userDatabaseController.findByUserId(UserId);
	}
	
	public UserEntity getUserByUserName(String username) {
		return userDatabaseController.findByUsername(username);
	}
	
	//TODO use a database for this 
	//Add, remove, and get list of speech transcriptions
	public ArrayList<Speech> getSpeeches(){
		return this.speeches;
	}
	
	//Getting a list of speech from the 
	public ArrayList<SpeechEntity> getSpeechesByUserId(Long userId){
		return (ArrayList<SpeechEntity>) speechDatabaseController.findByUserId(userId);
	}
	
	public Long addSpeech(Speech newSpeech, Long userId) {
		return speechDatabaseController.addNewSpeech(userId, newSpeech);
	}
	
	@Async
	public void addReport(Long speechId, Long userId, String fillerRatio, double speechRate, int score, String sentiment){
		reportDatabaseController.addNewReport(speechId, userId, fillerRatio, speechRate, score, sentiment);
	}
	
	public Speech getSpeechById(int id) {
		for(Speech s: speeches) {
			if(s.getId() == id) {
				return s;
			}
		}
		return new NullSpeech();
	};
	
	public SpeechEntity getSpeechById(Long speechId) {
		return speechDatabaseController.findBySpeechId(speechId);
	}
	
	public void removeSpeech(Speech s) {
		speeches.remove(s);
	}
	
	public void modifySpeech(Speech s) {
		//TODO implement Speech object ID and have this find object by id rather than by reference.
		speeches.set(speeches.indexOf(s), s);
	}
	
	public Speech getSpeech(Speech s) {
		//Returns a copy of a given speech
		return speeches.get(speeches.indexOf(s));
	}
	
	//Use commandInvoker
	public void receiveCommand(Command c) {
		commandInvoker.receiveCommand(c);
	}

}
