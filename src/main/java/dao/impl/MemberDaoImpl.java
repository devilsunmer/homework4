package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import model.Member;
import util.DbConnection;
import util.SQLTool;

public class MemberDaoImpl implements MemberDao {
	public static void main(String[] args) {
//		Member member=new Member();
//		member.setMemberNumber("m002");
//		member.setMemberName("花甲");
//		member.setMemberPassword("re43");
//		member.setMemberAddress("永和區");
//		member.setMemberPhone("0999999999");
//		member.setMemberOrNot(false);
//		new MemberDaoImpl().update(member);

//		new MemberDaoImpl().updateAddressAndPhone("m004","小醜醜","路邊撿來的", "0912121212");

//		new MemberDaoImpl().updatePassword("m003", "me12");

//		Member member=new Member();
//		member.setMemberNumber("memberNumber-1");
//		new MemberDaoImpl().delete(member);

//		System.out.print(new MemberDaoImpl().checkUsername("test1"));

//		List<Member> memberList = new MemberDaoImpl().all();
//		for (Member o : memberList) {
//			System.out.println(o.getMemberNumber());
//		}

//		new MemberDaoImpl().addAddressAndPhone("m002", null, "新北市", "090000000")

		/*
		 * List<Member> memberList = new MemberDaoImpl().idAllView("m002"); for (Member
		 * o : memberList) { System.out.println(o.getMemberAddress()); }
		 */

//		new MemberDaoImpl().add(new Member("大可愛","test2","t123","taipei","0911111110",false));
	}

	Connection connection = DbConnection.getDb();

	@Override
	public void add(Member member) {
		String sql = "insert into member(memberNumber,memberName,memberUsername,memberPassword"
				+ ",memberAddress,memberPhone,memberOrNot) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String newMemberNumber = SQLTool.NumberForSQL("member", "memberNumber", "m", connection);
			preparedStatement.setString(1, newMemberNumber);
			preparedStatement.setString(2, member.getMemberName());
			preparedStatement.setString(3, member.getMemberUsername());
			preparedStatement.setString(4, member.getMemberPassword());
			preparedStatement.setString(5, member.getMemberAddress());
			preparedStatement.setString(6, member.getMemberPhone());
			preparedStatement.setBoolean(7, member.getMemberOrNot());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAddressAndPhone(String memberNumber, String name, String address, String phone) {
		String sql = "insert into member(memberNumber,memberName,memberUsername,memberPassword"
				+ ",memberAddress,memberPhone,memberOrNot) values(?,?,?,?,?,?,?)";
		Member member = idView(memberNumber);
		String blankNull = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			if (member != null) {
				String numberUnder = SQLTool.numberUnder("member", "memberNumber", member.getMemberNumber(),
						connection);
				preparedStatement.setString(1, numberUnder);
				if (name == null)
					name = member.getMemberName();
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, blankNull);
				preparedStatement.setString(4, blankNull);
				preparedStatement.setString(5, address);
				preparedStatement.setString(6, phone);
				preparedStatement.setBoolean(7, member.getMemberOrNot());
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Address and phone added successfully.");
				} else {
					System.out.println("Failed to add address and phone.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Member> all() {
		String sql = "select * from member";
		List<Member> memberList = new ArrayList<>();
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public List<Member> isMember(Integer oneOrZero) {
		String sql = "select * from member where memberOrNot=?";
		List<Member> memberList = new ArrayList<>();
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, oneOrZero);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public Member idView(String memberNumber) {
		String sql = "select * from member where memberNumber=?";
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public Member nameSearch(String memberName) {
		String sql = "select * from member where memberName=?";
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	@Override
	public List<Member> idAllView(String memberNumber) {
		String sql = "select * from member where memberNumber like ?";
		List<Member> memberList = new ArrayList<>();
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String number = memberNumber + "%";
			preparedStatement.setString(1, number);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	@Override
	public List<Member> userView(String memberUsername) {
		String sql = "select * from member where memberUsername=?";
		List<Member> memberList = new ArrayList<>();
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberUsername);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public Member userLogin(String username, String password) {
		String sql = "select * from member where memberUsername=? and memberPassword=?";
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public Member freeUserUse(String memberName, String memberPhone) {
		String sql = "select * from member where memberName=? and memberPhone=?";
		Member member = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberName);
			preparedStatement.setString(2, memberPhone);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				member = new Member();
				member.setMemberNumber(resultSet.getString("memberNumber"));
				member.setMemberName(resultSet.getString("memberName"));
				member.setMemberUsername(resultSet.getString("memberUsername"));
				member.setMemberPassword(resultSet.getString("memberPassword"));
				member.setMemberAddress(resultSet.getString("memberAddress"));
				member.setMemberPhone(resultSet.getString("memberPhone"));
				member.setMemberOrNot(resultSet.getBoolean("memberOrNot"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public Boolean checkUsername(String username) {
		String sql = "select count(*) from member where memberUsername = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Member update(Member member) {
		Member original = idView(member.getMemberNumber());
		if (original == null)
			return null;
		String name = member.getMemberName() != null ? member.getMemberName() : original.getMemberName();
		String password = member.getMemberPassword() != null ? member.getMemberPassword()
				: original.getMemberPassword();
		String address = member.getMemberAddress() != null ? member.getMemberAddress() : original.getMemberAddress();
		String phone = member.getMemberPhone() != null ? member.getMemberPhone() : original.getMemberPhone();
		Boolean orNot = member.getMemberOrNot() != null ? member.getMemberOrNot() : original.getMemberOrNot();
		String sql = "update member set memberName=?,memberPassword=?,memberAddress=?,memberPhone=?,"
				+ "memberOrNot=? where memberNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, phone);
			preparedStatement.setBoolean(5, orNot);
			preparedStatement.setString(6, member.getMemberNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return original;
	}

	@Override
	public Boolean updatePassword(String userName, String password) {
		String sql = "update member set memberPassword=? where memberUsername=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, userName);
			int row = preparedStatement.executeUpdate();
			return row > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateAddressAndPhone(String memberNumber, String name, String address, String phone) {
		String sql = "update member set memberName=?,memberAddress=?,memberPhone=? where memberNumber=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, memberNumber);
			int row = preparedStatement.executeUpdate();
			return row > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void delete(Member member) {
		String sql = "delete from member where memberNumber=?";
		if (member != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				String number = member.getMemberNumber();
				preparedStatement.setString(1, number);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
