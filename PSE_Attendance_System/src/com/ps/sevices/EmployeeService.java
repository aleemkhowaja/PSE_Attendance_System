package com.ps.sevices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ps.common.JTableList;
import com.ps.model.EmployeeModel;

public interface EmployeeService {

	public JTableList<EmployeeModel> returnAllEmployeesForGrid(HttpServletRequest request);
	
	public String addEmployee(EmployeeModel employeeModel);
	
	public void updateEmployee(Integer departmentId, String name, String fatherName, String mobileNo, String registrationNo, String presentAddress, String username, String password, String role);
	
	public JTableList deleteEmployee(Integer employeeid);
	
	public EmployeeModel getEmployeeById(Integer employeeId);
	
	public List<EmployeeModel> returnAllEmployees(String user);
	
}
