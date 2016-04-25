package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;


import model.Goodess;
import db.DBUtil;
/**
 * 模型层
 */



public class GoodessDao {
	/*
	 * 新增女神
	 */
	public void addGoddess()
	{
		Connection conn = DBUtil.getConnection();
	}
	/*
	 * 修改女神
	 */
	public void updateGoddess()
	{
		
	}
	/*
	 * 删除女神
	 */
	public void delGoddess()
	{
		
	}
	/*
	 * 查询女神
	 */
	public List<Goodess> query() throws SQLException
	{
		Connection conn = DBUtil.getConnection();
		//创建Statement对象
		Statement stmt = (Statement) conn.createStatement();	
		ResultSet res = stmt.executeQuery("select * from goodess");
		//建立返回女神返回值集合，作为返回值
		List<Goodess> gd = new ArrayList<Goodess>();
		//建立用来存储女神对象的变量名，初值为null
		Goodess g = null;
		while(res.next())
		{
			g = new Goodess();
			g.setId(res.getInt("id"));
			//g.setAdd_time(res.getInt("add_time"));
			g.setUser_name(res.getString("user_name"));
			g.setSex(res.getInt("sex"));
			//g.setBirthday(res.getDate(columnIndex));
			gd.add(g);
		}
		return gd;
	}
	/*
	 * 查询单个女神
	 */
	public Goodess get()
	{
		return null;
	}
}
