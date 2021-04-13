package com.journaldev.jdbc.statements;

import java.util.List;
import java.util.ArrayList;

public class DecoratorStatementStrategy extends PsStrategy {
    private PsStrategy parentStrategy;
    public DecoratorStatementStrategy() {
	parentStrategy = new PsStrategy();
	System.out.println("Using DecoratorStatementStrategy");
    }
    @Override
    public List<PersonBean> executeQuery(String id, String pwd)  {
	if (id.indexOf(';') != -1 || id.indexOf('\'') != -1 || pwd.indexOf(';') != -1 || pwd.indexOf('\'') != -1) {
	    return new ArrayList<PersonBean>();
	}
	else return parentStrategy.executeQuery(id, pwd);
    }
}
