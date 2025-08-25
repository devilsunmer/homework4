package service;

import java.util.List;

import model.OrderItem;

public interface OrderItemService {
	// creat
	public void addOrderItem(OrderItem order);

	// read
	public List<OrderItem> allOrderItem();
	public List<OrderItem> orderItemView(String orderNumber);

	// update
	public Boolean update(OrderItem order);

	// delete
	public Boolean delete(String orderNumber);
	public Boolean deleteOrderProduct(String orderNumber, String productNumber);

}
