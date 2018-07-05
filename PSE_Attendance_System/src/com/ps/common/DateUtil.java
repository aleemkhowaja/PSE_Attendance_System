package com.ps.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	private static String pattern = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	static Integer HOURS;
	static Integer MINUTES;
	public static String getCurrenntDateInString() 
	{
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String[] compareTime(String employeeDate, String time, String actualTime) throws Exception
	{
		String arr[] = new String[5];
		//String IN_TIME = "09:30 am";
//		String OUT_TIME = "05:30 pm";
		final String TIME = " 00:00:00";
		SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
		
		Date d1 = null;
		Date d2 = null;
		Date date = new Date();
		SimpleDateFormat simpDate = new SimpleDateFormat("hh:mm a");
		d1 = simpDate.parse(actualTime);
		if(time != null && !"".equals(time))
		{
			d2 = simpDate.parse(time);
		}
		else
		{
			d2 = simpDate.parse(simpDate.format(date));
		}
		long diff = d2.getTime() - d1.getTime();

		Long diffSeconds = diff / 1000 % 60;
		Long diffMinutes = diff / (60 * 1000) % 60;
		Long diffHours = diff / (60 * 60 * 1000) % 24;
		
		String lateTimeAndDescription = diffHours > 0 ? "Hours = "+diffHours : "Hours = "+(diffHours);
		lateTimeAndDescription += " \n Minutes = "+diffMinutes;
		lateTimeAndDescription += "\n Seconds = "+diffSeconds;
		
		Timestamp attendDate ;
		if(employeeDate != null && !"".equals(employeeDate))
		{
			attendDate = DateUtil.stringToTimeStampWithTime2(employeeDate+TIME);
		}
		else
		{
			attendDate = DateUtil.stringToTimeStampWithTime2(sdfr.format(date)+TIME);
		}	
		arr[0] = String.valueOf(diffHours);
		arr[1] = String.valueOf(diffMinutes);
		arr[2] = String.valueOf(diffSeconds);
		arr[3] = String.valueOf(lateTimeAndDescription);
		arr[4] = String.valueOf(attendDate);
		return arr;
	}
	
	public static String stringToTimeStampWithTime(String strTime) 
	{
		String tempTimestamp = null;
		String date = "";
		if (strTime != null && !strTime.equals(""))
		{
			//SimpleDateFormat dateFormat = new SimpleDateFormat(
				//	"dd-MMM-yy hh:mm:ss");
			Date parsedTimeStamp = null;
			try {
				parsedTimeStamp=new SimpleDateFormat("dd/MM/yyyy").parse(strTime);  
				date = simpleDateFormat.format(parsedTimeStamp);
				System.out.println(date);
				//parsedTimeStamp = simpleDateFormat.parse(date);  
				//parsedTimeStamp = simpleDateFormat.parse(strTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//tempTimestamp =  Timestamp.valueOf(date);

		}
		return date;
	}
	
	
	public static Timestamp stringToTimeStampWithTime2(String strTime)
	{
		Timestamp tempTimestamp = null;
		if(strTime!=null && !strTime.equals(""))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(
		            "dd-MMM-yy hh:mm:ss");

		    Date parsedTimeStamp = null;
			try {
				parsedTimeStamp = dateFormat.parse(strTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tempTimestamp = new Timestamp(parsedTimeStamp.getTime());
		}
		return tempTimestamp;
    }
	
	public static String timeStampToString(Timestamp timeStamp)
	{
    	String timeStampToStr=null;
    	if(timeStamp!=null)
			timeStampToStr = new SimpleDateFormat("dd-MMM-yy").format(timeStamp);
		return timeStampToStr;
    }

	public static Timestamp stringToTimeStamp(String strTime)
	{
		Timestamp tempTimestamp = null;
		if(strTime!=null && !strTime.equals(""))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(
		            "dd-MMM-yy");

		    Date parsedTimeStamp = null;
			try {
				parsedTimeStamp = dateFormat.parse(strTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tempTimestamp = new Timestamp(parsedTimeStamp.getTime());
			
			
		}
		return tempTimestamp;
    }
	
	public static Date stringToDate(String strTime)
	{
		Timestamp tempTimestamp = null;
		Date parsedTimeStamp = null;
		if(strTime!=null && !strTime.equals(""))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(
		            "dd-MMM-yy");

		    
			try {
				parsedTimeStamp = dateFormat.parse(strTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return parsedTimeStamp;
    }
	
	public static int getTotalDaysByMonthAndYear(int year, int month)
	{
		int iYear = year;
		//int iMonth = Calendar.JUNE; // 1 (months begin with 0)
		int iDay = 1;
		// Create a calendar object and set year and month
		Calendar mycal = new GregorianCalendar(iYear, month, iDay);
		// Get the number of days in that month
		//int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
		
		// Get the number of days in that month
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth(); //28  
		
		return daysInMonth;
	}
	
	public static int getHolyDaysByMonthAndYear(int year, int month)
	{
		/*int iYear = year;
		int iMonth = getMonth(month); // 1 (months begin with 0)
		int iDay = 1;
		int holydays = 0;
		Calendar cal = new GregorianCalendar(iYear, iMonth, iDay);
		do 
		{
		    // get the day of the week for the current day
		    int day = cal.get(Calendar.DAY_OF_WEEK);
		    // check if it is a Saturday or Sunday
		    if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
		        // print the day - but you could add them to a list or whatever
		        ++holydays;
		    }
		    // advance to the next day
		    cal.add(Calendar.DAY_OF_YEAR, 1);
		}  while (cal.get(Calendar.MONTH) == iMonth);
		return holydays;*/
		
		Calendar calendar = Calendar.getInstance();
	    // Note that month is 0-based in calendar, bizarrely.
	    calendar.set(year, month - 1, 1);
	    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	    int count = 0;
	    for (int day = 1; day <= daysInMonth; day++) {
	        calendar.set(year, month - 1, day);
	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
	            count++;
	            // Or do whatever you need to with the result.
	        }
	    }
	    return count;
	}
	
	private static int getMonth(int month)
	{
		switch(month)
		{
		case 1:
			return Calendar.JANUARY;
		case 2:
			return Calendar.FEBRUARY;
		case 3:
			return Calendar.MARCH;
		case 4:
			return Calendar.APRIL;
		case 5:
			return Calendar.MAY;
		case 6:
			return Calendar.JUNE;
		case 7:
			return Calendar.JULY;
		case 8:
			return Calendar.AUGUST;
		case 9:
			return Calendar.SEPTEMBER;
		case 10:
			return Calendar.OCTOBER;
		case 11:
			return Calendar.NOVEMBER;
		case 12:
			return Calendar.DECEMBER;
		}
		
		return 0;
	}
	
	public static String getMonthName(int month)
	{
		switch(month)
		{
		case 1:
			return "JANUARY";
		case 2:
			return "FEBRUARY";
		case 3:
			return "MARCH";
		case 4:
			return "APRIL";
		case 5:
			return "MAY";
		case 6:
			return "JUNE";
		case 7:
			return "JULY";
		case 8:
			return "AUGUST";
		case 9:
			return "SEPTEMBER";
		case 10:
			return "OCTOBER";
		case 11:
			return "NOVEMBER";
		case 12:
			return "DECEMBER";
		}
		
		return "";
	}
	
	
}
