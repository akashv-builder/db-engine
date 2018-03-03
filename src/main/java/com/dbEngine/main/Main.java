package com.dbEngine.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.dbEngine.jsonParsing.JsonParsing;
import com.dbEngine.queryParameter.WhereQuery;

public class Main {

	public static void main(String[] args) {
		//creating object of scanner
		Scanner sc = new Scanner(System.in);
		//taking query from user
		System.out.println("Enter query");
		String query;
		// calling set query method String
		query = sc.nextLine();

		// String query = "select city from ipl.cvs where city='bangalore' ;";
		//executing the query
		WhereQuery executeQuery = new WhereQuery();
		executeQuery.queryFire(query);
		//asking to create a json or not
		System.out.println("Do you want to create json");
		String option = sc.next();
		if(option.equals("Y")||option.equals("y")) {
			//creating obj of json parsing class
			JsonParsing createJson = new JsonParsing();
			//storing retrieved data and passing to form json
			ArrayList<String> jsonResultToStore = new ArrayList<String>();
			jsonResultToStore = executeQuery.returnJson();
			createJson.generateJson(jsonResultToStore);
		}
		else {
			//if do not want to create json
			System.out.println("OK");
		}
		
	}
}