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
		//查询女神多条记录
		GoodessDao g = new GoodessDao();
		List<Goodess> gs = g.query();
		for (Goodess goodess : gs) {
			System.out.print(goodess.getId());
			System.out.println(goodess.getUser_name());
		}
		//新增女神
		Goodess gs1 = new Goodess();
		gs1.setUser_name("liangxifeng");
		gs1.setSex(3);
		gs1.setBirthday(new Date());
		g.addGoddess(gs1);
	}

}
