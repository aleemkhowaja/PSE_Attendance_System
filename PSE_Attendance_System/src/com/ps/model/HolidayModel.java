package com.ps.model;

import java.sql.Timestamp;

public class HolidayModel extends Model {

	private Integer holidayId;
	private String holiday;
	private Timestamp date;
	private String description;
	
	/**
	 * @return the holidayId
	 */
	public Integer getHolidayId() {
		return holidayId;
	}
	/**
	 * @param holidayId the holidayId to set
	 */
	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}
	/**
	 * @return the holiday
	 */
	public String getHoliday() {
		return holiday;
	}
	/**
	 * @param holiday the holiday to set
	 */
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
    	
}
