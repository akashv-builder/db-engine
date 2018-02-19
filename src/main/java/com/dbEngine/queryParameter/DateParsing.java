package com.dbEngine.queryParameter;

import com.dbEngine.fileDataHandler.FileDataHandler;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateParsing {
	//arraylist to store the parsed date
	private ArrayList<Date> parsedDate = new ArrayList<Date>();

	public void setDateParsing() {
		//reading the file using file data handler object
		FileDataHandler fh = new FileDataHandler();
		ArrayList<String> fileData = new ArrayList<String>();
		ArrayList<String> tempDate = new ArrayList<String>();
		//setting all the records
		fh.setDataType();
		//getting all the records in an array list
		fileData = fh.getFileRecords();
		String res = String.join(",", fileData);
		//regex to get all the date
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Matcher m = p.matcher(res);

		while (m.find()) {
			tempDate.add(m.group());
		}

		String res1 = String.join(",", tempDate);
		String[] split = res1.split(",");
		//parsing the date into date object
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (String data : split) {
			try {
				Date today = sdf.parse(data);
				parsedDate.add(today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}


	}
	//getting the parsed date
	public ArrayList<Date> getParsedDate() {
		return parsedDate;
	}

}