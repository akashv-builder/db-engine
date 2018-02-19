package com.dbEngine.queryParameter;

import java.util.*;
import java.util.regex.*;

public class QueryParameters {
	private String inputQuery = null;
	private String baseQuery = null;
	private String afterWhereQuery = null;
	private String[] split = null;
	private ArrayList<String> fileName = new ArrayList<>();
	private ArrayList<String> conditions = new ArrayList<>();
	private ArrayList<String> operators = new ArrayList<>();
	private String[] selectedFieldsSplit = null;
	private String orderByField = null;
	private String groupByField = null;
	private ArrayList<String> aggregate = new ArrayList<>();

	// function to get the query from user
	public void setQuery(String query) {
		inputQuery = query;
	}

	// function to get the query from user
	public String getQuery() {
		return inputQuery;
	}

	// function to tokenize the query
	public void tokenizeQuery() {

		if (!inputQuery.equals("")) {
			split = inputQuery.split("[ ,]");
		} else {
			split = null;
		}
	}

	// function to get the tokenized query
	public String[] getTokenize() {
		return split;
	}

	// function to get the base part of query
	public void setBasePart() {

		if (!inputQuery.equals("")) {
			int indexOfWhere = inputQuery.indexOf("where");
			int indexOfSemiColon = inputQuery.indexOf(";");
			if (indexOfWhere != -1) {
				baseQuery = inputQuery.substring(0, indexOfWhere - 1);
			} else if (indexOfSemiColon != -1) {

				baseQuery = inputQuery.substring(0, indexOfSemiColon - 1);
			} else {
				baseQuery = null;
			}

		} else {
			baseQuery = null;
		}

	}

	// function to return the base part of query
	public String getBasePart() {
		return baseQuery;
	}

	// function to get the after part of query
	public void setAfterWherePart() {
		if (!inputQuery.equals("")) {
			int indexOfWhere = inputQuery.indexOf("where");
			if (indexOfWhere != -1) {
				afterWhereQuery = inputQuery.substring(indexOfWhere + 6, inputQuery.length());

			} else {
				afterWhereQuery = null;
			}
		} else {
			afterWhereQuery = null;
		}
	}

	// function to return the after where part of query
	public String getAfterWherePart() {
		return afterWhereQuery;
	}
	//function to set the file name
	public void setFileName() {
		if (!inputQuery.equals("")) {
			Pattern p = Pattern.compile("[a-zA-Z0-9]+\\.(csv)");
			Matcher m = p.matcher(inputQuery);
			while (m.find()) {
				fileName.add(m.group());
			}
		}
	}
	//function to get the file name
	public ArrayList<String> getFileName() {
		return fileName;
	}
	//function to get the conditions in the query
	public void setConditions() {
		if (afterWhereQuery != null) {
			Pattern p2 = Pattern.compile("([A-Za-z0-9]+[ ]?)((<=)|(>=)|(<>)|=|>|<)([ ]?[']?[A-Za-z0-9]+[']?)");
			Matcher m2 = p2.matcher(afterWhereQuery);

			while (m2.find()) {
				conditions.add(m2.group());
			}
		}
	}
	
	//function returning the conditions
	public ArrayList<String> getConditions() {
		return conditions;
	}
	//function to get the operators in the query
	public void setOperator() {
		if (afterWhereQuery != null) {
			Pattern p3 = Pattern.compile("(and)|(or){2}|(not)");
			Matcher m3 = p3.matcher(afterWhereQuery);

			while (m3.find()) {
				operators.add(m3.group());
			}
		}
	}
	//function to return the operator fetched
	public ArrayList<String> getOperators() {
		return operators;
	}
	//function to get all the condition of where clause
	public void setDesired() {
		if (baseQuery != null) {
			int index_of_from = baseQuery.indexOf("from");
			int index_of_select = baseQuery.indexOf("select");

			String selected_fields = baseQuery.substring(index_of_select + 7, index_of_from);

			if (!(selected_fields.equals("*"))) {
				selectedFieldsSplit = selected_fields.split("[ ,]");
			}
		}
	}
	//function to get all the where conditions
	public String[] getDesired() {
		return selectedFieldsSplit;
	}
	//finding the order by field of the query
	public void setOrderBy() {
		if (afterWhereQuery != null) {
			Pattern p4 = Pattern.compile("(order by)[ ]?[A-Za-z_]+");
			Matcher m4 = p4.matcher(afterWhereQuery);
			if (m4.find()) {

				m4.group();
				int index_of_by = m4.group().indexOf("by");
				orderByField = m4.group().substring(index_of_by + 3, m4.group().length());
			}

		}
	}
	//getting the order by field
	public String getOrderBy() {
		return orderByField;
	}
	//finding the group by field of the query
	public void setGroupBy() {
		if (afterWhereQuery != null) {
			Pattern p5 = Pattern.compile("(group by)[ ]?[A-Za-z_]+");
			Matcher m5 = p5.matcher(afterWhereQuery);

			if (m5.find()) {
				m5.group();
				int index_of_by1 = m5.group().indexOf("by");
				groupByField = m5.group().substring(index_of_by1 + 3, m5.group().length());
			}

		}
	}
	//getting the group by field
	public String getGroupBy() {
		return groupByField;
	}
	//finding the aggregate field the query
	public void setAggregate() {
		if (baseQuery != null) {
			Pattern p6 = Pattern.compile("(sum|avg|min|max)(\\([A-Za-z_]*\\))");
			Matcher m6 = p6.matcher(baseQuery);
			while (m6.find()) {
				aggregate.add(m6.group());
			}
		}
	}
	//returning the aggregate field
	public ArrayList<String> getAggregate() {
		return aggregate;
	}

}