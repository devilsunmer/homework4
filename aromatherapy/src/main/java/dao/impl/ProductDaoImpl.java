package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import util.DbConnection;
import util.SQLTool;

public class ProductDaoImpl implements ProductDao {

	public static void main(String[] args) {
//		List<Product> productList=new ProductDaoImpl().all();
//		for(Product o:productList)
//		{
//			System.out.println(o.getProductNumber());
//		}

//		new ProductDaoImpl().delete("p003");

//		Product product=new Product();
//		product.setProductNumber("p002");
//		product.setProductName("薰衣草");
//		product.setCategory("花類");
//		product.setProdouctOverview("從老到小、普羅大眾廣泛熟知、同時也是大多群眾最能接受的香氣，仍是要注意少數可能會有過敏體質。");
//		product.setProductCost(1000);
//		product.setProductPrice(1300);
//		new ProductDaoImpl().update(product);

//		System.out.println(new ProductDaoImpl().idView("p001").getProdouctOverview());

//		System.out.println(new ProductDaoImpl().checkName("測試"));

//		List<Product> productList=new ProductDaoImpl().all();
//		for(Product o:productList)
//		{
//			System.out.println(o.getProductName());
//		}

//		new ProductDaoImpl().add(new Product("測試","測試","測試",200,500));

	}

	Connection connection = DbConnection.getDb();

	@Override
	public void add(Product product) {
		String sql = "insert into product(productNumber,productName,category,productOverview,productCost,productPrice) "
				+ "values(?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String number = SQLTool.NumberForSQL("product", "productNumber", "p", connection);
			preparedStatement.setString(1, number);
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3, product.getCategory());
			preparedStatement.setString(4, product.getProdouctOverview());
			preparedStatement.setInt(5, product.getProductCost());
			preparedStatement.setInt(6, product.getProductPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> all() {
		String sql = "select * from product";
		List<Product> productList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductNumber(resultSet.getString("productNumber"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setProdouctOverview(resultSet.getString("productOverview"));
				product.setProductCost(resultSet.getInt("productCost"));
				product.setProductPrice(resultSet.getInt("productPrice"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Product> number() {
		String sql = "select * from product";
		List<Product> productList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductNumber(resultSet.getString("productNumber"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Product idView(String productNumber) {
		String sql = "select * from product where productNumber=?";
		Product product = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setProductNumber(resultSet.getString("productNumber"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setProdouctOverview(resultSet.getString("productOverview"));
				product.setProductCost(resultSet.getInt("productCost"));
				product.setProductPrice(resultSet.getInt("productPrice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product checkName(String productName) {
		String sql = "select * from product where productName=?";
		Product product = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setProductNumber(resultSet.getString("productNumber"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setProdouctOverview(resultSet.getString("productOverview"));
				product.setProductCost(resultSet.getInt("productCost"));
				product.setProductPrice(resultSet.getInt("productPrice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	@Override
	public Product checkNumber(String productNumber) {
		String sql = "select * from product where productNumber=?";
		Product product = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setProductNumber(resultSet.getString("productNumber"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setProdouctOverview(resultSet.getString("productOverview"));
				product.setProductCost(resultSet.getInt("productCost"));
				product.setProductPrice(resultSet.getInt("productPrice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Integer cost(String productNumber) {
		String sql = "select productCost from product where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt("productCost");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer price(String productNumber) {
		String sql = "select productPrice from product where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt("productPrice");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Product update(Product product) {
		String sql = "update product set productName=?,category=?,productOverview=?,productCost=?,productPrice=? where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setString(3, product.getProdouctOverview());
			preparedStatement.setInt(4, product.getProductCost());
			preparedStatement.setInt(5, product.getProductPrice());
			preparedStatement.setString(6, product.getProductNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void costChange(String productNumber, Integer cost) {
		String sql = "update product set productCost=? where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cost);
			preparedStatement.setString(2, productNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void priceChange(String productNumber, Integer price) {
		String sql = "update product set productPrice=? where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, price);
			preparedStatement.setString(2, productNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String productNumber) {
		String sql = "delete from product where productNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
