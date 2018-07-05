package com.ps.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.dao.AttendanceReportDao;
import com.ps.model.EmployeeModel;
import com.ps.sevices.AttendanceReportService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class AttendanceReportServiceImpl implements AttendanceReportService {

	@Autowired
	private AttendanceReportDao  attendanceReportDao;
	
	@Override
	public byte[] getPdfReport(HashMap<String, Object> jasperParameter,
			String reportName, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		String serverPath = request.getServletContext().getRealPath("");
		byte[] pdfData = null;
		OutputStream output = null;
		int employeeId = 0;
		long time = System.currentTimeMillis();
		try {
			
			EmployeeModel employeeModel = (EmployeeModel)request.getSession().getAttribute("employee");
			
			if(employeeModel != null){
				employeeId = employeeModel.getEmployeeId();
			}			
			Connection connection = attendanceReportDao.getConnection();
			InputStream inputStream = new FileInputStream(serverPath + "/reports/"+reportName);
			
			// filling report with data from data source
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
			pdfData = JasperExportManager.exportReportToPdf(jasperPrint);
			// Initialize response.
			response.reset();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition","attachment; filename=\""+reportName.replace("Report.jasper", "_")+ employeeId+"_"+time +".pdf\"");
			// Write file to response.
			output = response.getOutputStream();
			output.write(pdfData);
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfData;
	}

	
	/**
	 * @return the attendanceReportDao
	 */
	public AttendanceReportDao getAttendanceReportDao() {
		return attendanceReportDao;
	}

	/**
	 * @param attendanceReportDao the attendanceReportDao to set
	 */
	public void setAttendanceReportDao(AttendanceReportDao attendanceReportDao) {
		this.attendanceReportDao = attendanceReportDao;
	}

}
