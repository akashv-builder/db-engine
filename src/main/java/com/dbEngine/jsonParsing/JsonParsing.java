package com.dbEngine.jsonParsing;

import java.util.ArrayList;

import org.json.JSONObject;

import com.dbEngine.queryParameter.WhereQuery;

public class JsonParsing {
	public static void createJSON(ArrayList<String> data) {
		JSONObject tomJsonObj = new JSONObject();
		tomJsonObj.put("data", data);
		System.out.println(tomJsonObj.toString(4));
	}
	public static void main(String[] args) {
		WhereQuery fireQuery=new WhereQuery();
		String query = "select * from ipl.cvs ;";
		fireQuery.queryFire(query);
		ArrayList<String> temp=fireQuery.returnJson();
		createJSON(temp);
	}
}
