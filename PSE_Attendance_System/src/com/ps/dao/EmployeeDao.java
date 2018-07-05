package com.ps.dao;

import java.util.List;
import com.ps.model.EmployeeModel;

public interface EmployeeDao {
	
	public List<EmployeeModel> returnAllEmployeesForGrid(String employeeName, int jtStartIndex, int jtPageSize,String sortingProperty, String order);
	
	public void addEmployee(EmployeeModel employeeModel);
	
	public void updateEmployee(EmployeeModel employeeModel);
	
	public void deleteEmployee(Integer employeeId);
	
	public EmployeeModel getEmployeeById(Integer employeeId);
	
	public EmployeeModel getEmployeeByPasswordORRegistrationNo(String password, String registrationNo);
	
	public List<EmployeeModel> returnAllEmployees(String user);

}
