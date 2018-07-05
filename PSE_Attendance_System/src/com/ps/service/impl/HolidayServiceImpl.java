package com.ps.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.common.DateUtil;
import com.ps.common.JTableList;
import com.ps.dao.HolidayDao;
import com.ps.model.HolidayModel;
import com.ps.sevices.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayDao holidayDao;
	
	public HolidayDao getHolidayDaoDao() {
		return holidayDao;
	}

	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}

	@Override
	@Transactional
	public JTableList<HolidayModel> returnAllHolidaysForGrid(HttpServletRequest request) {
		JTableList<HolidayModel> jTableList = new JTableList<HolidayModel>();
		try {
			final String TIME = " 00:00:00";
			String order="";
			String sortingProperty="";
			String jtSorting = request.getParameter("jtSorting");
			Integer jtStartIndex = request.getParameter("jtStartIndex") == null ? null : Integer.parseInt(request.getParameter("jtStartIndex"));
			Integer jtPageSize = request.getParameter("jtPageSize") == null ? null : Integer.parseInt(request.getParameter("jtPageSize"));
			String date = request.getParameter("holiday");
            System.out.println("--------"+date);
			//String attendanceDate = DateUtil.stringToTimeStampWithTime(date);
			Timestamp holidayDate = null;
			if(date != null && !"".equals(date))
			{
				holidayDate = DateUtil.stringToTimeStampWithTime2(date+TIME);
			}
			
			if(jtSorting !=null && !jtSorting.equalsIgnoreCase("")){
				String[] propertyOrder= jtSorting.split(" ");
				sortingProperty= "table_name";
				order= propertyOrder[1];			
			}
		
			List<HolidayModel> holidayModels = holidayDao.returnAllHolidaysForGrid(jtStartIndex, jtPageSize, sortingProperty, order, holidayDate);
			
			Long size = new Long(holidayModels.size());
			jTableList.setRecords(holidayModels);
			jTableList.setResult("OK");
			jTableList.setTotalRecordCount(size);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jTableList;
		
	}

	@Override
	@Transactional
	public JTableList addHoliday(HolidayModel holidayModel, String mode) {
		final String TIME = " 00:00:00";
		System.out.println("====="+holidayModel.getHoliday());
		Timestamp holiday = DateUtil.stringToTimeStampWithTime2(holidayModel.getHoliday()+TIME);
		
		//Date holiday = DateUtil.stringToTimeStamp(holidayModel.getHoliday());
		holidayModel.setDate(holiday);
		JTableList jTableList = null;
		try {
			jTableList = new JTableList();
			if(holidayModel != null && holidayModel.getHolidayId() == null) {
				holidayDao.addHoliday(holidayModel);
			} else {
				holidayDao.updateHoliday(holidayModel);
			}
			jTableList.setRecord(holidayModel);
			jTableList.setResult("OK");
		} catch(Exception e) {
			jTableList.setResult("ERROR");
			e.printStackTrace();
		}
		return jTableList;
	}

	@Override
	@Transactional
	public void updateHoliday(HolidayModel holidayModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void deleteHoliday(Integer holidayId) {
		
		try {
			holidayDao.deleteHoliday(holidayId);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public HolidayModel getHolidayById(Integer holidayId) {
		HolidayModel holidayModel = holidayDao.getHolidayById(holidayId);
		try {
			if(holidayModel != null && holidayModel.getHoliday() != null)
			{
				DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromFormat.setLenient(false);
				DateFormat toFormat = new SimpleDateFormat("dd-MMM-yyyy");
				Date date = fromFormat.parse(holidayModel.getHoliday());
				
				holidayModel.setHoliday(toFormat.format(date));	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return holidayModel;
	}
	
	@Override
	public List<HolidayModel> returnAllHolidays() {
		return holidayDao.returnAllHolidays();
	}

}
