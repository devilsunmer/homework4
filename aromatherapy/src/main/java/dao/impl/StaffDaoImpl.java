package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StaffDao;
import model.Member;
import model.Staff;
import util.DbConnection;
import util.SQLTool;

public class StaffDaoImpl implements StaffDao {

	public static void main(String[] args) {
//		new StaffDaoImpl().delete("s004");

//		new StaffDaoImpl().updatePhone("s004", "頂客族", "090");

//		new StaffDaoImpl().updatePassword("s003", "qweq123");

//		Staff staff=new Staff();
//		staff.setStaffName("劉花園");
//		staff.setStaffPhone("0923123123");
//		staff.setStaffNumber("s004");
//		new StaffDaoImpl().update(staff);

//		System.out.println(new StaffDaoImpl().checkUsername("sta2"));

//		System.out.println(new StaffDaoImpl().userLogin("sta2", "s123").getStaffName());

//		System.out.println(new StaffDaoImpl().idView("s003").getStaffPhone());

//		List<Staff> stafflist=new StaffDaoImpl().all();
//		for(Staff o:stafflist)
//		{
//			System.out.println(o.getStaffName());
//		}

//		new StaffDaoImpl().add(new Staff("張公公","sta2","s123","0900001100"));
	}

	Connection connection = DbConnection.getDb();

	@Override
	public void add(Staff staff) {
		String sql = "insert into staff(staffNumber,staffName,staffUsername,staffPassword,staffPhone) "
				+ "values(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String number = SQLTool.NumberForSQL("staff", "staffNumber", "s", connection);
			preparedStatement.setString(1, number);
			preparedStatement.setString(2, staff.getStaffName());
			preparedStatement.setString(3, staff.getStaffUsername());
			preparedStatement.setString(4, staff.getStaffPassword());
			preparedStatement.setString(5, staff.getStaffPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Staff> all() {
		String sql = "select * from staff";
		List<Staff> stafflist = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Staff staff = new Staff();
				staff.setStaffNumber(resultSet.getString("staffNumber"));
				staff.setStaffName(resultSet.getString("staffName"));
				staff.setStaffUsername(resultSet.getString("staffUsername"));
				staff.setStaffPassword(resultSet.getString("staffPassword"));
				staff.setStaffPhone(resultSet.getString("staffPhone"));
				stafflist.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stafflist;
	}

	@Override
	public Staff idView(String staffNumber) {
		String sql = "select * from staff where staffNumber=?";
		Staff staff = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, staffNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				staff = new Staff();
				staff.setStaffNumber(resultSet.getString("staffNumber"));
				staff.setStaffName(resultSet.getString("staffName"));
				staff.setStaffUsername(resultSet.getString("staffUsername"));
				staff.setStaffPassword(resultSet.getString("staffPassword"));
				staff.setStaffPhone(resultSet.getString("staffPhone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staff;
	}

	@Override
	public Staff userLogin(String username, String password) {
		String sql = "select * from staff where staffUsername=? and staffPassword=?";
		Staff staff = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				staff = new Staff();
				staff.setStaffNumber(resultSet.getString("staffNumber"));
				staff.setStaffName(resultSet.getString("staffName"));
				staff.setStaffUsername(resultSet.getString("staffUsername"));
				staff.setStaffPassword(resultSet.getString("staffPassword"));
				staff.setStaffPhone(resultSet.getString("staffPhone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staff;
	}

	@Override
	public Boolean checkUsername(String username) {
		String sql = "select * from staff where staffUsername=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Staff update(Staff staff) {
		Staff original = idView(staff.getStaffNumber());
		if (original == null)
			return null;
		String name = staff.getStaffName() != null ? staff.getStaffName() : original.getStaffName();
		String password = staff.getStaffPassword() != null ? staff.getStaffPassword() : original.getStaffPassword();
		String phone = staff.getStaffPhone() != null ? staff.getStaffPhone() : original.getStaffPhone();
		String sql = "update staff set staffName=?,staffPassword=?,staffPhone=? where staffNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, staff.getStaffNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return original;
	}

	@Override
	public void updatePassword(String staffNumber, String password) {
		String sql = "update staff set staffPassword=? where staffNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, staffNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePhone(String staffNumber, String name, String phone) {
		String sql = "update staff set staffName=?,staffPhone=? where staffNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, staffNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String staffNumber) {
		String sql = "delete from staff where staffNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, staffNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
