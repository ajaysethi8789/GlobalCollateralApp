package com.app.globalcollateralapp.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.app.globalcollateralapp.model.OilModel;
import com.app.globalcollateralapp.model.TransactionModel;

@Component
public class CommonUtils {

	final static Logger logger = Logger.getLogger(CommonUtils.class);

	public static List<OilModel> oilModelList = new ArrayList<OilModel>();
	public static List<TransactionModel> txModelList = new ArrayList<TransactionModel>();

	static {

		OilModel oilModel1 = new OilModel("AAC", "Standard", 1, 0, 42);
		OilModel oilModel2 = new OilModel("REW", "Standard", 7, 0, 47);
		OilModel oilModel3 = new OilModel("BWO", "Standard", 17, 0, 61);
		OilModel oilModel4 = new OilModel("TIM", "Premium", 5, 7, 111);
		OilModel oilModel5 = new OilModel("QFC", "Standard", 22, 0, 123);

		oilModelList.add(oilModel1);
		oilModelList.add(oilModel2);
		oilModelList.add(oilModel3);
		oilModelList.add(oilModel4);
		oilModelList.add(oilModel5);
	}

	public static List<OilModel> getOilModelList() {
		logger.info("CommonUtils:getOilModelList");
		return oilModelList;
	}

	public static double findNthRootOfSumPrice(float sumPrice, float N) {

		logger.info("CommonUtils:findNthRootOfSumPrice" + sumPrice + "N Val" + N);

		double xPre = Math.random() % 10;

		double eps = 0.001;
		double delX = 2147483647;
		double xK = 0.0;

		logger.info("CommonUtils:findNthRootOfSumPrice:While Loop Starting...");

		while (delX > eps) {
			xK = ((N - 1.0) * xPre + (double) sumPrice / Math.pow(xPre, N - 1)) / (double) N;
			delX = Math.abs(xK - xPre);
			xPre = xK;
		}
		return xK;
	}
}
