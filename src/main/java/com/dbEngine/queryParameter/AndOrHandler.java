package com.dbEngine.queryParameter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dbEngine.fileDataHandler.FileDataHandler;

public class AndOrHandler {

	public ArrayList<String> retrieveWhereData(ArrayList<String> dataToOprate, ArrayList<Integer> index,
			ArrayList<String> conditionoperator, ArrayList<String> conditionActual, int noOfTime) {
		ArrayList<String> toBeReturned = new ArrayList<String>();
		for (int i = 1; i < dataToOprate.size(); i++) {

			String[] split = dataToOprate.get(i).split(",");

			for (int j = 0; j < split.length; j++) {
				if (j == index.get(noOfTime)) {

					if (conditionoperator.get(noOfTime).equals("=")) {
						if (split[j].equalsIgnoreCase(conditionActual.get(noOfTime))) {
							toBeReturned.add(dataToOprate.get(i));
						}
					} else if (conditionoperator.get(noOfTime).equals("<")) {
						if (Double.parseDouble(split[j]) < Double.parseDouble(conditionActual.get(noOfTime))) {
							toBeReturned.add(dataToOprate.get(i));
						}
					} else if (conditionoperator.get(noOfTime).equals(">")) {

						if (Double.parseDouble(split[j]) > Double.parseDouble(conditionActual.get(noOfTime))) {
							toBeReturned.add(dataToOprate.get(i));
						}
					}

				}

			}
		}

		return toBeReturned;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String query = "select * from ipl.cvs where id>500 or city='bangalore';";
		QueryParameters queryParameter = new QueryParameters();
		ArrayList<String> logicalOperator = new ArrayList<String>();

		logicalOperator = queryParameter.setOperator(query);

		ArrayList<String> conditionName = new ArrayList<String>();
		ArrayList<String> conditionoperator = new ArrayList<String>();
		ArrayList<String> conditionActual = new ArrayList<String>();
		String s1 = queryParameter.setAfterWherePart(query);
		if (s1 != null) {
			ArrayList<String> conditions = new ArrayList<String>();
			conditions = queryParameter.setConditions(queryParameter.setAfterWherePart(query));
			Pattern p2 = Pattern.compile("([A-Za-z0-9]+[ ]?)(<=|>=|<>|=|>|<)([ ]?[']?)([A-Za-z0-9]+)([']?)");
			Matcher m2 = p2.matcher(s1);
			// getting all the desired conditions in array list
			while (m2.find()) {
				conditionName.add(m2.group(1));
				conditionoperator.add(m2.group(2));
				conditionActual.add(m2.group(4));
			}
		}
		System.out.println(conditionName);
		System.out.println(conditionoperator);
		System.out.println(conditionActual);

		FileDataHandler fileHandler = new FileDataHandler();
		fileHandler.setDataType();
		ArrayList<String> csvData = fileHandler.getFileRecords();
		LinkedHashMap<String, String> headerInfo = new LinkedHashMap<String, String>();
		headerInfo = fileHandler.getDataType();
		System.out.println(headerInfo);
		Set<String> setOfKey = headerInfo.keySet();
		Object[] obejectOfKey = setOfKey.toArray();
		ArrayList<Integer> indexToSearch = new ArrayList<Integer>();

		for (int looper = 0; looper < conditionName.size(); looper++) {
			for (int i = 0; i < obejectOfKey.length; i++) {
				if (obejectOfKey[i].equals(conditionName.get(looper))) {
					indexToSearch.add(i);
				}
			}
		}
		ArrayList<String> semiResult = new ArrayList<String>();
		ArrayList<String> finalResult = new ArrayList<String>();
		AndOrHandler a = new AndOrHandler();
		System.out.println(logicalOperator.get(0));
		if (logicalOperator.get(0).equals("and")) {
			semiResult = a.retrieveWhereData(csvData, indexToSearch, conditionoperator, conditionActual, 0);
			finalResult = a.retrieveWhereData(semiResult, indexToSearch, conditionoperator, conditionActual, 1);
			System.out.println(finalResult);
		}
		else {
			semiResult = a.retrieveWhereData(csvData, indexToSearch, conditionoperator, conditionActual, 0);
			finalResult = a.retrieveWhereData(csvData, indexToSearch, conditionoperator, conditionActual, 1);
			semiResult.addAll(finalResult);
			System.out.println(semiResult);
		}

	}

}
