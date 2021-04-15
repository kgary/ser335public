package com.journaldev.jdbc.statements;

import java.util.List;

public interface IQueryStrategy {
	public List<PersonBean> executeQuery(String id, String pwd);
}
