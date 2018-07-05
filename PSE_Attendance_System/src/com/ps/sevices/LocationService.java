package com.ps.sevices;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.ps.common.JTableCombo;
import com.ps.common.JTableList;
import com.ps.model.LocationModel;

public interface LocationService {
	
	//public JTableList<JTableCombo> returnAllLocations();
	public List<LocationModel> returnAllLocations();
}
