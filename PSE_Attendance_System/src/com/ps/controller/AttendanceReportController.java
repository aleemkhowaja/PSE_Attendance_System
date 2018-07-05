package com.ps.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.transform.SynchronizedASTTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ps.common.DateUtil;
import com.ps.model.AttendanceDetailModel;
import com.ps.model.EmployeeModel;
import com.ps.sevices.AttendanceReportService;

@Controller
public class AttendanceReportController {
	
	@Autowired
	private AttendanceReportService attendanceReportService;
	
	public AttendanceReportService getAttendanceReportService() {
		return attendanceReportService;
	}

	public void setAttendanceReportService(
			AttendanceReportService attendanceReportService) {
		this.attendanceReportService = attendanceReportService;
	}


	@RequestMapping(value="/attendance/aggregrateMonthlyAttendanceReportPdf.htm")
	@ResponseBody
	public byte[] returnAllAttendanceDetailForGrid(@ModelAttribute("attendanceData")AttendanceDetailModel attendanceDetailModel , HttpServletRequest request, HttpServletResponse response) {
		
		String serverPath = request.getServletContext().getRealPath("");
		HashMap<String,Object> jasperParameter = new HashMap<String,Object>();
		
		String monthName  = DateUtil.getMonthName(attendanceDetailModel.getMonth());
		jasperParameter.put("totalDays", DateUtil.getTotalDaysByMonthAndYear(attendanceDetailModel.getYear(), attendanceDetailModel.getMonth()));
		jasperParameter.put("imagePath", serverPath+ "/resources/theme/images/path1.jpg");
		jasperParameter.put("holidays", DateUtil.getHolyDaysByMonthAndYear(attendanceDetailModel.getYear(), attendanceDetailModel.getMonth()));
		jasperParameter.put("monthName",monthName+" - "+attendanceDetailModel.getYear());
		
		if(attendanceDetailModel.getMonth()> 0 && attendanceDetailModel.getMonth() < 10)
		{
			jasperParameter.put("month", attendanceDetailModel.getYear()+"-0"+attendanceDetailModel.getMonth());
		}
		else
		{
			jasperParameter.put("month", attendanceDetailModel.getYear()+"-"+attendanceDetailModel.getMonth());
		}
		return attendanceReportService.getPdfReport(jasperParameter, "path-showattendance.jrxml", request, response);
	}
	//end get all attendance detail for search screen
	
			
}
