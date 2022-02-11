package com.speechhelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speechhelper.databasemanager.ReportEntity;
import com.speechhelper.databasemanager.ReportRepository;
import com.speechhelper.databasemanager.SpeechEntity;

@RestController
public class ReportDatabaseController {
	@Autowired
	private ReportRepository reportRepository;
	
	@Transactional
	@PostMapping(path="/add_report") // Map ONLY POST Requests
	public String addNewReport (@RequestParam Long speechId, @RequestParam Long userId, @RequestParam String fillerRatio, @RequestParam double speechRate, @RequestParam int score, @RequestParam String sentiment) {
		ReportEntity n = new ReportEntity();
		n.setSpeechId(speechId);
		n.setUserId(userId);
		n.setFillerRatio(fillerRatio);
		n.setSpeechRate(speechRate);
		n.setScore(score);
		n.setSentiment(sentiment);
		reportRepository.save(n);
		return "Saved";
	}
	
	@Transactional
	@GetMapping(path="/all/report")
	public Iterable<ReportEntity> getAllReports() {
	    // This returns a JSON or XML with the users
	    return reportRepository.findAll();
	}
	
	@Transactional
	@GetMapping(path="/report_id}")
	public ReportEntity findByReportId(@RequestParam Long reportId) {
		return reportRepository.findById(reportId).get();
	}
	
	@Transactional
	@GetMapping(path = "/report_speech_id")
	public ReportEntity findBySpeechId(@RequestParam Long speechId){
		return reportRepository.findBySpeechId(speechId);
	}
}
