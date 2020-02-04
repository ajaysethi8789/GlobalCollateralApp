package com.app.globalcollateralapp.model;

public class OilModel {

	private String oilId;
	private String type;
	private float fixedRevenue;
	private float variableRevenue;
	private float oilBarrelValue;

	public OilModel(String oilId, String type, float fixedRevenue, float variableRevenue, float oilBarrelValue) {
		super();
		this.oilId = oilId;
		this.type = type;
		this.fixedRevenue = fixedRevenue;
		this.variableRevenue = variableRevenue;
		this.oilBarrelValue = oilBarrelValue;
	}

	public String getOilId() {
		return oilId;
	}

	public void setOilId(String oilId) {
		this.oilId = oilId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getFixedRevenue() {
		return fixedRevenue;
	}

	public void setFixedRevenue(float fixedRevenue) {
		this.fixedRevenue = fixedRevenue;
	}

	public float getVariableRevenue() {
		return variableRevenue;
	}

	public void setVariableRevenue(float variableRevenue) {
		this.variableRevenue = variableRevenue;
	}

	public float getOilBarrelValue() {
		return oilBarrelValue;
	}

	public void setOilBarrelValue(float oilBarrelValue) {
		this.oilBarrelValue = oilBarrelValue;
	}

	@Override
	public String toString() {
		return "OilModel [oilId=" + oilId + ", type=" + type + ", fixedRevenue=" + fixedRevenue + ", variableRevenue="
				+ variableRevenue + ", oilBarrelValue=" + oilBarrelValue + "]";
	}

}
