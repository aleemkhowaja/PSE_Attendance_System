package com.ps.dao.impl;

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
import com.ps.model.AttendanceDetailModel;

@Repository
public class AttendanceDetailDaoImpl implements AttendanceDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<AttendanceDetailModel> returnAllAttendanceDetailForGrid(int jtStartIndex, int jtPageSize, String sortingProperty, String order, Timestamp attendanceDate, Integer employeeId) {
		StringBuilder sql = new StringBuilder("select * from attendance_detail ad left outer join employee e "
				+ "on e.employee_id=ad.employee_id left outer join department d on e.department_id=d.department_id where ad.is_deleted=0");
		
		if(employeeId != null) 
		{
			sql.append(" and ad.employee_id="+employeeId);
		}
		if(attendanceDate != null && !"".equals(attendanceDate)) 
		{
			sql.append(" and ad.date = '"+attendanceDate+"' ");
		}
		
		List<AttendanceDetailModel> employeeModels = this.jdbcTemplate.query(sql.toString(),
				new Object[]{},
		        new RowMapper<AttendanceDetailModel>() {
		            public AttendanceDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
		            	String date = DateUtil.timeStampToString(rs.getTimestamp("date"));
		            	attendanceDetailModel.setAttendanceDateString(date);
		            	attendanceDetailModel.setInTime(rs.getString("in_time"));
		            	attendanceDetailModel.setOutTime(rs.getString("out_time"));
		            	if(rs.getString("leave_sick_leave").equals("Absent"))
		            	{
		            		attendanceDetailModel.setOnLeave("A");
		            	}
		            	else if(rs.getString("leave_sick_leave").equals("Present"))
		            	{
		            		attendanceDetailModel.setOnLeave("P");
		            	}
		            	else
		            	{
		            		attendanceDetailModel.setOnLeave("L");
		            	}
		            	attendanceDetailModel.setOnLeave(rs.getString("leave_sick_leave"));
		            	attendanceDetailModel.setEmplayeeName(rs.getString("name"));
		            	attendanceDetailModel.setAttendanceDetailId(rs.getInt("attendance_detail_id"));
		            	attendanceDetailModel.setDepartment(rs.getString("department_name"));
		                return attendanceDetailModel;
		            }
		        });
		return employeeModels;
	}
	
	@Override
	public int addAttendance(AttendanceDetailModel attendanceDetailModel) {
		StringBuilder  query = new StringBuilder("insert into attendance_detail ");
		StringBuilder parameters = new StringBuilder("(employee_id, date, in_time, in_time_description, in_late_time, in_time_status, present_at, out_time, out_time_description, out_late_time, out_time_status,leave_sick_leave,is_deleted, remarks)");
		StringBuilder  values = new StringBuilder("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		int rows = this.jdbcTemplate.update(query.append(parameters).append(values).toString(),
		        attendanceDetailModel.getEmployee(),attendanceDetailModel.getAttendanceDate(), attendanceDetailModel.getInTime(), 
		        attendanceDetailModel.getInTimeDescription(),attendanceDetailModel.getInLateTime(), attendanceDetailModel.getInTimeStatus(),attendanceDetailModel.getPlaceAt(),
		        attendanceDetailModel.getOutTime(), attendanceDetailModel.getOutTimeDescription(), attendanceDetailModel.getOutLateTime(), attendanceDetailModel.getOutTimeStatus(),attendanceDetailModel.getOnLeave(),0,attendanceDetailModel.getRemarks());
		return rows;
	}
	
	@Override
	public int updateAttendance(AttendanceDetailModel attendanceDetailModel) {
		int rows = this.jdbcTemplate.update("update attendance_detail set employee_id=?, date=?, "
				+ "in_time=?,in_time_description=?, in_late_time=?, in_time_status=?, present_at=?, out_time=? , out_time_description=?, "
				+ "out_late_time=?, out_time_status=? , leave_sick_leave=?, remarks=?"
				+ " where attendance_detail_id = ?",
				attendanceDetailModel.getEmployee(),
				attendanceDetailModel.getAttendanceDate(),
				attendanceDetailModel.getInTime(), 
				attendanceDetailModel.getInTimeDescription(),
				attendanceDetailModel.getInLateTime(),
				attendanceDetailModel.getInTimeStatus(),
				attendanceDetailModel.getPlaceAt(), 
				attendanceDetailModel.getOutTime(), 
				attendanceDetailModel.getOutTimeDescription(), 
				attendanceDetailModel.getOutLateTime(), 
				attendanceDetailModel.getOutTimeStatus(),
				attendanceDetailModel.getOnLeave(), 
				attendanceDetailModel.getRemarks(), 
				attendanceDetailModel.getAttendanceDetailId());
		return rows;
	}
	
	@Override
	public void deleteAttendance(Integer attendanceDetailId) { 
		this.jdbcTemplate.update("update attendance_detail set is_deleted=1 where attendance_detail_id = ?", attendanceDetailId);		
		
	}
	@Override
	public AttendanceDetailModel getAttendanceById(Integer attendanceDetailId) {
		AttendanceDetailModel attendanceDetailModel;
		try{
			attendanceDetailModel = this.jdbcTemplate.queryForObject(
			"select * from attendance_detail ad  left outer join employee e on e.employee_id=ad.employee_id where ad.attendance_detail_id = ?",
			new Object[]{attendanceDetailId},
			new RowMapper<AttendanceDetailModel>() {
				public AttendanceDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
					attendanceDetailModel.setEmployee(rs.getInt("employee_id"));
					attendanceDetailModel.setAttendanceDateString(rs.getString("date"));
			      	attendanceDetailModel.setInTime(rs.getString("in_time"));
			      	attendanceDetailModel.setOutTime(rs.getString("out_time"));
			        attendanceDetailModel.setAttendanceDetailId(rs.getInt("attendance_detail_id"));
			        attendanceDetailModel.setPlaceAt(rs.getString("present_at"));
			        attendanceDetailModel.setOnLeave(rs.getString("leave_sick_leave"));
			        attendanceDetailModel.setRemarks(rs.getString("remarks"));
			        return attendanceDetailModel;
			    }
			});
		}
		catch(EmptyResultDataAccessException er)
		{
			return null;
		}
		return attendanceDetailModel;
	}
	@Override
	public AttendanceDetailModel getAttendanceByDate(Integer employeeId, Timestamp attendanceDate) {
		
		AttendanceDetailModel attendanceDetailModel;
		try{
			attendanceDetailModel = this.jdbcTemplate.queryForObject(
			"select * from attendance_detail ad where ad.date = ? and employee_id = ? and ad.is_deleted=0",
			new Object[]{attendanceDate, employeeId},
			new RowMapper<AttendanceDetailModel>() {
				public AttendanceDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
					attendanceDetailModel.setEmployee(rs.getInt("employee_id"));
					attendanceDetailModel.setAttendanceDate(rs.getTimestamp("date"));
			      	attendanceDetailModel.setInTime(rs.getString("in_time"));
			        attendanceDetailModel.setAttendanceDetailId(rs.getInt("attendance_detail_id"));
			        attendanceDetailModel.setRemarks(rs.getString("remarks"));
			        return attendanceDetailModel;
			    }
			});
		}
		catch(EmptyResultDataAccessException er)
		{
			return null;
		}
		return attendanceDetailModel;
	}
	
}
