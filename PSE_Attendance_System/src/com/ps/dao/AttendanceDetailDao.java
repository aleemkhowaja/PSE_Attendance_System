package com.ps.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ps.model.AttendanceDetailModel;
import com.ps.model.EmployeeModel;

public interface AttendanceDetailDao {
	
	public List<AttendanceDetailModel> returnAllAttendanceDetailForGrid(int jtStartIndex, int jtPageSize,String sortingProperty, String order, 
			Timestamp attendanceDae, Integer employeeId);
	
	public int addAttendance(AttendanceDetailModel attendanceDetailModel);
	
	public int updateAttendance(AttendanceDetailModel attendanceDetailModel);
	
	public void deleteAttendance(Integer attendanceDetailId);
	
	public AttendanceDetailModel getAttendanceById(Integer attendanceDetailId) ;
	
	public AttendanceDetailModel getAttendanceByDate(Integer employeeId, Timestamp attendanceDate) ;
	
}
