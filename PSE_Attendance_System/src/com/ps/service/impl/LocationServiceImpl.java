package com.ps.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.common.JTableCombo;
import com.ps.common.JTableList;
import com.ps.dao.LocationDao;
import com.ps.model.EmployeeModel;
import com.ps.model.LocationModel;
import com.ps.sevices.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Transactional
	@Override
	public List<LocationModel> returnAllLocations() {
		
		List<LocationModel> locationModels = null;
		try {
			locationModels = locationDao.returnAllLocations();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return locationModels;
	}
	
	/*@Transactional
	@Override
	public JTableList<JTableCombo> returnAllLocations() {
		
		JTableList<JTableCombo> jTableList = new JTableList<JTableCombo>();
		
		List<LocationModel> locationModels = null;
		List<JTableCombo> jTableCombos = null;
		try {
			locationModels = locationDao.returnAllLocations();
			JTableCombo jTableCombo = new JTableCombo();
			if(locationModels != null){
				jTableCombo.setDisplayText("Select Location");
				jTableCombo.setValue("");
				jTableCombos = new ArrayList<JTableCombo>();
				jTableCombos.add(jTableCombo);
				for(int i=0; i<locationModels.size(); i++) {
					jTableCombo = new JTableCombo();
					jTableCombo.setDisplayText(locationModels.get(i).getLocationName());
					jTableCombo.setValue(""+locationModels.get(i).getLocationId());
					jTableCombos.add(jTableCombo);
				}
			} else {
				jTableCombo.setDisplayText("No Item Found");
				jTableCombo.setValue("");
				jTableCombos.add(jTableCombo);
			}
			
			jTableList.setOptions(jTableCombos);
			jTableList.setResult("OK");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jTableList;
	}*/

}
