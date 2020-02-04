package com.app.globalcollateralapp.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.app.globalcollateralapp.model.PriceEarningModel;
import com.app.globalcollateralapp.model.RevenueModel;
import com.app.globalcollateralapp.service.OilService;

@RestController
public class OilRestController {

	@Autowired
	private OilService oilService;

	@GetMapping("/api/getReveneue/{price}")
	public List<RevenueModel> getRevenueYield(@PathVariable String price) {
		return oilService.getRevenueYield(Float.parseFloat(price));
	}

	@GetMapping("/api/getPriceEarningRatio/{price}")
	public List<PriceEarningModel> getPriceEarningRatio(@PathVariable String price) {
		return oilService.getPriceEarningRatio(Float.parseFloat(price));
	}

	@GetMapping("/api/calVolumeWeightedOilPrice")
	public JSONObject getWeightedOilPriceForPast30Min() {
		return oilService.getWeightedOilPriceForPast30Min();
	}

	@GetMapping("/api/calInventoryIndex")
	public JSONObject calInventoryIndexUsingGeometricMean() {
		return oilService.getGeometricMean();
	}
}
