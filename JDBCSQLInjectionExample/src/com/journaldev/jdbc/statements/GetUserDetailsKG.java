package com.journaldev.jdbc.statements;

import java.util.List;
import java.util.Scanner;

public class GetUserDetailsKG {

	public static void main(String[] args) {

		// read user entered data
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter email id:");
		String id = scanner.nextLine();
		System.out.println("User id=" + id);
		System.out.println("Please enter password to get details:");
		String pwd = scanner.nextLine();
		System.out.println("User password=" + pwd);
		printUserData(id, pwd);
		scanner.close();
	}

	private static void printUserData(String id, String pwd) {

		IQueryStrategy queryStrategy = QueryStrategyFactory.getQueryStrategy();
		List<PersonBean> people = queryStrategy.executeQuery(id, pwd);

		if (people == null || people.isEmpty()) {
			System.out.println("There are no people!");
		} else {
			for (PersonBean person : people) {
				System.out.println(person.toString());
			}
		}
	}
}
