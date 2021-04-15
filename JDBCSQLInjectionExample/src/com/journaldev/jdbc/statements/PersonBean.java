package com.journaldev.jdbc.statements;

public final class PersonBean {
	private String name = null;
	private String country = null;
	private String passwd = null;
	
	public PersonBean(String n, String c, String p) {
		this.name = n;
		this.country = c;
		this.passwd = p;
	}
	
	public String getName() { return name; }
	public String getCountry() { return country; }
	public String getPassword() { return passwd; }
	
	public String toString() {
		return "Name=" + getName() + ",country=" + getCountry() + ",password=" + getPassword();
	}
}
