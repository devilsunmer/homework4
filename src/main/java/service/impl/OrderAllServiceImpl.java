package service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.impl.OrderAllDaoImpl;
import model.OrderAll;
import model.OrderItem;
import service.OrderAllService;

public class OrderAllServiceImpl implements OrderAllService {

	public static void main(String[] args) {
		System.out.println(new OrderAllServiceImpl().deleteOrder("od025"));
		
//		OrderAll orderAll = new OrderAll("m002", 123000., Date.valueOf("2025-07-03"));
//		List<OrderItem> orderItem=new ArrayList<>();
//		OrderItem item = new OrderItem("p002", 4, 2600.);
//		OrderItem item2 = new OrderItem("p005", 1, 4800.);
//		OrderItem item3 = new OrderItem("p006", 1, 1000.);
//		orderItem.add(item);
//		orderItem.add(item2);
//		orderItem.add(item3);
//		new OrderAllServiceImpl().addOrder(orderAll, orderItem);
		
//		OrderAll orderAll = new OrderAll("m002", 7850., Date.valueOf("2025-07-03"));
//		OrderItem orderItem=new OrderItem("p003", 4, 1200.);
//		new OrderAllServiceImpl().addOrder(orderAll, orderItem);
		
//		System.out.println(new OrderAllServiceImpl().toString());
	}

	public static OrderAllDaoImpl orderAllDaoImpl = new OrderAllDaoImpl();
	public static OrderItemServiceImpl orderItemServiceImpl = new OrderItemServiceImpl();

	@Override
	public void addOrder(OrderAll order, OrderItem item) {
		if (orderAllDaoImpl.checkOrder(order.getOrderNumber())==null) {
			orderAllDaoImpl.add(order);
		}
		List<OrderAll> view=orderAllDaoImpl.userView(order.getMemberNumber());
		String number = null;
		for(OrderAll o:view)
		{
			number=o.getOrderNumber();
		}
		item.setOrderNumber(number);
		orderItemServiceImpl.addOrderItem(item);
	}

	@Override
	public void addOrder(OrderAll order, List<OrderItem> item) {
		if (orderAllDaoImpl.checkOrder(order.getOrderNumber())==null) {
			orderAllDaoImpl.add(order);
		}
		List<OrderAll> view=orderAllDaoImpl.userView(order.getMemberNumber());
		String number = null;
		for(OrderAll o:view)
		{
			number=o.getOrderNumber();
		}
		for (OrderItem o : item) {
			o.setOrderNumber(number);
			orderItemServiceImpl.addOrderItem(o);
		}
	}

	@Override
	public List<OrderAll> allOrder() {
		return orderAllDaoImpl.all();
	}

	@Override
	public List<OrderAll> userOrderView(String memberNumber) {
		return orderAllDaoImpl.userView(memberNumber);
	}


	@Override
	public List<OrderAll> orderNumberView(String orderNumber) {
		return orderAllDaoImpl.orderView(orderNumber);
	}
	
	@Override
	public List<OrderAll> memberOrderView(String memberNumber) {
		return orderAllDaoImpl.memberView(memberNumber);
	}

	@Override
	public Boolean update(OrderAll order) {
		if (orderAllDaoImpl.checkOrder(order.getOrderNumber())!=null) {
			orderAllDaoImpl.update(order);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOrderName(String orderNumber, String memberNumber) {
		if (orderAllDaoImpl.checkOrder(orderNumber)!=null) {
			orderAllDaoImpl.updateName(orderNumber, memberNumber);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOrderDate(String orderNumber, Date orderDate) {
		if (orderAllDaoImpl.checkOrder(orderNumber)!=null) {
			orderAllDaoImpl.updateDate(orderNumber, orderDate);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteOrder(String orderNumber) {
		if (orderAllDaoImpl.checkOrder(orderNumber)!=null) {
			orderAllDaoImpl.delete(orderNumber);
			orderItemServiceImpl.delete(orderNumber);
			return true;
		}
		return false;
	}

}
