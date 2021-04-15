package com.journaldev.jdbc.statements;

import java.util.Properties;

public final class QueryStrategyFactory {
	private static Properties props = KGProperties.getProps();
	
	public static final IQueryStrategy getQueryStrategy() {
		String iqs = props.getProperty("strategy.impl", "stmt");
		if (iqs.equals("stmt")) {
			return new StatementStrategy();
		}
		else {
			return new PsStrategy();
		}
	}

	public static final String getQueryParams(String key) {
		return props.getProperty(key);
	}
}
