package service;

import java.sql.Date;
import java.util.List;

import model.OrderAll;
import model.OrderItem;

public interface OrderAllService {
	// creat
	public void addOrder(OrderAll order,OrderItem item);
	public void addOrder(OrderAll order,List<OrderItem> item);

	// read
	public List<OrderAll> allOrder();
	public List<OrderAll> userOrderView(String memberNumber);
	public List<OrderAll> memberOrderView(String memberNumber);

	// update
	public Boolean update(OrderAll order);
	public Boolean updateOrderName(String orderNumber, String memberNumber);
	public Boolean updateOrderDate(String orderNumber, Date orderDate);

	// delete
	public Boolean deleteOrder(String orderNumber);
	List<OrderAll> orderNumberView(String orderNumber);

}
