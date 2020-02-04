package com.app.globalcollateralapp.model;

public class TransactionModel {

	private long timestamp;
	private String indicator;
	private float quantity;
	private float price;

	public TransactionModel(long timestamp, String indicator, float quantity, float price) {
		super();
		this.timestamp = timestamp;
		this.indicator = indicator;
		this.quantity = quantity;
		this.price = price;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TransactionModel [timestamp=" + timestamp + ", indicator=" + indicator + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

}
