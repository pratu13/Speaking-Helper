package com.speechhelper.databasemanager;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SpeechID.class)
@Table(name="Report")
public class ReportEntity {
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  @Column(name="reportId")
	  private Long reportId;
	
	  @Id
	  @Column(name="speechId")
	  private Long speechId;
	  
	  @Id
	  @Column(name="userId")
	  private Long userId;
	  
	  @Column(name="wordFrequency")
	  private String wordFrequency;
	  
	  @Column(name = "fillerFrequency")
	  private String fillerFrequency;
	  
	  @Column(name = "fillerRatio")
	  private String fillerRatio;
	  
	  @Column(name = "speechRate")
	  private double speechRate;
	  
	  @Column(name = "feedback")
	  private String feedback;
	  
	  @Column(name = "spellingFixes")
	  private HashMap<String,String> spellingFixes;
	  
	  @Column(name = "score")
	  private int score;
	  
	  @Column(name = "sentiment")
	  private String sentiment;
	  
	  public ReportEntity() {}
	
	  @Override
	  public String toString() {
	    return String.format(
	        "User[reportId=%d, speechId=%d, userId=%d, wordFrequency=%s, fillerFrequency=%s, fillerRatio=%s, speechRate=%d, feedback=%s, spellingFixes=%s, score=%s]",
	        reportId, speechId, userId, wordFrequency, fillerFrequency, fillerRatio, speechRate, feedback, spellingFixes, score);
	  }
	  
	public String getWordFrequency() {
		return wordFrequency;
	}

	public void setWordFrequency(String wordFrequency) {
		this.wordFrequency = wordFrequency;
	}

	public String getFillerFrequency() {
		return fillerFrequency;
	}

	public void setFillerFrequency(String fillerFrequency) {
		this.fillerFrequency = fillerFrequency;
	}

	public String getFillerRatio() {
		return fillerRatio;
	}

	public void setFillerRatio(String fillerRatio) {
		this.fillerRatio = fillerRatio;
	}

	public double getSpeechRate() {
		return speechRate;
	}

	public void setSpeechRate(double speechRate) {
		this.speechRate = speechRate;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public HashMap<String, String> getSpellingFixes() {
		return spellingFixes;
	}

	public void setSpellingFixes(HashMap<String, String> spellingFixes) {
		this.spellingFixes = spellingFixes;
	}

	public Long getReportId() {
		return reportId;
	}
	
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	
	public Long getSpeechId() {
		return speechId;
	}
	
	public void setSpeechId(Long speechId) {
		this.speechId = speechId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	public String getSentiment() {
		return sentiment;
	}
}
