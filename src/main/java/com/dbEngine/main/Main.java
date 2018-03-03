package com.dbEngine.main;

import com.dbEngine.queryParameter.WhereQuery;

public class Main {

	public static void main(String[] args) {

		// Scanner sc = new Scanner(System.in);
		// System.out.println("Enter query");
		// calling set query method String
		// query = sc.nextLine();

		String query = "select * from ipl.cvs where city='bangalore' ;";
		WhereQuery executeQuery = new WhereQuery();
		executeQuery.queryFire(query);
	}
}