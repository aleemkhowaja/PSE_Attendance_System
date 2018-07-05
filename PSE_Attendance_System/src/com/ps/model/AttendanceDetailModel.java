package com.ps.model;

import java.sql.Timestamp;

public class AttendanceDetailModel extends Model {
	
	private Integer attendanceDetailId;
	private Timestamp attendanceDate;
	private String attendanceDateString;
	private String timeStatus;
	private String inTime;
	private String outTime;
	private String inTimeDescription;
	private String outTimeDescription;
	private String inLateTime;
	private String inTimeStatus;
	private String outLateTime;
	private String outTimeStatus; 
	private Integer employee;
	private String emplayeeName;
	private Integer month;
	private Integer year;
	private String placeAt;
	private String onLeave;
	private String remarks;
	private String department;
	
	public AttendanceDetailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the attendanceDetailId
	 */
	public Integer getAttendanceDetailId() {
		return attendanceDetailId;
	}
	
	/**
	 * @param attendanceDetailId the attendanceDetailId to set
	 */
	public void setAttendanceDetailId(Integer attendanceDetailId) {
		this.attendanceDetailId = attendanceDetailId;
	}
	
	/**
	 * @return the timeStatus
	 */
	public String getTimeStatus() {
		return timeStatus;
	}
	
	/**
	 * @param timeStatus the timeStatus to set
	 */
	public void setTimeStatus(String timeStatus) {
		this.timeStatus = timeStatus;
	}
	
	/**
	 * @return the attendanceDate
	 */
	public Timestamp getAttendanceDate() {
		return attendanceDate;
	}
	
	/**
	 * @param attendanceDate the attendanceDate to set
	 */
	public void setAttendanceDate(Timestamp attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	/**
	 * @return the attendanceDateString
	 */
	public String getAttendanceDateString() {
		return attendanceDateString;
	}

	/**
	 * @param attendanceDateString the attendanceDateString to set
	 */
	public void setAttendanceDateString(String attendanceDateString) {
		this.attendanceDateString = attendanceDateString;
	}

	/**
	 * @return the inTime
	 */
	public String getInTime() {
		return inTime;
	}
	
	/**
	 * @param inTime the inTime to set
	 */
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	
	/**
	 * @return the outTime
	 */
	public String getOutTime() {
		return outTime;
	}
	
	/**
	 * @param outTime the outTime to set
	 */
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	/**
	 * 
	 * @return inTimeDescription
	 */
	public String getInTimeDescription() {
		return inTimeDescription;
	}
	
	/**
	 * 
	 * @param inTimeDescription
	 */
	public void setInTimeDescription(String inTimeDescription) {
		this.inTimeDescription = inTimeDescription;
	}
	
	/**
	 * 
	 * @return outTimeDescription
	 */
	public String getOutTimeDescription() {
		return outTimeDescription;
	}
	
	/**
	 * 
	 * @param outTimeDescription
	 */
	public void setOutTimeDescription(String outTimeDescription) {
		this.outTimeDescription = outTimeDescription;
	}
	
	/**
	 * @return the lateTime
	 */
	public String getInLateTime() {
		return inLateTime;
	}

	/**
	 * @param lateTime the lateTime to set
	 */
	public void setInLateTime(String inLateTime) {
		this.inLateTime = inLateTime;
	}

	/**
	 * @return the status
	 */
	public String getInTimeStatus() {
		return inTimeStatus;
	}

	/**
	 * @param status the status to set
	 */
	public void setInTimeStatus(String inTimeStatus) 
	{
		this.inTimeStatus = inTimeStatus;	
	}

	/**
	 * @return the outLateTime
	 */
	public String getOutLateTime() {
		return outLateTime;
	}

	/**
	 * @param outLateTime the outLateTime to set
	 */
	public void setOutLateTime(String outLateTime) {
		this.outLateTime = outLateTime;
	}

	/**
	 * @return the outTimeStatus
	 */
	public String getOutTimeStatus() {
		return outTimeStatus;
	}

	/**
	 * @param outTimeStatus the outTimeStatus to set
	 */
	public void setOutTimeStatus(String outTimeStatus) {
		this.outTimeStatus = outTimeStatus;
	}

	/**
	 * @return the employee
	 */
	public Integer getEmployee() {
		return employee;
	}
	
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	/**
	 * @return the emplayeeName
	 */
	public String getEmplayeeName() {
		return emplayeeName;
	}

	/**
	 * @param emplayeeName the emplayeeName to set
	 */
	public void setEmplayeeName(String emplayeeName) {
		this.emplayeeName = emplayeeName;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the placeAt
	 */
	public String getPlaceAt() {
		return placeAt;
	}

	/**
	 * @param placeAt the placeAt to set
	 */
	public void setPlaceAt(String placeAt) {
		this.placeAt = placeAt;
	}

	/**
	 * @return the onLeave
	 */
	public String getOnLeave() {
		return onLeave;
	}

	/**
	 * @param onLeave the onLeave to set
	 */
	public void setOnLeave(String onLeave) {
		this.onLeave = onLeave;
	}
	
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
