package com.speechhelper.databasemanager;

import java.io.Serializable;

public class SpeechID implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long speechId;
    private Long userId;
    
    public Long getSpeechId() {
    	return speechId;
    }
    
    public Long getUserId() {
    	return userId;
    }
    
    public void setSpeechId(Long id) {
    	this.speechId = id;
    }
    
    public void setUserId(Long id) {
    	this.userId = id;
    }

}
