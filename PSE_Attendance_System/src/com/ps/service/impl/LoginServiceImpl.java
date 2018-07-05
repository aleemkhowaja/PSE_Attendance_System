package com.ps.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.common.SecurityUtil;
import com.ps.dao.LoginDao;
import com.ps.dto.OnlineDTO;
import com.ps.model.EmployeeModel;
import com.ps.sevices.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public OnlineDTO login(String username, String password, HttpServletRequest request) {

		OnlineDTO dto = OnlineDTO.initialize();
		try {
			password = SecurityUtil.encodePassword(password);
			EmployeeModel employeeModel = loginDao.login(username, password);
			if (employeeModel != null) 
			{
				if(employeeModel.getIsActive())
				{
					HttpSession session = request.getSession(false);
					if (session != null) {
						session.invalidate();
					}
					session = request.getSession(true);
					session.setAttribute("employee", employeeModel);
					dto.setStatus(true);
					dto.setMessage("Wellcome");
					dto.setUrl("jsp/home.jsp");
				}
				else
				{
					dto.setStatus(false);
					dto.setMessage("Your Account is not Active Contact with Admin");
					dto.setUrl("index.jsp");
				}
			} else {
				dto.setStatus(false);
				dto.setMessage("Invalid Username or Password");
				dto.setUrl("index.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dto;
	}

	@Override
	public OnlineDTO logout(HttpServletRequest request) {
		OnlineDTO dto = OnlineDTO.initialize();
		try
		{
			HttpSession session = request.getSession(false);
			if (session != null ){
				session.invalidate();
				dto.setStatus(true);
				dto.setMessage("Logout");
				dto.setUrl("index.jsp");
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return dto;
	}

}
