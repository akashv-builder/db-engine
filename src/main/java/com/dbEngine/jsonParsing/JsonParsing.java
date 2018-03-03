package com.dbEngine.jsonParsing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dbEngine.fileDataHandler.FileDataHandler;
import com.dbEngine.queryParameter.WhereQuery;

public class JsonParsing {

	public void generateJson(ArrayList<String>dataToPutInJson ) {
		//making object of file handler class
		FileDataHandler fileHandler = new FileDataHandler();
		//getting the header fields
		fileHandler.setDataType();
		//creating json object and array
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
		//creating a json file
		FileWriter jsonFile=null;
		
		try {
		jsonFile =  new FileWriter("queryjsondata.json");
		}catch(Exception e){
			System.out.println("Please enter a valid path where you want to store your json");
		}
		
		String key[] = fileHandler.returnKeys();
		for (int i = 0; i < dataToPutInJson.size(); i++) {
			String arr[] = dataToPutInJson.get(i).split(",");
			for (int j = 0; j < key.length - 1; j++) {
				json.put(key[j], arr[j]);
				jarray.add(i,json);			
			}	
		}
		try {
			jsonFile.write(jarray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jarray.toJSONString());
	}
}
