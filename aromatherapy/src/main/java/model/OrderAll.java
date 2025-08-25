package model;

import java.io.Serializable;
import java.sql.Date;

public class OrderAll  implements Serializable{
	private static final long serialVersionUID = 1L;
	String orderNumber;
	String memberNumber;
	Double orderSum;
	Date orderDate;

	public OrderAll() {
		super();
	}

	public OrderAll(String memberNumber, Double orderSum, Date orderDate) {
		super();
		this.memberNumber = memberNumber;
		this.orderSum=orderSum;
		this.orderDate = orderDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public Double getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(Double orderSum) {
		this.orderSum = orderSum;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
