package com.dbEngine.jsonParsing;

import java.util.HashMap;

import org.json.JSONObject;

public class JsonParsing {
	private static void createJSON() {
		JSONObject tomJsonObj = new JSONObject();
		tomJsonObj.put("name", "Tom");
		tomJsonObj.put("birthday", "1940-02-10");
		tomJsonObj.put("age", 76);
		tomJsonObj.put("married", false);

		// Cannot set null directly
		tomJsonObj.put("car", JSONObject.NULL);

		tomJsonObj.put("favorite_foods", new String[] { "cookie", "fish", "chips" });

		// {"id": 100001, "nationality", "American"}
		JSONObject passportJsonObj = new JSONObject();
		passportJsonObj.put("id", 100001);
		passportJsonObj.put("nationality", "American");
		// Value of a key is a JSONObject
		tomJsonObj.put("passport", passportJsonObj);

			//number of spaces to indent
			System.out.println(tomJsonObj.toString(4));
	}
			 public void createJSONFromMap() {
		    // Java Map object to store key-value pairs
		    HashMap<String, Object> tom = new HashMap<String, Object>();
		    tom.put("name", "Tom");
		    tom.put("birthday", "1940-02-10");
		    tom.put("age", 76);
		    tom.put("married", false);
		    
		    // Must be  JSONObject.NULL instead of null
		    tom.put("car", JSONObject.NULL);
		    tom.put("favorite_foods", new String[] { "cookie", "fish", "chips" });

		    HashMap<String, Object> passport = new HashMap<String, Object>();
		    passport.put("id", 100001);
		    passport.put("nationality", "American");
		    tom.put("passport", passport);
		    
		    // Create JSON object from Java Map
		    JSONObject tomJsonObj1 = new JSONObject(tom);
		        // With 4 indent spaces
		        System.out.println(tomJsonObj1.toString(4));
		   
		}
	
	
	public static void main(String[] args) {
		JsonParsing.createJSON();
	}
}
