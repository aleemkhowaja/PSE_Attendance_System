package com.ps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.common.JTableList;
import com.ps.model.HolidayModel;
import com.ps.sevices.HolidayService;

@RestController
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
	private List<HolidayModel> holidayModels = new ArrayList<HolidayModel>();
	
	
	//start get all holidays for search screen
	@RequestMapping(value="/holiday/returnAllHolidaysForGrid", produces = "application/json")
	@ResponseBody
	public JTableList<HolidayModel> returnAllHolidaysForGrid(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return holidayService.returnAllHolidaysForGrid(request);
	}
	//end get all holidays for search screen
	
	//start add holidays
	@RequestMapping(value="/holiday/getHoliday", method = RequestMethod.POST)
	@ResponseBody
	public HolidayModel getHolidayById(@RequestParam("holidayId") Integer holidayId, Model model) {
		HolidayModel holidayModel = holidayService.getHolidayById(holidayId);
		return holidayModel;
	}
	//end add holiday
		
	//start add holidays
	@RequestMapping(value="/holiday/save.htm", method = RequestMethod.POST)
	@ResponseBody
	public String addHoliday(@RequestBody HolidayModel holidayModel, HttpServletRequest request) {
		holidayService.addHoliday(holidayModel, holidayModel.MODE);
		return "Success";
	}
	//end add holiday
	
	//start update holidays
	@RequestMapping(value="/holiday/updateHoliday", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public JTableList updateHoliday(@ModelAttribute("holidayData") HolidayModel holidayModel, HttpServletRequest request) {
		JTableList jTableList = holidayService.addHoliday(holidayModel, "Update");
		return jTableList;
	}
	//end update holiday
	
	//start update holidays
	@RequestMapping(value="/holiday/deleteHoliday")
	@ResponseBody
	public String deleteHoliday(@RequestParam("holidayId")Integer holidayId) {
		holidayService.deleteHoliday(holidayId);
		return "Success";
	}
	//end update holiday
	
	//start clear holidays
	@RequestMapping(value="/holiday/clear", method = RequestMethod.GET)
	public String clearHolidayForm(Model model, HttpServletRequest request) {
		
		com.ps.model.Model.MODE = "Add";
		model.addAttribute("holidayData", new HolidayModel());
		return "search-holiday";
	}
	//end add holiday
	
	//	Getter and Setter Methods
	public HolidayService getHolidayService() {
		return holidayService;
	}
	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}
}
