package com.ps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ps.dao.DepartmentDao;
import com.ps.model.DepartmentModel;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<DepartmentModel> returnAllDepartments() {
		List<DepartmentModel> departmentModels = this.jdbcTemplate.query("select * from department d",
		new RowMapper<DepartmentModel>() {
			public DepartmentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				DepartmentModel departmentModel = new DepartmentModel();
			    departmentModel.setDepartmentId(rs.getInt("d.department_id"));
			    departmentModel.setDepartmentName(rs.getString("d.department_name"));
			    return departmentModel;
			}
		});
		return departmentModels;
	}

}
