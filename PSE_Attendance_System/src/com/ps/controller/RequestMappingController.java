package com.ps.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ps.model.AttendanceDetailModel;
import com.ps.model.EmployeeModel;
import com.ps.model.HolidayModel;
import com.ps.sevices.DepartmentService;
import com.ps.sevices.EmployeeService;
import com.ps.sevices.LocationService;

@Controller
public class RequestMappingController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/attendance/insert-attendance.htm", method=RequestMethod.GET)
	public String returnInserAttendanceDetailPage(Model model) {
		AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
		//attendanceDetailModel.setAttendanceDate(new Timestamp(System.currentTimeMillis()));
		model.addAttribute("attendanceData", attendanceDetailModel);
		model.addAttribute("employeeList", employeeService.returnAllEmployees("employee"));
		return "search-attendance-detail";
	}
	
	@RequestMapping(value="/attendance/search-employees.htm", method=RequestMethod.GET)
	public String getSearchEmployee(Model model) {
		model.addAttribute("employeeData", new EmployeeModel());
		AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
		attendanceDetailModel.setAttendanceDate(new Timestamp(System.currentTimeMillis()));
		model.addAttribute("attendanceData", attendanceDetailModel);
		model.addAttribute("departmentList", departmentService.returnAllDepartments());
		model.addAttribute("locationList", locationService.returnAllLocations());
		return "search-employee";
	}
	
	@RequestMapping(value="/home.htm", method=RequestMethod.GET)
	public String returnHomePage(Model model) {
		//AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
		//attendanceDetailModel.setAttendanceDate(new Timestamp(System.currentTimeMillis()));
		//model.addAttribute("attendanceData", attendanceDetailModel);
		return "home";
	}
	
	@RequestMapping(value="/attendance/aggregrate-monthly-attendance-report.htm", method=RequestMethod.GET)
	public String returnAggregrateAttendanceReprotPage(Model model) {
		AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
		model.addAttribute("attendanceData", attendanceDetailModel);
		return "aggregrate-attendance-report";
	}
	
	@RequestMapping(value="/holidays/search-holiday.htm", method=RequestMethod.GET)
	public String returnSearchHolidayPage(Model model) {
		HolidayModel holidayModel = new HolidayModel();
		model.addAttribute("holidayData", holidayModel);
		return "search-holiday";
	}
	
	@RequestMapping(value="/attendance/register-user.htm", method=RequestMethod.GET)
	public String returnRegisterUserPage(Model model) {
		model.addAttribute("employeeData", new EmployeeModel());
		model.addAttribute("departmentList", departmentService.returnAllDepartments());
		model.addAttribute("locationList", locationService.returnAllLocations());
		return "register-user";
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
