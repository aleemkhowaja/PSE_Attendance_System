package com.ps.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ps.dto.OnlineDTO;
import com.ps.model.AttendanceDetailModel;
import com.ps.sevices.LoginService;

@Controller
public class LoginController {
    
	@Autowired
	private LoginService loginService;
 
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value="/attendance/home.htm", method=RequestMethod.GET)
	public String returnInserAttendanceDetailPage(Model model) {
		return "home";
	}
	
	//Login Method
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public OnlineDTO login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		OnlineDTO dto = this.loginService.login(username, password, request);
		return dto;
	}
	
	/*@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public OnlineDTO logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		OnlineDTO dto = this.loginService.logout(request);
		return dto;
	}*/
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/index.jsp";
    }
}
