package com.Model;

public class Product {
	private int productId;
	private String productName;
	private double productPrice;
	private int productQuantity;
	private boolean isReserved;
	private int customerId;
	private byte[] productImage;

	public Product(){

	}

	public Product(int productId, String productName, double productPrice, int productQuantity, boolean isReserved,
			int customerId, byte[] productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.isReserved = isReserved;
		this.customerId = customerId;
		this.productImage = productImage;
	}


	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public boolean isReserved() {
		return isReserved;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public byte[] getImagePath() {
		return productImage;
	}
	public void setImagePath(byte[] productImage) {
		this.productImage = productImage;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productQuantity=" + productQuantity + ", isReserved=" + isReserved + ", customerId=" + customerId
				+ "]";
	}
}