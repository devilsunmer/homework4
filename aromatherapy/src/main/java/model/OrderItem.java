package model;

import java.io.Serializable;

public class OrderItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String orderNumber;
	String productNumber;
	Integer productQuantity;
	Double orderProductPrice;
	
	public OrderItem() {
		super();
	}

	public OrderItem(String productNumber, Integer productQuantity, Double orderProductPrice) {
		super();
		this.productNumber = productNumber;
		this.productQuantity = productQuantity;
		this.orderProductPrice = orderProductPrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
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
	
}
