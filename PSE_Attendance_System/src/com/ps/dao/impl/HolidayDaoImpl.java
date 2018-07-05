package com.ps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ps.common.DateUtil;
import com.ps.dao.HolidayDao;
import com.ps.model.DepartmentModel;
import com.ps.model.EmployeeModel;
import com.ps.model.HolidayModel;
import com.ps.model.LocationModel;

@Repository
public class HolidayDaoImpl implements HolidayDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<HolidayModel> returnAllHolidaysForGrid(int jtStartIndex, int jtPageSize, String sortingProperty, String order, Timestamp holydayDate) {
		
		StringBuilder sql = new StringBuilder("select * from holidays h");
		if(holydayDate != null && !"".equals(holydayDate)) 
		{
			sql.append(" where h.holiday = '"+holydayDate+"' ");
		}
		
		List<HolidayModel> holidayModels= this.jdbcTemplate.query(
				sql.toString(),
		        new RowMapper<HolidayModel>() {
		            public HolidayModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	HolidayModel holidayModel = new HolidayModel();
		            	holidayModel.setHolidayId(rs.getInt("holiday_id"));
		            	String date = DateUtil.timeStampToString(rs.getTimestamp("holiday"));
		            	holidayModel.setHoliday(date);
		            	holidayModel.setDescription(rs.getString("description"));
		                return holidayModel;
		            }
		        });
		return holidayModels;
	}

	@Override
	public void addHoliday(HolidayModel holidayModel) {
		
		this.jdbcTemplate.update(
		        "insert into holidays(holiday, description) values (?, ?)",
		        holidayModel.getDate(),holidayModel.getDescription());
	}

	@Override
	public void updateHoliday(HolidayModel holidayModel) {
		
		this.jdbcTemplate.update(
				 "update holidays set holiday = ?, description = ?  where holiday_id = ?",
				 holidayModel.getDate(), holidayModel.getDescription(), holidayModel.getHolidayId());		
	}

	@Override
	public void deleteHoliday(Integer holidayId) {
		
		this.jdbcTemplate.update("delete from holidays where holiday_id = ?",holidayId);		
	}
	
	@Override
	public HolidayModel getHolidayById(Integer holidayId) {
		HolidayModel holidayModel = this.jdbcTemplate.queryForObject(
		        "select * from holidays h where h.holiday_id = ?",
		       
		        new Object[]{holidayId},
		        new RowMapper<HolidayModel>() {
		            public HolidayModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	HolidayModel holidayModel = new HolidayModel();
		            	holidayModel.setHolidayId(rs.getInt("holiday_id"));
		            	holidayModel.setHoliday(rs.getString("holiday"));
		            	holidayModel.setDescription(rs.getString("description"));
		                return holidayModel;
		            }
		        });
				return holidayModel;
	}
	
	@Override
	public List<HolidayModel> returnAllHolidays() {

		List<HolidayModel> holidayModels= this.jdbcTemplate.query(
		        "select * from holidays",
		        new RowMapper<HolidayModel>() {
		            public HolidayModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	HolidayModel holidayModel = new HolidayModel();
		            	holidayModel.setHolidayId(rs.getInt("holiday_id"));
		            	holidayModel.setHoliday(rs.getString("holiday"));
		            	holidayModel.setDescription(rs.getString("description"));
		                return holidayModel;
		            }
		        });
		return holidayModels;
	}

}
