package action;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.GoodessDao;
import model.Goodess;

public class GoodessAction {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		GoodessDao g = new GoodessDao();
		//查询女神多条记录
		/*
		List<Goodess> gs = g.query();
		for (Goodess goodess : gs) {
			System.out.print(goodess.getId());
			System.out.println(goodess.getUser_name());
		}
		*/
		
		Goodess gs1 = new Goodess();
		gs1.setUser_name("liangxifeng modify");
		gs1.setSex(1);
		gs1.setBirthday(new Date());
		gs1.setUpdate_time(new Date());
		gs1.setId(1);
		//新增女神
		//g.addGoddess(gs1);
		//修改女神
		g.updateGoddess(gs1);
		//删除女神
		//g.delGoddess(3);
		
		//查询单个女神
		Goodess gObj = g.get(1);
		System.out.println(gObj.toString());
	}

}
