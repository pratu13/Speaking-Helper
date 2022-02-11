package com.speechhelper.databasemanager;

import java.io.Serializable;

public class ReportID implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long reportId;
    
    public Long getReportId() {
    	return reportId;
    }
    
    public void setReportId(Long id) {
    	this.reportId = id;
    }
}
