package com.ps.sevices;

import javax.servlet.http.HttpServletRequest;
import com.ps.common.JTableList;
import com.ps.model.AttendanceDetailModel;

public interface AttendanceDetailService {
	/**
	 * return all attendance details for Grid
	 * @param request
	 * @return
	 */
	public JTableList<AttendanceDetailModel> returnAllAttendaceDetailForGrid(HttpServletRequest request);
	
	/**
	 * Add Attendance Details
	 * @param request
	 * @param mode
	 * @return
	 */
	public String addAttendanceDetail(HttpServletRequest request, String mode);
	
	/**
	 * update Attendance Details
	 * @param attendanceDetailModel
	 * @return
	 */
	public String updateAttendanceDetail(AttendanceDetailModel attendanceDetailModel);
	
	/**
	 * Delete Attendance Details
	 * @param attendanceDetailId
	 */
	public void deleteAttendance(Integer attendanceDetailId);
	
	/**
	 * return Attendance Details
	 * @param attendanceDetailId
	 * @return
	 */
	public AttendanceDetailModel getAttendanceDetailById(Integer attendanceDetailId);
}
