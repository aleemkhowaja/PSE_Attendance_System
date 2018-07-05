package com.ps.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ps.common.DateUtil;
import com.ps.common.JTableList;
import com.ps.dao.AttendanceDetailDao;
import com.ps.model.AttendanceDetailModel;
import com.ps.model.EmployeeModel;
import com.ps.sevices.AttendanceDetailService;

@Service
public class AttendanceDetailServiceImpl implements AttendanceDetailService{

	@Autowired
	private AttendanceDetailDao attendanceDetailDao;
	
	
	public AttendanceDetailDao getAttendanceDetailDao() {
		return attendanceDetailDao;
	}

	public void setAttendanceDetailDao(AttendanceDetailDao attendanceDetailDao) {
		this.attendanceDetailDao = attendanceDetailDao;
	}

	@Override
	public JTableList<AttendanceDetailModel> returnAllAttendaceDetailForGrid(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		EmployeeModel employeeModel = (EmployeeModel) session.getAttribute("employee");
		JTableList<AttendanceDetailModel> jTableList = new JTableList<AttendanceDetailModel>();
		try {
			final String TIME = " 00:00:00";
			String order="";
			String sortingProperty="";
			String jtSorting = request.getParameter("jtSorting");
			Integer jtStartIndex = request.getParameter("jtStartIndex") == null ? null : Integer.parseInt(request.getParameter("jtStartIndex"));
			Integer jtPageSize = request.getParameter("jtPageSize") == null ? null : Integer.parseInt(request.getParameter("jtPageSize"));
			if(jtSorting !=null && !jtSorting.equalsIgnoreCase("")){
				String[] propertyOrder= jtSorting.split(" ");
				sortingProperty= "table_name";
				order= propertyOrder[1];			
			}
			String date = request.getParameter("attendanceDate");
			//String attendanceDate = DateUtil.stringToTimeStampWithTime(date);
			Timestamp attendanceDate = null;
			if(date != null && !"".equals(date))
			{
				 attendanceDate = DateUtil.stringToTimeStampWithTime2(date+TIME);
			}
			
			List<AttendanceDetailModel> attendanceDetailModels;
			if(employeeModel.getRole().equals("admin"))
			{
				attendanceDetailModels = attendanceDetailDao.returnAllAttendanceDetailForGrid(jtStartIndex, jtPageSize, sortingProperty, order, attendanceDate, null);
			}
			else
			{
				attendanceDetailModels = attendanceDetailDao.returnAllAttendanceDetailForGrid(jtStartIndex, jtPageSize, sortingProperty, order, attendanceDate, employeeModel.getEmployeeId());
			}
			Long locationSize = new Long(attendanceDetailModels.size());
			jTableList.setRecords(attendanceDetailModels);
			jTableList.setResult("OK");
			jTableList.setTotalRecordCount(locationSize);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jTableList;
	}

	@Override
	public String addAttendanceDetail(HttpServletRequest request, String mode) 
	{
		String IN_TIME = "09:45 am";
		String OUT_TIME = "05:30 pm";
		final String TIME = " 00:00:00";
		AttendanceDetailModel attendanceDetailModel = new AttendanceDetailModel();
		String attendanceId = request.getParameter("attendanceDetailId");
		String employeeId = request.getParameter("employee");
		String employeeDate = request.getParameter("attendanceDate");
		String placeAt = request.getParameter("placeAt");
		String inTime = request.getParameter("inTime");
		String outTime = request.getParameter("outTime");
		String onLeave = request.getParameter("onLeave");
		String remarks = request.getParameter("remarks");
		
		HttpSession session = request.getSession(false);
		EmployeeModel employeeModel = (EmployeeModel) session.getAttribute("employee");
		
		SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
		
		Date date = new Date();
		SimpleDateFormat simpDate = new SimpleDateFormat("hh:mm a");
		String time =  simpDate.format(date);
		String result = "";
		
		try {
			
			if(employeeModel.getRole().equals("admin"))
			{
				String inTimeData[] = DateUtil.compareTime(employeeDate, inTime, IN_TIME);
				String outTimeData[] = DateUtil.compareTime(employeeDate, outTime, OUT_TIME);
				if(attendanceId == null || "".equals(attendanceId))
				{
					attendanceDetailModel = attendanceDetailDao.getAttendanceByDate(Integer.valueOf(employeeId), DateUtil.stringToTimeStampWithTime2(employeeDate+TIME));
					
					if(attendanceDetailModel == null)
					{
						attendanceDetailModel = new AttendanceDetailModel();
						attendanceDetailModel.setEmployee(Integer.valueOf(employeeId));
						attendanceDetailModel.setAttendanceDate(Timestamp.valueOf(inTimeData[4]));
						if(onLeave.equals("Present"))
						{
							if((Long.valueOf(inTimeData[1]) >= 0 && Long.valueOf(inTimeData[1]) <= 15) || (Long.valueOf(inTimeData[2]) >= 0 && Long.valueOf(inTimeData[2]) >= 59))
							{
								attendanceDetailModel.setInLateTime(inTimeData[3].toString());
								attendanceDetailModel.setInTimeStatus("O");
							}
							else
								if(Long.valueOf(inTimeData[0]) > 0 || Long.valueOf(inTimeData[1]) > 0 || Long.valueOf(inTimeData[2]) > 0)
							{
								attendanceDetailModel.setInLateTime(inTimeData[3].toString());
								attendanceDetailModel.setInTimeStatus("L");
							}
							else
							{
								attendanceDetailModel.setInLateTime(inTimeData[3]);
								attendanceDetailModel.setInTimeStatus("O");
							}
							attendanceDetailModel.setInTimeDescription((String)request.getParameter("remarks"));
							attendanceDetailModel.setInTime(inTime);
							attendanceDetailModel.setPlaceAt(placeAt);
							attendanceDetailModel.setOnLeave(onLeave);
							
							if(Long.valueOf(outTimeData[0]) > 0 || Long.valueOf(outTimeData[1]) > 0 || Long.valueOf(outTimeData[2]) > 0)
							{
								attendanceDetailModel.setOutLateTime(outTimeData[3]);
								attendanceDetailModel.setOutTimeStatus("L");
							}
							else
							{
								attendanceDetailModel.setOutLateTime(outTimeData[3]);
								attendanceDetailModel.setOutTimeStatus("B");
							}
							
							attendanceDetailModel.setOutTimeDescription((String)request.getParameter("remarks"));
							attendanceDetailModel.setOutTime(simpDate.format(date));
							attendanceDetailModel.setOutTime(outTime);
						}else
						{
							attendanceDetailModel.setInLateTime(null);
							attendanceDetailModel.setInTimeStatus(null);
							attendanceDetailModel.setInTimeDescription(null);
							attendanceDetailModel.setInTime(null);
							attendanceDetailModel.setPlaceAt(null);
							attendanceDetailModel.setOutTimeDescription(null);
							attendanceDetailModel.setOutTime(null);
							attendanceDetailModel.setOutLateTime(null);
							attendanceDetailModel.setOutTimeStatus(null);
							attendanceDetailModel.setOnLeave(onLeave);
						}
						attendanceDetailModel.setRemarks(remarks);
						attendanceDetailDao.addAttendance(attendanceDetailModel);
						result = "Success";
					}
					else
					{
						result = "duplicate";
					}
				}
				else
				{
					attendanceDetailModel = attendanceDetailDao.getAttendanceById(Integer.parseInt(attendanceId));
					attendanceDetailModel.setEmployee(Integer.valueOf(employeeId));
					attendanceDetailModel.setAttendanceDate(Timestamp.valueOf(inTimeData[4]));
					if(onLeave.equals("Present"))
					{
						if((Long.valueOf(inTimeData[1]) >= 0 && Long.valueOf(inTimeData[1]) <= 15) || (Long.valueOf(inTimeData[2]) >= 0 && Long.valueOf(inTimeData[2]) >= 59))
						{
							attendanceDetailModel.setInLateTime(inTimeData[3].toString());
							attendanceDetailModel.setInTimeStatus("O");
						}
						else
							if(Long.valueOf(inTimeData[0]) > 0 || Long.valueOf(inTimeData[1]) > 0 || Long.valueOf(inTimeData[2]) > 0)
						{
							attendanceDetailModel.setInLateTime(inTimeData[3].toString());
							attendanceDetailModel.setInTimeStatus("L");
						}
						else
						{
							attendanceDetailModel.setInLateTime(inTimeData[3]);
							attendanceDetailModel.setInTimeStatus("O");
						}
						attendanceDetailModel.setInTimeDescription((String)request.getParameter("remarks"));
						attendanceDetailModel.setInTime(inTime);
						attendanceDetailModel.setPlaceAt(placeAt);
						attendanceDetailModel.setOnLeave(onLeave);
						
						if(Long.valueOf(outTimeData[0]) > 0 || Long.valueOf(outTimeData[1]) > 0 || Long.valueOf(outTimeData[2]) > 0)
						{
							attendanceDetailModel.setOutLateTime(outTimeData[3]);
							attendanceDetailModel.setOutTimeStatus("L");
						}
						else
						{
							attendanceDetailModel.setOutLateTime(outTimeData[3]);
							attendanceDetailModel.setOutTimeStatus("B");
						}
						
						attendanceDetailModel.setRemarks(remarks);
						attendanceDetailModel.setOutTime(simpDate.format(date));
						attendanceDetailModel.setOutTime(outTime);
					}
					else
					{
						attendanceDetailModel.setInLateTime(null);
						attendanceDetailModel.setInTimeStatus(null);
						attendanceDetailModel.setInTimeDescription(null);
						attendanceDetailModel.setInTime(null);
						attendanceDetailModel.setPlaceAt(null);
						attendanceDetailModel.setOutTimeDescription(null);
						attendanceDetailModel.setOutTime(null);
						attendanceDetailModel.setOutLateTime(null);
						attendanceDetailModel.setOutTimeStatus(null);
						
						attendanceDetailModel.setOnLeave(onLeave);
					}
					attendanceDetailDao.updateAttendance(attendanceDetailModel);
					result = "Success";
				}
				
			}
			else if(request.getParameter("timeStatus").equals("in"))
			{
				String timeData[] = DateUtil.compareTime(employeeDate, time, IN_TIME);
				attendanceDetailModel = attendanceDetailDao.getAttendanceByDate(employeeModel.getEmployeeId(), DateUtil.stringToTimeStampWithTime2(sdfr.format(date)+TIME));
				if(attendanceDetailModel == null)
				{
					attendanceDetailModel = new AttendanceDetailModel();
					System.out.println(Long.valueOf(timeData[1]));
					if((Long.valueOf(timeData[1]) > -15 && Long.valueOf(timeData[1]) <= 0))
					{
						attendanceDetailModel.setInLateTime(timeData[3].toString());
						attendanceDetailModel.setInTimeStatus("O");
					}
					else if(Long.valueOf(timeData[0]) > 0 || Long.valueOf(timeData[1]) > 0 || Long.valueOf(timeData[2]) > 0)
					{
						attendanceDetailModel.setInLateTime(timeData[3].toString());
						attendanceDetailModel.setInTimeStatus("L");
					}
					else
					{
					attendanceDetailModel.setInLateTime(timeData[3]);
						attendanceDetailModel.setInTimeStatus("O");
					}
					if(employeeId != null && !"".equals(employeeId))
					{
						attendanceDetailModel.setEmployee(Integer.valueOf(employeeId));
					}
					else
					{

						attendanceDetailModel.setEmployee(employeeModel.getEmployeeId());
					}
					attendanceDetailModel.setAttendanceDate(Timestamp.valueOf(timeData[4]));
					attendanceDetailModel.setInTimeDescription((String)request.getParameter("remarks"));
					attendanceDetailModel.setInTime(simpDate.format(date));
					attendanceDetailModel.setOnLeave("Present");
					if(placeAt != null && !"".equals(placeAt))
					{
						attendanceDetailModel.setPlaceAt(placeAt);
					}
					else
					{
						attendanceDetailModel.setPlaceAt("office");
					}
					attendanceDetailModel.setRemarks(remarks);
					attendanceDetailDao.addAttendance(attendanceDetailModel);
					result = "Success";
				}
				else
				{
					result = "duplicate";
				}
			}
			else
			{
				
				attendanceDetailModel = attendanceDetailDao.getAttendanceByDate(employeeModel.getEmployeeId(), DateUtil.stringToTimeStampWithTime2(sdfr.format(date)+TIME));
				if(attendanceDetailModel != null)
				{
					String timeData[] = DateUtil.compareTime(employeeDate, time, OUT_TIME);
					
					if((Long.valueOf(timeData[1]) > -15 && Long.valueOf(timeData[1]) <= 15))
					{
						attendanceDetailModel.setOutLateTime(timeData[3]);
						attendanceDetailModel.setOutTimeStatus("O");
					}
					else if(Long.valueOf(timeData[0]) > 0 || Long.valueOf(timeData[1]) > 0 || Long.valueOf(timeData[2]) > 0)
					{
						attendanceDetailModel.setOutLateTime(timeData[3]);
						attendanceDetailModel.setOutTimeStatus("L");
					}
					else
					{
						attendanceDetailModel.setOutLateTime(timeData[3]);
						attendanceDetailModel.setOutTimeStatus("B");
					}
					
					attendanceDetailModel.setOutTimeDescription((String)request.getParameter("remarks"));
					attendanceDetailModel.setOutTime(simpDate.format(date));
					attendanceDetailModel.setOnLeave("Present");
					if(placeAt != null && !"".equals(placeAt))
					{
						attendanceDetailModel.setPlaceAt(placeAt);
					}
					else
					{
						attendanceDetailModel.setPlaceAt("office");
					}
					attendanceDetailModel.setRemarks(remarks);
					attendanceDetailDao.updateAttendance(attendanceDetailModel);
					result = "Success";
				}
				else
				{
					result = "inTimeMissing";
				}
			}
		
		} catch(Exception e) {
			result = "Error";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateAttendanceDetail(AttendanceDetailModel attendanceDetailModel) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void deleteAttendance(Integer attendanceDetailId) {
		
		try {
			attendanceDetailDao.deleteAttendance(attendanceDetailId);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AttendanceDetailModel getAttendanceDetailById(Integer attendanceDetailId) {
		AttendanceDetailModel attendanceDetailModel =  attendanceDetailDao.getAttendanceById(attendanceDetailId);
		try {
			if(attendanceDetailModel != null && attendanceDetailModel.getAttendanceDateString() != null)
			{
				DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromFormat.setLenient(false);
				DateFormat toFormat = new SimpleDateFormat("dd-MMM-yyyy");
				Date date = fromFormat.parse(attendanceDetailModel.getAttendanceDateString());
				attendanceDetailModel.setAttendanceDateString(toFormat.format(date));	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return attendanceDetailModel;
	}


	

}
