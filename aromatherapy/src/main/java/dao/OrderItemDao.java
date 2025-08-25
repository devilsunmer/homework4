package dao;

import java.util.List;

import model.OrderItem;

public interface OrderItemDao {
	// creat
		public void add(OrderItem order);

		// read
		public List<OrderItem> all();
		public List<OrderItem> orderView(String orderNumber);
		public Boolean checkOrder(String orderNumber);// true->有、false->沒
		public Boolean checkOrderItem(String orderNumber, String productNumber);

		// update
		public void update(OrderItem order);
		
		// delete
		public void delete(String orderNumber);
		public void deleteOrderProduct(String orderNumber, String productNumber);
}
