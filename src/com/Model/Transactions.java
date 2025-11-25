package com.Model;

public class Transactions {
	private int transactionId;
	private double totalAmount;
	private int noOfItems;
	private int customerId;
	private int productId;

	public Transactions() {

	}

	public Transactions(int transactionId, double totalAmount, int noOfItems, int customerId,
			int productId) {
		super();
		this.transactionId = transactionId;
		this.totalAmount = totalAmount;
		this.noOfItems = noOfItems;
		this.customerId = customerId;
		this.productId = productId;
	}

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}


	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", totalAmount=" + totalAmount + ", noOfItems="
				+ noOfItems + ", customerId=" + customerId + ", productId=" + productId + "]";
	}
}