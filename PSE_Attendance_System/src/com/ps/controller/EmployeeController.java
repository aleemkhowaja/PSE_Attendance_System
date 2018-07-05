package com.ps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.common.JTableList;
import com.ps.model.DepartmentModel;
import com.ps.model.EmployeeModel;
import com.ps.sevices.DepartmentService;
import com.ps.sevices.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LocationController locationController;
	
	@Autowired
	private DepartmentService departmentService;
	
	private List<DepartmentModel> departmentList = new ArrayList<DepartmentModel>();
	
	
	@RequestMapping(value="/employeeDetailPage", method=RequestMethod.GET)
	public String getEmployeeDetail(Model model) {
		model.addAttribute("employeeData", new EmployeeModel());
		return "employee-detail";
	}
	
	//start get all employees for search screen
	@RequestMapping(value="/employee/returnAllEmployeesForGrid", produces = "application/json")
	@ResponseBody
	public JTableList<EmployeeModel> returnAllEmployeesForGrid(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return employeeService.returnAllEmployeesForGrid(request);
	}
	//end get all employees for search screen
	
	//start add employees
	@RequestMapping(value="/getEmployee/{employeeId}", method = RequestMethod.GET)
	public String getEmployeeById(@PathVariable("employeeId") Integer employeeId, Model model) {
		EmployeeModel employeeModel = employeeService.getEmployeeById(employeeId);
		com.ps.model.Model.MODE = "Update";
		model.addAttribute("employeeData", employeeModel);
		return "search-employee";
	}
	//end add employee
	
	//start add employees
	@RequestMapping(value="/employee/getEmployeeById", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeModel getEmployeeById1(@RequestParam("employeeId") Integer employeeId, Model model) {
		EmployeeModel employeeModel = employeeService.getEmployeeById(employeeId);
		return employeeModel;
		/*com.ps.model.Model.MODE = "Update";
		model.addAttribute("employeeData", employeeModel);
		return "search-employee";*/
	}
		
		
		
	//start add employees
	@RequestMapping(value="/employee/save.htm", method = RequestMethod.POST)
	@ResponseBody
	public String addEmployee(@RequestBody EmployeeModel employeeModel, HttpServletRequest request) {
		String result = employeeService.addEmployee(employeeModel);
		return result;
	}
	//end add employee
	
	//start add employees
	@RequestMapping(value="/employee/registerEmployee.htm", method = RequestMethod.POST)
	@ResponseBody
	public String registerEmployee(@RequestBody EmployeeModel employeeModel, HttpServletRequest request) {
		String result =  employeeService.addEmployee(employeeModel);
		return result;
	}
	//end add employee
	
	
	
/*	//start update employees
	@RequestMapping(value="/employee/update.htm", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public JTableList updateEmployee(@ModelAttribute("employeeData")EmployeeModel employeeModel, HttpServletRequest request) {
		employeeService.addEmployee(employeeModel);
		return jTableList;
	}
	//end update employee
*/	
	//start update employees
	@RequestMapping(value="/employee/deleteEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String deleteEmployee(@RequestParam("employeeId")Integer employeeId , HttpServletRequest request) {
		employeeService.deleteEmployee(employeeId);
		return "Success";
	}
	//end update employee
	
	//start clear employees
	@RequestMapping(value="/clear", method = RequestMethod.GET)
	public String clearEmployeeForm(Model model, HttpServletRequest request) {
		
		com.ps.model.Model.MODE = "Add";
		model.addAttribute("employeeData", new EmployeeModel());
		return "search-employee";
	}
	//end add employee
	
	//	Getter and Setter Methods
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public LocationController getLocationController() {
		return locationController;
	}
	public void setLocationController(LocationController locationController) {
		this.locationController = locationController;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public List<DepartmentModel> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentModel> departmentList) {
		this.departmentList = departmentList;
	}

}
