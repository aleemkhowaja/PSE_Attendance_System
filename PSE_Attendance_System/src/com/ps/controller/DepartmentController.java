package com.ps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ps.common.JTableCombo;
import com.ps.common.JTableList;
import com.ps.model.DepartmentModel;
import com.ps.model.LocationModel;
import com.ps.sevices.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	/*@RequestMapping(value="/returnAllDepartments", produces = "application/json")
	@ResponseBody
	public JTableList<JTableCombo> returnAllDepartments() {
		return departmentService.returnAllDepartments();
	}*/
	
	
	
}
