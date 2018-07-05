package com.ps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ps.dao.LoginDao;
import com.ps.model.EmployeeModel;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public EmployeeModel login(String username, String password) {
		
		try {
			EmployeeModel employeeModel = this.jdbcTemplate.queryForObject(
			        "select * from employee where username = ? and password = ?",
			        new Object[]{username,password},
			        new RowMapper<EmployeeModel>() {
			            public EmployeeModel mapRow(ResultSet rs, int rowNum)  throws SQLException {
			            	
			            	EmployeeModel employeeModel = null;
			            	employeeModel = new EmployeeModel();
				            employeeModel.setEmployeeId(rs.getInt("employee_id"));
							employeeModel.setEmployeeName(rs.getString("name"));
							employeeModel.setFathersName(rs.getString("father_name"));
							employeeModel.setMobileNo(rs.getString("mobile_no"));
							employeeModel.setRegistrationNo(rs.getString("registration_no"));
							employeeModel.setAddress(rs.getString("present_address"));
							employeeModel.setUsername(rs.getString("username"));
							employeeModel.setPassword(rs.getString("password"));
							employeeModel.setRole(rs.getString("role"));
							employeeModel.setIsActive(rs.getBoolean("isActive"));
			                return employeeModel;
			            }
			        });
			return employeeModel;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	

}
