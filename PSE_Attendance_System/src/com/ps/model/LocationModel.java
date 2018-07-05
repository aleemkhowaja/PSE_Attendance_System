package com.ps.model;

public class LocationModel extends Model{
	
	private Integer locationId;
	private String locationName;
	
	private LocationModel parentLocationModel;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public LocationModel getParentLocationModel() {
		return parentLocationModel;
	}

	public void setParentLocationModel(LocationModel parentLocationModel) {
		this.parentLocationModel = parentLocationModel;
	}
	

}
