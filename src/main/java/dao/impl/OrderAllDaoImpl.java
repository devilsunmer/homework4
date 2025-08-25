package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderAllDao;
import model.OrderAll;
import util.DbConnection;
import util.SQLTool;

public class OrderAllDaoImpl implements OrderAllDao {

	public static void main(String[] args) {
//		new OrderAllDaoImpl().delete("o001");
		
//		new OrderAllDaoImpl().updateDate("o001", Date.valueOf("2029-09-09"));
		
//		new OrderAllDaoImpl().updateName("o001", "m005");
		
//		OrderAll order=new OrderAll();
//		order.setOrderNumber("o001");
//		order.setMemberNumber("m003");
//		order.setOrderSum(9050.);
//		order.setOrderDate(Date.valueOf("2024-05-30"));
//		new OrderAllDaoImpl().update(order);
		
//		System.out.println(new OrderAllDaoImpl().checkOrder("o001"));
		
//		List<OrderAll> orderList =new OrderAllDaoImpl().userView("m002");
//		for(OrderAll o:orderList)
//		{
//			System.out.println(o.getOrderSum());
//		}
		
		List<OrderAll> orderList =new OrderAllDaoImpl().all();
		for(OrderAll o:orderList)
		{
			System.out.println(o.getOrderSum());
		}
		
//		new OrderAllDaoImpl().add(new OrderAll("m002",1900.,Date.valueOf("2025-08-01")));

	}

	Connection connection = DbConnection.getDb();

	@Override
	public void add(OrderAll order) {
		String sql = "insert into order_all(orderNumber,memberNumber,orderSum,orderDate) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String number = SQLTool.NumberForSQL("order_all", "orderNumber", "od", connection);
			preparedStatement.setString(1, number);
			preparedStatement.setString(2, order.getMemberNumber());
			preparedStatement.setDouble(3, order.getOrderSum());
			preparedStatement.setDate(4, order.getOrderDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderAll> all() {
		String sql = "select * from order_all";
		List<OrderAll> orderList = new ArrayList<>();
		OrderAll order = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order = new OrderAll();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setMemberNumber(resultSet.getString("memberNumber"));
				order.setOrderSum(resultSet.getDouble("orderSum"));
				order.setOrderDate(resultSet.getDate("orderDate"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<OrderAll> userView(String memberNumber) {
		String sql = "select * from order_all where memberNumber=?";
		List<OrderAll> orderList = new ArrayList<>();
		OrderAll order = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order = new OrderAll();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setMemberNumber(resultSet.getString("memberNumber"));
				order.setOrderSum(resultSet.getDouble("orderSum"));
				order.setOrderDate(resultSet.getDate("orderDate"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	@Override
	public List<OrderAll> orderView(String orderNumber) {
		String sql = "select * from order_all where orderNumber=?";
		List<OrderAll> orderList = new ArrayList<>();
		OrderAll order = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order = new OrderAll();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setMemberNumber(resultSet.getString("memberNumber"));
				order.setOrderSum(resultSet.getDouble("orderSum"));
				order.setOrderDate(resultSet.getDate("orderDate"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	@Override
	public List<OrderAll> memberView(String memberNumber) {
		String sql = "select * from order_all where memberNumber like ?";
		List<OrderAll> orderList = new ArrayList<>();
		OrderAll order = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String search=memberNumber+"%";
			preparedStatement.setString(1, search);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order = new OrderAll();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setMemberNumber(resultSet.getString("memberNumber"));
				order.setOrderSum(resultSet.getDouble("orderSum"));
				order.setOrderDate(resultSet.getDate("orderDate"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public OrderAll checkOrder(String orderNumber) {
		String sql = "select * from order_all where orderNumber=?";
		OrderAll order=null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				order = new OrderAll();
				order.setOrderNumber(resultSet.getString("orderNumber"));
				order.setMemberNumber(resultSet.getString("memberNumber"));
				order.setOrderSum(resultSet.getDouble("orderSum"));
				order.setOrderDate(resultSet.getDate("orderDate"));
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void update(OrderAll order) {
		String sql = "update order_all set memberNumber=?,orderSum=?,orderDate=? where orderNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getMemberNumber());
			preparedStatement.setDouble(2, order.getOrderSum());
			preparedStatement.setDate(3, order.getOrderDate());
			preparedStatement.setString(4, order.getOrderNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateName(String orderNumber, String memberNumber) {
		String sql = "update order_all set memberNumber=? where orderNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberNumber);
			preparedStatement.setString(2, orderNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDate(String orderNumber, Date orderDate) {
		String sql = "update order_all set orderDate=? where orderNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, orderDate);
			preparedStatement.setString(2, orderNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String orderNumber) {
		String sql = "delete from order_all where orderNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
