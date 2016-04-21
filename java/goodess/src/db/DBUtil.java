package db;
/*
 * jdbc连接mysql数据库
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/test";
	private static final String NAME = "common";
	private static final String PASS = "common";
	

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
			//加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//获取数据库连接
			Connection conn = DriverManager.getConnection(URL, NAME, PASS);
			//创建Statement对象
			Statement stmt = (Statement) conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select id from a");
			while(res.next())
			{
				System.out.println(res.getInt("id"));
			}

		
	}

}
