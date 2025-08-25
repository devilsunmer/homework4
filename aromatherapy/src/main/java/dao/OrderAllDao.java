package dao;

import java.sql.Date;
import java.util.List;

import model.OrderAll;

public interface OrderAllDao {
	// creat
	public void add(OrderAll order);

	// read
	public List<OrderAll> all();
	public List<OrderAll> userView(String memberNumber);
	public List<OrderAll> memberView(String memberNumber);
	public OrderAll checkOrder(String orderNumber);// true->有、false->沒

	// update
	public void update(OrderAll order);
	public void updateName(String orderNumber, String memberNumber);
	public void updateDate(String orderNumber, Date orderDate);
	
	// delete
	public void delete(String orderNumber);

	List<OrderAll> orderView(String orderNumber);

}
