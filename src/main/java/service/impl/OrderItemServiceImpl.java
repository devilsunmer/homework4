package service.impl;

import java.util.List;

import dao.impl.OrderItemDaoImpl;
import model.OrderItem;
import service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	public static void main(String[] args) {
//		new OrderItemServiceImpl().delete("od024");
	}

	public static OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();

	@Override
	public void addOrderItem(OrderItem order) {
		if (!orderItemDaoImpl.checkOrderItem(order.getOrderNumber(),order.getProductNumber())) {
			orderItemDaoImpl.add(order);
		} else {
			orderItemDaoImpl.update(order);
		}
	}

	@Override
	public List<OrderItem> allOrderItem() {
		return orderItemDaoImpl.all();
	}

	@Override
	public List<OrderItem> orderItemView(String orderNumber) {
		return orderItemDaoImpl.orderView(orderNumber);
	}

	@Override
	public Boolean update(OrderItem order) {
		if (orderItemDaoImpl.checkOrder(order.getOrderNumber())) {
			orderItemDaoImpl.update(order);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(String orderNumber) {
		if (orderItemDaoImpl.checkOrder(orderNumber)) {
			orderItemDaoImpl.delete(orderNumber);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteOrderProduct(String orderNumber, String productNumber) {
		if (orderItemDaoImpl.checkOrder(orderNumber)) {
			orderItemDaoImpl.deleteOrderProduct(orderNumber, productNumber);
			return true;
		}
		return false;
	}

}
