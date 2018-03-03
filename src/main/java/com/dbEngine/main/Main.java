package com.dbEngine.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.dbEngine.jsonParsing.JsonParsing;
import com.dbEngine.queryParameter.WhereQuery;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter query");
		String query;
		// calling set query method String
		query = sc.nextLine();

		// String query = "select city from ipl.cvs where city='bangalore' ;";
		WhereQuery executeQuery = new WhereQuery();
		executeQuery.queryFire(query);
		System.out.println("Do you want to create json");
		String option = sc.next();
		if(option.equals("Y")||option.equals("y")) {
			JsonParsing createJson = new JsonParsing();
			ArrayList<String> jsonResultToStore = new ArrayList<String>();
			jsonResultToStore = executeQuery.returnJson();
			createJson.generateJson(jsonResultToStore);
		}
		else {
			System.out.println("OK");
		}
		
	}
}