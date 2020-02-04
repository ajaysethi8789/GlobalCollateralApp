package com.app.globalcollateralapp.model;

public class PriceEarningModel {

	private float priceEarningRatio;
	private String type;
	private String oilId;

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

	public float getPriceEarningRatio() {
		return priceEarningRatio;
	}

	public void setPriceEarningRatio(float priceEarningRatio) {
		this.priceEarningRatio = priceEarningRatio;
	}

	@Override
	public String toString() {
		return "PriceEarningModel [priceEarningRatio=" + priceEarningRatio + ", type=" + type + ", oilId=" + oilId
				+ "]";
	}

}
