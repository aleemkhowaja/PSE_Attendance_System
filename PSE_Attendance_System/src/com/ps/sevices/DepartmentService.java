package com.ps.sevices;

import java.util.List;

import com.ps.common.JTableCombo;
import com.ps.common.JTableList;
import com.ps.model.DepartmentModel;

public interface DepartmentService {
	
//	public JTableList<JTableCombo> returnAllDepartments();
	public  List<DepartmentModel>  returnAllDepartments();

}
