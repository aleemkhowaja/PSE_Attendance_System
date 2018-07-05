package com.ps.sevices;

import javax.servlet.http.HttpServletRequest;

import com.ps.dto.OnlineDTO;

public interface LoginService {
	
	public OnlineDTO login(String username, String password, HttpServletRequest  request);
	
	public OnlineDTO logout(HttpServletRequest  request);

}
