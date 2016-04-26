package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.List;



import model.Goodess;
import db.DBUtil;
/**
 * 模型层
 */



public class GoodessDao {
	//定义mysql连接句柄
	private static Connection conn = null;
	private static Statement stmt = null;
	static
	{
		conn = DBUtil.getConnection();
		//创建Statement对象
		try {
			stmt = (Statement) conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/*
	 * 新增女神
	 * param [Goodess] gs 女神对象
	 */
	public void addGoddess(Goodess gs) throws SQLException
	{
		String sql = " insert into goodess (user_name,sex,birthday,add_time,update_time) " + 
					"values (" + 
					"?,?,?,current_date,current_date)";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//为预编译的占位符赋值
		ptmt.setString(1, gs.getUser_name());
		ptmt.setInt(2,gs.getSex());
		ptmt.setDate(3, new Date(gs.getBirthday().getTime()));
		//执行sql语句
		ptmt.execute();
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
