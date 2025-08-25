package model;

import java.io.Serializable;
import java.util.List;

public class OrderData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrderAll orderall;
	List<OrderItem> items;
	public OrderData() {
		super();
	}
	public OrderData(OrderAll orderall, List<OrderItem> items) {
		super();
		this.orderall = orderall;
		this.items = items;
	}
	public OrderAll getOrderall() {
		return orderall;
	}
	public void setOrderall(OrderAll orderall) {
		this.orderall = orderall;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	

}
