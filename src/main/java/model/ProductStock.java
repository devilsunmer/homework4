package model;

import java.io.Serializable;
import java.sql.Date;

public class ProductStock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String productNumber;
	Integer productInStock;
	Integer productOutStock;
	Date productStockDate;

	public ProductStock() {
		super();
	}

	public ProductStock(String productNumber, Integer productInStock, Integer productOutStock, Date productStockDate) {
		super();
		this.productNumber = productNumber;
		this.productInStock = productInStock;
		this.productOutStock = productOutStock;
		this.productStockDate = productStockDate;
	}

	public Date getProductStockDate() {
		return productStockDate;
	}

	public void setProductStockDate(Date productStockDate) {
		this.productStockDate = productStockDate;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Integer getProductInStock() {
		return productInStock;
	}

	public void setProductInStock(Integer productInStock) {
		this.productInStock = productInStock;
	}

	public Integer getProductOutStock() {
		return productOutStock;
	}

	public void setProductOutStock(Integer productOutStock) {
		this.productOutStock = productOutStock;
	}

}
