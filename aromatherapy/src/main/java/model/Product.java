package model;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String productNumber;
	String productName;
	String category;
	String prodouctOverview;// 產品介紹大綱
	Integer productCost;// 產品成本價格
	Integer productPrice;// 產品販售價格

	public Product() {
		super();
	}

	public Product(String productName, String category, String prodouctOverview,
			Integer productCost, Integer productPrice) {
		super();
		this.productName = productName;
		this.category = category;
		this.prodouctOverview = prodouctOverview;
		this.productCost = productCost;
		this.productPrice = productPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProdouctOverview() {
		return prodouctOverview;
	}

	public void setProdouctOverview(String prodouctOverview) {
		this.prodouctOverview = prodouctOverview;
	}

	public Integer getProductCost() {
		return productCost;
	}

	public void setProductCost(Integer productCost) {
		this.productCost = productCost;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

}
