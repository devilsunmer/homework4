package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductSystemViewDao;
import model.ProductSystemView;
import util.DbConnection;

public class ProductSystemViewDaoImpl implements ProductSystemViewDao{

	public static void main(String[] args) {

	}

	public static Connection connection=DbConnection.getDb();
	
	@Override
	public ProductSystemView getByName(String name) {
		String sql="select * from product_system_view where name=?";
		ProductSystemView systemView=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				systemView=new ProductSystemView();
				systemView.setNumber(resultSet.getString("number"));
				systemView.setName(resultSet.getString("name"));
				systemView.setIn(resultSet.getInt("in"));
				systemView.setOut(resultSet.getInt("out"));
				systemView.setNowHave(resultSet.getInt("nowHave"));
			}
		} catch (SQLException e) {
			System.out.println("sql語法錯誤-psvName");
			e.printStackTrace();
		}
		return systemView;
	}

	@Override
	public List<ProductSystemView> getByAll() {
		String sql="select * from product_system_view";
		List<ProductSystemView> systemViewList=new ArrayList<>();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				ProductSystemView systemView=new ProductSystemView();
				systemView.setNumber(resultSet.getString("number"));
				systemView.setName(resultSet.getString("name"));
				systemView.setIn(resultSet.getInt("in"));
				systemView.setOut(resultSet.getInt("out"));
				systemView.setNowHave(resultSet.getInt("nowHave"));
				systemViewList.add(systemView);
			}
		} catch (SQLException e) {
			System.out.println("sql語法錯誤-psvAll");
			e.printStackTrace();
		}
		return systemViewList;
	}

	
}
