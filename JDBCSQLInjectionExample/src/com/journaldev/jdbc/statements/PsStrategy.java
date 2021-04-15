package com.journaldev.jdbc.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PsStrategy implements IQueryStrategy {
	public PsStrategy() { System.out.println("Using PsStrategy"); }

	@Override
	public List<PersonBean> executeQuery(String id, String pwd) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		ArrayList<PersonBean> rlist = new ArrayList<PersonBean>();
		try {
			String query = QueryStrategyFactory.getQueryParams("ps.query");
			con = DBConnectionKG.getConnection();
			ps = con.prepareStatement(query);

			//set the parameter
			ps.setString(1, id);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			while (rs.next()) {
				rlist.add(new PersonBean(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			return rlist;
		} catch (Throwable t) {
			t.printStackTrace();
			return rlist;
		} finally {
			try {
				if (rs != null)  rs.close();
				if (ps != null)  ps.close();
				if (con != null) con.close();
			} catch (Throwable t) {
				System.out.println("Error closing DB resources");
			}
		}
	}

}
