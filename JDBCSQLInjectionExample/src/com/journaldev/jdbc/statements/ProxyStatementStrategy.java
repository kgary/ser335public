package com.journaldev.jdbc.statements;

import java.util.List;
import java.util.ArrayList;

public class ProxyStatementStrategy implements IQueryStrategy {
    	public ProxyStatementStrategy() { System.out.println("Using ProxyStatementStrategy"); }

	@Override
	public List<PersonBean> executeQuery(String id, String pwd)  {
	    if (id.indexOf(';') != -1 || id.indexOf('\'') != -1 || pwd.indexOf(';') != -1 || pwd.indexOf('\'') != -1) {
		return new ArrayList<PersonBean>();
	    }
	    else return new StatementStrategy().executeQuery(id, pwd);
	}
}
