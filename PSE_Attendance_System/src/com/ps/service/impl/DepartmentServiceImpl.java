package com.ps.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.common.JTableCombo;
import com.ps.common.JTableList;
import com.ps.dao.DepartmentDao;
import com.ps.model.DepartmentModel;
import com.ps.model.LocationModel;
import com.ps.sevices.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Transactional
	@Override
	public List<DepartmentModel> returnAllDepartments() {
		
		List<DepartmentModel> departmentModels = null;
		try {
			departmentModels = departmentDao.returnAllDepartments();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return departmentModels;
		
	}
	
	/*@Transactional
	@Override
	public JTableList<JTableCombo> returnAllDepartments() {
		
		JTableList<JTableCombo> jTableList = new JTableList<JTableCombo>();
		
		List<DepartmentModel> departmentModels = null;
		List<JTableCombo> jTableCombos = new ArrayList<JTableCombo>();
		JTableCombo jTableCombo = new JTableCombo();
		try {
			departmentModels = departmentDao.returnAllDepartments();
			if(departmentModels != null){
				jTableCombo.setDisplayText("Select Department");
				jTableCombo.setValue("");
				jTableCombos.add(jTableCombo);
				for(int i=0; i<departmentModels.size(); i++) {
					jTableCombo = new JTableCombo();
					jTableCombo.setDisplayText(departmentModels.get(i).getDepartmentName());
					jTableCombo.setValue(""+departmentModels.get(i).getDepartmentId());
					jTableCombos.add(jTableCombo);
				}
			} else {
				
				jTableCombo.setDisplayText("No Item Found");
				jTableCombo.setValue("");
			}
			
			jTableList.setOptions(jTableCombos);
			jTableList.setResult("OK");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jTableList;
		
	} */
	

}
