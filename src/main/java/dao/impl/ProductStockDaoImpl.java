package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductStockDao;
import model.ProductStock;
import util.DbConnection;

public class ProductStockDaoImpl implements ProductStockDao {

	public static void main(String[] args) {
//		new ProductStockDaoImpl().delete("01");

//		new ProductStockDaoImpl().update(new ProductStock("01",900,900));

//		new ProductStockDaoImpl().outChange("01", 3000);

//		new ProductStockDaoImpl().inChange("01", 400);

//		System.out.println(new ProductStockDaoImpl().out("01"));

//		System.out.println(new ProductStockDaoImpl().in("01"));

//		System.out.println(new ProductStockDaoImpl().check("00"));

//		List<ProductStock> stockList=new ProductStockDaoImpl().all();
//		for(ProductStock o:stockList)
//		{
//			System.out.println(o.getProductNumber());
//		}

//		new ProductStockDaoImpl().add(new ProductStock("01",100,100));
	}

	Connection connection = DbConnection.getDb();

	@Override
	public void add(ProductStock productStock) {
		String sql = "insert into product_stock(productNumber,productInStock,productOutStock,productStockDate) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productStock.getProductNumber());
			preparedStatement.setInt(2, productStock.getProductInStock());
			preparedStatement.setInt(3, productStock.getProductOutStock());
			preparedStatement.setDate(4, productStock.getProductStockDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductStock> all() {
		String sql = "select * from product_stock";
		List<ProductStock> stockList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductStock productStock = new ProductStock();
				productStock.setProductNumber(resultSet.getString("productNumber"));
				productStock.setProductInStock(resultSet.getInt("productInStock"));
				productStock.setProductOutStock(resultSet.getInt("productOutStock"));
				productStock.setProductStockDate(resultSet.getDate("productStockDate"));
				stockList.add(productStock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}

	@Override
	public ProductStock check(String productNumber) {
		String sql = "select * from product_stock where productNumber=?";
		ProductStock productStock = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				productStock = new ProductStock();
				productStock.setProductNumber(resultSet.getString("productNumber"));
				productStock.setProductInStock(resultSet.getInt("productInStock"));
				productStock.setProductOutStock(resultSet.getInt("productOutStock"));
				productStock.setProductStockDate(resultSet.getDate("productStockDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productStock;
	}

	@Override
	public Integer in(String productNumber) {
		String sql = "select productInStock from product_stock where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt("productInStock");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer out(String productNumber) {
		String sql = "select productOutStock from product_stock where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt("productOutStock");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(ProductStock productStock) {
		String sql = "update product_stock set productInStock=?,productOutStock=?,productStockDate=? where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productStock.getProductInStock());
			preparedStatement.setInt(2, productStock.getProductOutStock());
			preparedStatement.setDate(3, productStock.getProductStockDate());
			preparedStatement.setString(4, productStock.getProductNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inChange(String productNumber, Integer in) {
		String sql = "update product_stock set productInStock=? where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, in);
			preparedStatement.setString(2, productNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void outChange(String productNumber, Integer out) {
		String sql = "update product_stock set productOutStock=? where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, out);
			preparedStatement.setString(2, productNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String productNumber) {
		String sql = "delete from product_stock where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
