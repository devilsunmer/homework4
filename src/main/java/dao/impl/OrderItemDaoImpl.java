package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderItemDao;
import model.OrderItem;
import util.DbConnection;

public class OrderItemDaoImpl implements OrderItemDao {

	public static void main(String[] args) {
//		new OrderItemDaoImpl().delete("od002");
		
//		new OrderItemDaoImpl().deleteOrderProduct("od001","p002");
		
//		OrderItem orderItem=new OrderItem();
//		orderItem.setOrderNumber("od001");
//		orderItem.setProductNumber("p001");
//		orderItem.setProductQuantity(9);
//		orderItem.setOrderProductPrice(3000.);
//		new OrderItemDaoImpl().update(orderItem);
		
//		List<OrderItem> orderList =new OrderItemDaoImpl().orderView("od002");
//		for(OrderItem o:orderList)
//			{
//				System.out.println(o.getProductNumber());
//			}
		
//		System.out.println(new OrderItemDaoImpl().checkOrder("od002"));
		
		List<OrderItem> orderList =new OrderItemDaoImpl().all();
		for(OrderItem o:orderList)
		{
			System.out.println(o.getOrderNumber()+"\t"+o.getProductNumber()+"\t"+o.getOrderProductPrice()+"\t"+o.getProductQuantity());
		}
		
//		new OrderItemDaoImpl().add(new OrderItem("od002","p002",20,1150.));
	}

	Connection connection = DbConnection.getDb();

	@Override
	public void add(OrderItem order) {
		String sql = "insert into order_item(orderNumber,productNumber,productQuantity,orderProductPrice) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String number=order.getOrderNumber()!=null ? order.getOrderNumber():"xo";
			preparedStatement.setString(1, number);
			preparedStatement.setString(2, order.getProductNumber());
			preparedStatement.setInt(3, order.getProductQuantity());
			preparedStatement.setDouble(4, order.getOrderProductPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> all() {
		String sql = "select * from order_item";
		List<OrderItem> orderList = new ArrayList<>();
		OrderItem order = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				order=new OrderItem();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setProductNumber(resultSet.getString("productNumber"));
				order.setProductQuantity(resultSet.getInt("productQuantity"));
				order.setOrderProductPrice(resultSet.getDouble("orderProductPrice"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<OrderItem> orderView(String orderNumber) {
		String sql = "select * from order_item where orderNumber=?";
		List<OrderItem> orderList = new ArrayList<>();
		OrderItem order = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				order=new OrderItem();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setProductNumber(resultSet.getString("productNumber"));
				order.setProductQuantity(resultSet.getInt("productQuantity"));
				order.setOrderProductPrice(resultSet.getDouble("orderProductPrice"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public Boolean checkOrder(String orderNumber) {
		String sql = "select * from order_item where orderNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Boolean checkOrderItem(String orderNumber,String productNumber) {
		String sql = "select * from order_item where orderNumber=? and productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNumber);
			preparedStatement.setString(2, productNumber);			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void update(OrderItem order) {
		String sql = "update order_item set productQuantity=?,orderProductPrice=? where orderNumber=? and productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, order.getProductQuantity());
			preparedStatement.setDouble(2, order.getOrderProductPrice());
			preparedStatement.setString(3, order.getOrderNumber());
			preparedStatement.setString(4, order.getProductNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String orderNumber) {
		String sql = "delete from order_item where orderNumber=?";
		if (checkOrder(orderNumber)) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, orderNumber);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteOrderProduct(String orderNumber, String productNumber) {
		String sql = "delete from order_item where orderNumber=? and productNumber=?";
		if (checkOrder(orderNumber)) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, orderNumber);
				preparedStatement.setString(2, productNumber);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
