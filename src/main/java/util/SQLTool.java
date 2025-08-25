package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static <T>String NumberForSQL(String table,String column,String serial,Connection connection)
	{
		String generatedNumber = serial + "001";
		String query = String.format("SELECT MAX(CAST(SUBSTRING(%s, LENGTH('%s') + 1) AS UNSIGNED)) FROM %s", column, serial, table);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                int maxId = resultSet.getInt(1);  // 取得資料庫中的最大編號
                generatedNumber = String.format("%s%03d", serial, maxId + 1);  // 生成新編號，保證是3位數
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedNumber;
	}

	public static String numberUnder(String table,String column,String serial,Connection connection)
	{
        String generatedNumber = serial + "-2";  // 預設編號，假設是 "m001-1"

        // SQL 查詢資料庫中相同編號前綴的最大後綴
        String query = "SELECT MAX(CAST(SUBSTRING(" + column + ", LENGTH(?) + 2) AS UNSIGNED)) " +
                "FROM " + table + " WHERE " + column + " LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	preparedStatement.setString(1, serial);  // 設定編號前綴（例如 "m001"）
        	preparedStatement.setString(2, serial + "%");  // 查詢條件：查詢 "m001%" 開頭的編號

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int maxSuffix = resultSet.getInt(1);  // 取得最大後綴
                generatedNumber = serial + "-" + (maxSuffix + 1);  // 生成新編號，保證是3位數
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedNumber;
    }

}
