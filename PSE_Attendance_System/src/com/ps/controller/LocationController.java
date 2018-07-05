package com.ps.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ps.common.JTableCombo;
import com.ps.common.JTableList;
import com.ps.model.EmployeeModel;
import com.ps.model.LocationModel;
import com.ps.sevices.LocationService;

@Controller
public class LocationController {
	
	
	@Autowired
	private LocationService locationService;

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

/*	@RequestMapping(value="//returnAllLocations", produces = "application/json")
	@ResponseBody
	public JTableList<JTableCombo> returnAllLocation() {
		return locationService.returnAllLocations();
	}*/

}
