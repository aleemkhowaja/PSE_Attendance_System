package com.ps.sevices;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AttendanceReportService {
	
	byte[] getPdfReport (HashMap<String, Object> jasperParameter,String reportName,HttpServletRequest request,HttpServletResponse response);

}
