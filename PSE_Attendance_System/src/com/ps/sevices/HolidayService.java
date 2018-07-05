package com.ps.sevices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ps.common.JTableList;
import com.ps.model.EmployeeModel;
import com.ps.model.HolidayModel;

public interface HolidayService {

	public JTableList<HolidayModel> returnAllHolidaysForGrid(HttpServletRequest request);
	
	public JTableList addHoliday(HolidayModel holidayModel, String mode);
	
	public void updateHoliday(HolidayModel holidayModel);
	
	public void deleteHoliday(Integer holidayId);
	
	public HolidayModel getHolidayById(Integer holidayId);
	
	public List<HolidayModel> returnAllHolidays();
	
}
