package com.journaldev.jdbc.statements;

import java.util.Properties;
import java.lang.reflect.Constructor;

public final class QueryStrategyFactory2 {
    private static Properties props = KGProperties.getProps();
	
    public static final IQueryStrategy getQueryStrategy() {
	try {
	    Constructor con = Class.forName(props.getProperty("strategy.class", "com.journaldev.jdbc.statements.StatementStrategy")).getConstructor();
	    System.out.println("Loading strategy " + props.getProperty("strategy.class"));
	    return (IQueryStrategy)con.newInstance(); 
	} catch (Exception e) {
	    // Need to clean this up and catch the proper exceptions
	    e.printStackTrace();
	}
	// should never get here, return this in the spirit of Establish Secure Defaults
	return new StatementStrategy();
    }
	
    public static final String getQueryParams(String key) {
	return props.getProperty(key);
    }
}
