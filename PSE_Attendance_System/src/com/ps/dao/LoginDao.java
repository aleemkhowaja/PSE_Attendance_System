package com.ps.dao;

import com.ps.model.EmployeeModel;

public interface LoginDao {

	public EmployeeModel login(String username, String password);
}
