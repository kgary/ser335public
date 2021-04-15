package com.journaldev.jdbc.statements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatementStrategy implements IQueryStrategy {
	public StatementStrategy() { System.out.println("Using StatementStrategy"); }

	@Override
	public List<PersonBean> executeQuery(String id, String pwd)  {
		ResultSet rs = null;
		Statement stmt = null;
		Connection con = null;
		ArrayList<PersonBean> rlist = new ArrayList<PersonBean>();
		try {
			String query = QueryStrategyFactory.getQueryParams("sql.query");
			query = query.replaceFirst("XXX", id);
			query = query.replaceFirst("YYY", pwd);
			con = DBConnectionKG.getConnection();
			stmt = con.createStatement();

			System.out.println("In StatementStrategy, query is " + query);
			rs = stmt.executeQuery(query);
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
				if (stmt != null)  stmt.close();
				if (con != null) con.close();
			} catch (Throwable t) {
				System.out.println("Error closing DB resources");
			}
		}
	}

}
