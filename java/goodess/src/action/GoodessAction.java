package action;


import java.sql.SQLException;
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
		List<Goodess> gs = g.query();
		for (Goodess goodess : gs) {
			System.out.print(goodess.getId());
			System.out.println(goodess.getUser_name());
		}
	}

}
