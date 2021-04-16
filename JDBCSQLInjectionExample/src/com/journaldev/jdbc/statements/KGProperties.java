package com.journaldev.jdbc.statements;

/* 
 Example on reading in a properties file
 You have 2 options:
 1. Read it in using the classloader getResourceAsStream
 2. Read it in using the normal File I/O

 This example shows #2. #1 is often used for embedded property files under a
 built context as it can bundle in with the compiled code in a jarfile or be
 put in a protected place (like in servlet web dev). I don't care which you 
 use, but your choice will impact where the .properties file needs to be at
 run time for your code to find it.

 This example uses a simple Singleton pattern, so these will be lazy loaded
 on the first request to get the Properties.
 */

import java.util.Properties;
import java.io.FileInputStream;

public final class KGProperties {
	private static Properties __theProps = null;

	public static Properties getProps() {
		try {
			if (__theProps == null) {
			        __theProps = System.getProperties();
				String propFile = System.getProperty("db");
				if (propFile == null) propFile="mysql";
				__theProps.load(new FileInputStream(propFile+".properties"));
			}
			// return a safe clone instead of the object for security reasons
			Properties cloneProps = new Properties();
			__theProps.putAll(cloneProps);
			//return cloneProps;
			return __theProps;
		} catch (Throwable t) {
			t.printStackTrace();  // really Dr. Gary? Isn't this a poor practice? YES!
			return null;
		}
	}
}
