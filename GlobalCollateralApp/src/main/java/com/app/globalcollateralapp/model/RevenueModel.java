package com.app.globalcollateralapp.model;

public class RevenueModel {

	private float revenue;
	private String type;
	private String oilId;

	public float getRevenue() {
		return revenue;
	}

	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOilId() {
		return oilId;
	}

	public void setOilId(String oilId) {
		this.oilId = oilId;
	}

	@Override
	public String toString() {
		return "RevenueModel [revenue=" + revenue + ", type=" + type + ", oilId=" + oilId + "]";
	}
}
