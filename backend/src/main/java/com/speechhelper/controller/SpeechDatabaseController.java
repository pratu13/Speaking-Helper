package com.speechhelper.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.speechhelper.databasemanager.SpeechEntity;
import com.speechhelper.databasemanager.SpeechRepository;
import com.speechhelper.speechtotext.Speech;

import javassist.bytecode.Descriptor.Iterator;

@RestController
public class SpeechDatabaseController {
	@Autowired
	private SpeechRepository speechRepository;
	
	@Transactional
	@PostMapping(path="/add_speech") // Map ONLY POST Requests
	public Long addNewSpeech (@RequestParam Long userId, @RequestParam Speech speech) {
		SpeechEntity n = new SpeechEntity();
		n.setUserId(userId);
		java.util.Date today = new java.util.Date();
		n.setDateCreated(new Date(today.getYear(), today.getMonth(), today.getDate()));
//		n.setTranscribedSpeechText(speech.getText());
//		n.setConvertedSpeechText(speech.getInput());
		n = speechRepository.save(n);
		System.out.println("after saving speech"+n.getSpeechId());
		return n.getSpeechId();
	}
	
	@Transactional
	@GetMapping(path="/all/speech")
	public Iterable<SpeechEntity> getAllSpeech() {
	    // This returns a JSON or XML with the users
	    return speechRepository.findAll();
	}
	
	@Transactional
	@GetMapping(path = "/speech_user_id")
	public Map<String, String> findByUserId(@RequestParam Long userId){
		HashMap<String, String> map = new HashMap<>();
		List<SpeechEntity> speeches = speechRepository.findByUserId(userId);
		
		java.util.Iterator<SpeechEntity> it  = speeches.iterator();
		while (it.hasNext()) {
			SpeechEntity e = it.next();
			map.put(e.getSpeechId()+"", e.getDateCreated().toString()+"");
		}
		
		return map;
	}
	
	
	@Transactional
	@GetMapping(path = "/speech_id")
	public SpeechEntity findBySpeechId(@RequestParam Long speechId){
		return speechRepository.findBySpeechId(speechId);
	}
	
	
}
