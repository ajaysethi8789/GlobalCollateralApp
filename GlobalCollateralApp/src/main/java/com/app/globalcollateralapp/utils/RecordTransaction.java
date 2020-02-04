package com.app.globalcollateralapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.app.globalcollateralapp.model.TransactionModel;

@Service
public class RecordTransaction {

	final static Logger logger = Logger.getLogger(RecordTransaction.class);

	public static List<TransactionModel> txModelList = new ArrayList<TransactionModel>();

	public static void addTransaction(String indicator, float quantity, float price) {
		logger.info("RecordTransaction==>AddTransaction");
		TransactionModel txMode = new TransactionModel(System.currentTimeMillis(), indicator, quantity, price);
		txModelList.add(txMode);
		logger.info("Number of Tx Recorded so far" + txModelList.size());
	}

	public static List<TransactionModel> getTransactionRecords() {
		return txModelList;
	}
}
