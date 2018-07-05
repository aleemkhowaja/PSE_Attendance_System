package com.ps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ps.dao.LocationDao;
import com.ps.model.LocationModel;

@Repository
public class LocationDaoImpl implements LocationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<LocationModel> returnAllLocations() {
		List<LocationModel> locationModels = this.jdbcTemplate.query(
		        "select * from location l",
		        new RowMapper<LocationModel>() {
		            public LocationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	LocationModel locationModel = new LocationModel();
		            	locationModel.setLocationId(rs.getInt("l.location_id"));
		            	locationModel.setLocationName(rs.getString("l.location_name"));
		                return locationModel;
		            }
		        });
		return locationModels;
	}

}
