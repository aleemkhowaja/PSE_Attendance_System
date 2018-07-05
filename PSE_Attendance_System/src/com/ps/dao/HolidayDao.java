package com.ps.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ps.model.EmployeeModel;
import com.ps.model.HolidayModel;

public interface HolidayDao {
	
	public List<HolidayModel> returnAllHolidaysForGrid(int jtStartIndex, int jtPageSize,String sortingProperty, String order, Timestamp holidate);
	
	public void addHoliday(HolidayModel holidayModel);
	
	public void updateHoliday(HolidayModel holidayModel);
	
	public void deleteHoliday(Integer holidayId);
	
	public HolidayModel getHolidayById(Integer holidayId);
	
	public List<HolidayModel> returnAllHolidays();

}
