package com.ps.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.common.JTableList;
import com.ps.common.SecurityUtil;
import com.ps.dao.EmployeeDao;
import com.ps.model.EmployeeModel;
import com.ps.sevices.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	@Transactional
	public JTableList<EmployeeModel> returnAllEmployeesForGrid(HttpServletRequest request) {
		JTableList<EmployeeModel> jTableList = new JTableList<EmployeeModel>();
		try {
			String order="";
			String sortingProperty="";
			String jtSorting = request.getParameter("jtSorting");
			Integer jtStartIndex = request.getParameter("jtStartIndex") == null ? null : Integer.parseInt(request.getParameter("jtStartIndex"));
			Integer jtPageSize = request.getParameter("jtPageSize") == null ? null : Integer.parseInt(request.getParameter("jtPageSize"));
			
			if(jtSorting !=null && !jtSorting.equalsIgnoreCase("")){
				String[] propertyOrder= jtSorting.split(" ");
				sortingProperty= "table_name";
				order= propertyOrder[1];			
			}
		    String employeeName = request.getParameter("employeeName");
			List<EmployeeModel> employeeModels = employeeDao.returnAllEmployeesForGrid(employeeName, jtStartIndex, jtPageSize, sortingProperty, order);
			
			Long locationSize = new Long(employeeModels.size());
			jTableList.setRecords(employeeModels);
			jTableList.setResult("OK");
			jTableList.setTotalRecordCount(locationSize);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jTableList;
		
	}

	@Override
	@Transactional
	public String addEmployee(EmployeeModel employeeModel) {
		
		try {
			employeeModel.setRole("employee");
			Boolean check = true;
			if(employeeModel.getEmployeeId() != null) 
			{
				EmployeeModel employeeModel2 = employeeDao.getEmployeeById(employeeModel.getEmployeeId());
				if(employeeModel2.getUsername().equalsIgnoreCase(employeeModel.getUsername()))
				{
					if(employeeModel2.getRegistrationNo().equalsIgnoreCase(employeeModel.getRegistrationNo()))
					{
						check = true;
					}
					else
					{
						employeeModel2 = employeeDao.getEmployeeByPasswordORRegistrationNo(null, employeeModel.getRegistrationNo());
						if(employeeModel2 != null)
						{
							return "Duplicate Employee Registration No";
						}
						else
						{
							check = true;
						}
					}
				} 
				else
				{
					employeeModel2 = employeeDao.getEmployeeByPasswordORRegistrationNo(employeeModel.getUsername(), null);
					if(employeeModel2 != null)
					{
						return "Duplicate Employee Username";
					}
					else
					{
						check = true;
					}
				}
				
				if(check)
				{
					if(employeeModel.getPassword() != null && !"".equals(employeeModel.getPassword()))
					{
						String encryptedPassword = SecurityUtil.encodePassword(employeeModel.getPassword());
						employeeModel2.setPassword(encryptedPassword);
					}
					employeeModel2.setEmployeeName(employeeModel.getEmployeeName());
					employeeModel2.setFathersName(employeeModel.getFathersName());
					employeeModel2.setMobileNo(employeeModel.getMobileNo());
					employeeModel2.setRegistrationNo(employeeModel.getRegistrationNo());
					employeeModel2.setAddress(employeeModel.getAddress());
					employeeModel2.setUsername(employeeModel.getUsername());
					employeeModel2.setDepartment(employeeModel.getDepartment());
					employeeModel2.setLocation(employeeModel.getLocation());
					employeeModel2.setIsActive(employeeModel.getIsActive());
					employeeDao.updateEmployee(employeeModel2);
					return "Employee Saved Successfuly";
				}
				
			} else 
			{
				EmployeeModel employeeModel2 = employeeDao.getEmployeeByPasswordORRegistrationNo(employeeModel.getUsername(), null);
				if(employeeModel2 != null)
				{
					return "Duplicate Employee Username";
				}
				else
				{
					employeeModel2 = employeeDao.getEmployeeByPasswordORRegistrationNo(null, employeeModel.getRegistrationNo());
					if(employeeModel2 != null)
					{
						return "Duplicate Employee Registration No";
					}
					else
					{
						String encryptedPassword = SecurityUtil.encodePassword(employeeModel.getPassword());
						employeeModel.setPassword(encryptedPassword);
						employeeDao.addEmployee(employeeModel);
						return "Employee Saved Successfuly";
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	@Transactional
	public void updateEmployee(Integer departmentId, String name, String fatherName, String mobileNo,String registrationNo, String presentAddress, String username, String password, String role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public JTableList deleteEmployee(Integer employeeId) {
		
		JTableList jTableList = new JTableList();
		try {
			employeeDao.deleteEmployee(employeeId);
			jTableList.setResult("OK");
		} catch(Exception e) {
			jTableList.setResult("ERROR");
			e.printStackTrace();
		}
		return jTableList;
	}

	@Override
	public EmployeeModel getEmployeeById(Integer employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}
	
	@Override
	public List<EmployeeModel> returnAllEmployees(String user) {
		return employeeDao.returnAllEmployees(user);
	}

}
