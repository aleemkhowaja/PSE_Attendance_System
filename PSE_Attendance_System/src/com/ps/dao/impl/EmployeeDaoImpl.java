package com.ps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ps.dao.EmployeeDao;
import com.ps.model.DepartmentModel;
import com.ps.model.EmployeeModel;
import com.ps.model.LocationModel;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<EmployeeModel> returnAllEmployeesForGrid(String employeeName, int jtStartIndex, int jtPageSize, String sortingProperty, String order) {
		
		StringBuilder query = new StringBuilder("select * from employee e left outer join department d on e.department_id=d.department_id left outer join location l on e.location_id=l.location_id where isDeleted=0 ");
		if(employeeName != null && !"".equals(employeeName))
		{
			query.append(" and e.name like '%"+employeeName+"%'");
		}
		List<EmployeeModel> employeeModels = this.jdbcTemplate.query(query.toString(),
		        new RowMapper<EmployeeModel>() {
		            public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	EmployeeModel employeeModel = new EmployeeModel();
		            	employeeModel.setEmployeeId(rs.getInt("e.employee_id"));
		            	employeeModel.setEmployeeName(rs.getString("e.name"));
		            	employeeModel.setFathersName(rs.getString("e.father_name"));
		            	employeeModel.setMobileNo(rs.getString("e.mobile_no"));
		            	employeeModel.setRegistrationNo(rs.getString("e.registration_no"));
		            	employeeModel.setAddress(rs.getString("e.present_address"));
		            	employeeModel.setUsername(rs.getString("e.username"));
		            	employeeModel.setPassword(rs.getString("e.password"));
		            	employeeModel.setRole(rs.getString("e.role"));
		            	employeeModel.setDepartment(rs.getString("d.department_id"));
		            	employeeModel.setLocation(rs.getString("l.location_id"));	
		            	employeeModel.setIsActive(rs.getBoolean("e.isActive"));
		                return employeeModel;
		            }
		        });
		return employeeModels;
	}

	@Override
	public void addEmployee(EmployeeModel employeeModel) {
		
		this.jdbcTemplate.update(
		        "insert into employee(department_id, location_id, name, father_name, mobile_no, registration_no, present_address, username, password, role, isActive, isDeleted) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)",
		        employeeModel.getDepartment(), employeeModel.getLocation(), employeeModel.getEmployeeName(), employeeModel.getFathersName(), employeeModel.getMobileNo(), employeeModel.getRegistrationNo(),employeeModel.getAddress(),employeeModel.getUsername(),employeeModel.getPassword(),employeeModel.getRole(), employeeModel.getIsActive(), 0);
	}

	@Override
	public void updateEmployee(EmployeeModel employeeModel) {
		
		this.jdbcTemplate.update(
				 "update employee set department_id = ?, location_id = ?, name = ?, father_name = ?, mobile_no = ?, registration_no = ?, present_address = ?, username = ?, isActive=?  where employee_id = ?",
				 employeeModel.getDepartment(), employeeModel.getLocation(), employeeModel.getEmployeeName(), employeeModel.getFathersName(), employeeModel.getMobileNo(), employeeModel.getRegistrationNo(), employeeModel.getAddress(), employeeModel.getUsername(), employeeModel.getIsActive() ,employeeModel.getEmployeeId());
		
		if(employeeModel.getPassword() != null || !"".equals(employeeModel.getPassword()))
		{
			this.jdbcTemplate.update(
					 "update employee set password = ?  where employee_id = ?",
					 employeeModel.getPassword() , employeeModel.getEmployeeId());
		}
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		
		this.jdbcTemplate.update("update employee set isDeleted=1 where employee_id = ?", employeeId);		
	}
	
	@Override
	public EmployeeModel getEmployeeById(Integer employeeId) {
		EmployeeModel employeeModel = this.jdbcTemplate.queryForObject(
		        "select * from employee e left outer join department d on e.department_id=d.department_id left outer join location l on e.location_id=l.location_id where e.employee_id = ?",
		       
		        new Object[]{employeeId},
		        new RowMapper<EmployeeModel>() {
		            public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	EmployeeModel employeeModel = new EmployeeModel();
		            	employeeModel.setLocation(""+rs.getInt("l.location_id"));
		            	employeeModel.setDepartment(""+rs.getString("d.department_id"));
		            	employeeModel.setEmployeeId(rs.getInt("e.employee_id"));
		            	employeeModel.setEmployeeName(rs.getString("e.name"));
		            	employeeModel.setFathersName(rs.getString("e.father_name"));
		            	employeeModel.setMobileNo(rs.getString("e.mobile_no"));
		            	employeeModel.setRegistrationNo(rs.getString("e.registration_no"));
		            	employeeModel.setAddress(rs.getString("e.present_address"));
		            	employeeModel.setUsername(rs.getString("e.username"));
		            	employeeModel.setPassword(rs.getString("e.password"));
		            	employeeModel.setRole(rs.getString("e.role"));
		            	employeeModel.setIsActive(rs.getBoolean("e.isActive"));
		                return employeeModel;
		            }
		        });
				return employeeModel;
	}
	
	@Override
	public List<EmployeeModel> returnAllEmployees(String user) {

		List<EmployeeModel> employeeModels = this.jdbcTemplate.query(
				"select * from employee e where e.isDeleted=0 and e.role='"+user+"' ",
		        new RowMapper<EmployeeModel>() {
		            public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	EmployeeModel employeeModel = new EmployeeModel();
		            	employeeModel.setEmployeeId(rs.getInt("e.employee_id"));
		            	employeeModel.setEmployeeName(rs.getString("e.name"));
		                return employeeModel;
		            }
		        });
		return employeeModels;
	}
	@Override
	public EmployeeModel getEmployeeByPasswordORRegistrationNo(String password,
			String registrationNo) {
		
		try {
			StringBuilder query = new StringBuilder("select * from employee e left outer join department d on e.department_id=d.department_id"
					+ " left outer join location l on e.location_id=l.location_id where e.username = ? "
					+ " OR e.registration_no = ?");
			
			EmployeeModel employeeModel = this.jdbcTemplate.queryForObject(query.toString(),
			       
			        new Object[]{password, registrationNo},
			        new RowMapper<EmployeeModel>() {
			            public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	EmployeeModel employeeModel = new EmployeeModel();
			            	employeeModel.setLocation(""+rs.getInt("l.location_id"));
			            	employeeModel.setDepartment(""+rs.getString("d.department_id"));
			            	employeeModel.setEmployeeId(rs.getInt("e.employee_id"));
			            	employeeModel.setEmployeeName(rs.getString("e.name"));
			            	employeeModel.setFathersName(rs.getString("e.father_name"));
			            	employeeModel.setMobileNo(rs.getString("e.mobile_no"));
			            	employeeModel.setRegistrationNo(rs.getString("e.registration_no"));
			            	employeeModel.setAddress(rs.getString("e.present_address"));
			            	employeeModel.setUsername(rs.getString("e.username"));
			            	employeeModel.setPassword(rs.getString("e.password"));
			            	employeeModel.setRole(rs.getString("e.role"));
			            	employeeModel.setIsActive(rs.getBoolean("e.isActive"));
			                return employeeModel;
			            }
			        });
					return employeeModel;
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
