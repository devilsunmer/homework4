package model;

import service.impl.ProductServiceImpl;

public class OrderDataRow  {
	// forOrderData
	String orderNumber;
	String memberName;
	String memberAddress;
	String memberPhone;
	String productNumber;
	String productName;
	Integer productQuantity;
	Double orderProductPrice;
	String orderSum;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductName() {
		new ProductServiceImpl().takeProductName(productNumber);
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Double getOrderProductPrice() {
		return orderProductPrice;
	}
	public void setOrderProductPrice(Double orderProductPrice) {
		this.orderProductPrice = orderProductPrice;
	}
	public String getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}
	
	

}
