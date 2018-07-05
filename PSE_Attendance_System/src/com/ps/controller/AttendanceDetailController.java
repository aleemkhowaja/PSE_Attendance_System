package com.ps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.common.JTableList;
import com.ps.model.AttendanceDetailModel;
import com.ps.model.HolidayModel;
import com.ps.sevices.AttendanceDetailService;

@RestController
public class AttendanceDetailController {
	
	@Autowired
	private AttendanceDetailService attendanceDetailService;
	
	
	public AttendanceDetailService getAttendanceDetailService() {
		return attendanceDetailService;
	}

	public void setAttendanceDetailService(AttendanceDetailService attendanceDetailService) {
		this.attendanceDetailService = attendanceDetailService;
	}

	//start get all attendance detail for search screen
	@RequestMapping(value="/attendance/attendanceList.htm", produces = "application/json")
	@ResponseBody
	public JTableList<AttendanceDetailModel> returnAllAttendanceDetailForGrid(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return attendanceDetailService.returnAllAttendaceDetailForGrid(request);
	}
	//end get all attendance detail for search screen
	
	//start get Attendance
	@RequestMapping(value="/attendance/getAttendanceById", method = RequestMethod.POST)
	@ResponseBody
	public AttendanceDetailModel getAttendanceDetailById(@RequestParam("attendanceId") Integer attendanceId, Model model) {
		AttendanceDetailModel attendanceDetailModel = attendanceDetailService.getAttendanceDetailById(attendanceId);
		return attendanceDetailModel;
	}
	//end add holiday
		
	
	//start add Attendance Detail
	@RequestMapping(value="/attendance/save.htm", method = RequestMethod.POST)
	@ResponseBody
	public String saveAttendance(HttpServletRequest request) {
		String result   = attendanceDetailService.addAttendanceDetail(request, "Add");
		return result;
	}
	//end add Attendance Detail
	
	//start delete Attendance Detail
	@RequestMapping(value="/attendance/delete.htm", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAttendance(@RequestParam("attendanceDetailId")Integer attendanceDetailId , HttpServletRequest request) {
		attendanceDetailService.deleteAttendance(attendanceDetailId);
		return "Success";
	}
	//end delete Attendance Detail
}
