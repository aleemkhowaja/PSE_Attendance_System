package com.ps.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ps.model.AttendanceDetailModel;
import com.ps.model.EmployeeModel;

public interface AttendanceReportDao {
	
	/**
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException;

}
