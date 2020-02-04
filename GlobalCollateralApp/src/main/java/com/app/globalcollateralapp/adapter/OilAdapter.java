package com.app.globalcollateralapp.adapter;

import java.util.List;

import org.json.simple.JSONObject;

import com.app.globalcollateralapp.model.PriceEarningModel;
import com.app.globalcollateralapp.model.RevenueModel;

public interface OilAdapter {

	public List<RevenueModel> getRevenueYield(float price);

	public List<PriceEarningModel> getPriceEarningRatio(float price);

	public JSONObject getWeightedOilPriceForPast30Min();

	public JSONObject getGeometricMean();
}
