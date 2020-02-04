package com.app.globalcollateralapp.service;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.globalcollateralapp.adapter.OilAdapter;
import com.app.globalcollateralapp.model.OilModel;
import com.app.globalcollateralapp.model.PriceEarningModel;
import com.app.globalcollateralapp.model.RevenueModel;
import com.app.globalcollateralapp.model.TransactionModel;
import com.app.globalcollateralapp.utils.CommonUtils;
import com.app.globalcollateralapp.utils.RecordTransaction;

@Service
public class OilService implements OilAdapter {

	final static Logger logger = Logger.getLogger(OilService.class);

	private static final int THIRTY_MINTUES = 30 * 60 * 1000;

	@Autowired
	static

	CommonUtils commonUtils;

	static int counter = 0;

	static float sumPriceQuan = 0;

	static float sumQuantity = 0;

	static float sumPrice = 1;

	public static List<OilModel> oilModelList = CommonUtils.getOilModelList();
	List<RevenueModel> revenueModelArrList = new ArrayList<RevenueModel>();
	List<PriceEarningModel> priceEarningList = new ArrayList<PriceEarningModel>();

	public List<RevenueModel> getRevenueYield(float price) {
		logger.info("getRevenueYield Service Class");
		List<RevenueModel> revenueModelList = new ArrayList<RevenueModel>();
		logger.info("getRevenueYield==>oilModelList" + oilModelList.size());
		oilModelList.forEach(oilModel -> {
			RevenueModel revenueModel = new RevenueModel();
			if (oilModel.getType().equals("Standard")) {
				revenueModel.setRevenue(oilModel.getFixedRevenue() / price);
				revenueModel.setOilId(oilModel.getOilId());
				revenueModel.setType(oilModel.getType());
			} else {
				revenueModel.setRevenue((oilModel.getVariableRevenue() * oilModel.getOilBarrelValue()) / price);
				revenueModel.setOilId(oilModel.getOilId());
				revenueModel.setType(oilModel.getType());
			}
			revenueModelList.add(revenueModel);
		});
		RecordTransaction.addTransaction("Buy", 5, price);
		return revenueModelList;
	}

	public List<PriceEarningModel> getPriceEarningRatio(float price) {
		logger.info("getPriceEarningRatio Service Class");
		logger.info("getPriceEarningRatio==>oilModelList" + oilModelList.size());

		revenueModelArrList = getRevenueYield(price);
		logger.info("getPriceEarningRatio==>revenueModelList size" + revenueModelArrList.size());

		oilModelList.forEach(oilModel -> {
			PriceEarningModel revenueModel = new PriceEarningModel();
			if (counter < oilModelList.size()) {
				revenueModel.setPriceEarningRatio(price / revenueModelArrList.get(counter++).getRevenue());
				revenueModel.setOilId(oilModel.getOilId());
				revenueModel.setType(oilModel.getType());
				priceEarningList.add(revenueModel);
			}
		});
		return priceEarningList;
	}

	public JSONObject getWeightedOilPriceForPast30Min() {

		logger.info("OilService:getWeightedOilPriceForPast30Min");
		JSONObject volumeWeightedOilPriceJson = new JSONObject();

		List<TransactionModel> txModel = RecordTransaction.getTransactionRecords();
		logger.info("OilService:getWeightedOilPriceForPast30Min:txModel.size" + txModel.size());
		long thirtyAgo = System.currentTimeMillis() - THIRTY_MINTUES;
		txModel.forEach(tx -> {
			if (tx.getTimestamp() > thirtyAgo) {
				logger.info("OilService:getWeightedOilPriceForPast30Min:Inside30Minutes");

				sumPriceQuan += tx.getPrice() * tx.getQuantity();
				sumQuantity += tx.getQuantity();
			}
		});
		logger.info("OilService:getWeightedOilPriceForPast30Min:SumPriceQuantity" + sumPriceQuan);
		logger.info("OilService:getWeightedOilPriceForPast30Min:sumQuantity" + sumQuantity);

		volumeWeightedOilPriceJson.put("VolumeWeightedOilPrice", sumPriceQuan / sumQuantity);

		return volumeWeightedOilPriceJson;
	}

	public JSONObject getGeometricMean() {

		logger.info("OilService:getGeometricMean");
		JSONObject geometricMean = new JSONObject();

		List<TransactionModel> txModel = RecordTransaction.getTransactionRecords();
		logger.info("OilService:getGeometricMean:txModel.size" + txModel.size());
		txModel.forEach(tx -> {
			logger.info("OilService:getGeometricMean:price" + tx.getPrice());
			sumPrice *= tx.getPrice();
			logger.info("OilService:getGeometricMean:price" + sumPrice);
		});
		double geometricMeanVal = CommonUtils.findNthRootOfSumPrice(sumPrice, txModel.size());

		geometricMean.put("GeometricMeanValue", Math.round(geometricMeanVal * 1000.0) / 1000.0);

		return geometricMean;
	}

}
