package com.ps.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ps.common.DateUtil;
import com.ps.dao.AttendanceDetailDao;
import com.ps.dao.AttendanceReportDao;
import com.ps.model.AttendanceDetailModel;
import com.ps.model.EmployeeModel;

@Repository
public class AttendanceReportDaoImpl implements AttendanceReportDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Connection connection;
	
	@Override
	public Connection getConnection() throws SQLException {
		if (connection == null) {
    		try {
    			connection = getJdbcTemplate().getDataSource().getConnection();
            }catch (Exception e) {
				e.printStackTrace();
			}
        }
        return connection;
	}
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
