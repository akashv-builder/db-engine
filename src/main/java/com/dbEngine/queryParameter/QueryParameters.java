package com.dbEngine.queryParameter;

import java.util.*;
import java.util.regex.*;

public class QueryParameters {
	private String inputQuery = null;
	private String baseQuery = null;
	private String afterWhereQuery = null;
	private String[] split = null;
	private ArrayList<String> fileName = new ArrayList<String>();
	private ArrayList<String> conditions = new ArrayList<String>();
	private ArrayList<String> operators = new ArrayList<String>();
	private String[] selectedFieldsSplit = null;
	private String orderByField = null;
	private String groupByField = null;
	private ArrayList<String> aggregate = new ArrayList<String>();

	// function to get the query from user
	public void setQuery(String query) {
		inputQuery = query;
	}

	// function to get the query from user
	public String getQuery() {
		return inputQuery;
	}

	// function to tokenize the query
	public String[] tokenizeQuery(String query) {

		if (!query.equals("")) {
			split = query.split("[ ,]");
		} else {
			split = null;
		}
		return split;
	}

	// function to get the base part of query
	public String setBasePart(String query) {

		if (!query.equals("")) {
			int indexOfWhere = query.indexOf("where");
			int indexOfSemiColon = query.indexOf(";");
			if (indexOfWhere != -1) {
				baseQuery = query.substring(0, indexOfWhere - 1);
			} else if (indexOfSemiColon != -1) {

				baseQuery = query.substring(0, indexOfSemiColon - 1);
			} else {
				baseQuery = null;
			}

		} else {
			baseQuery = null;
		}
		return baseQuery;

	}

	// function to get the after part of query
	public String setAfterWherePart(String query) {
		if (!query.equals("")) {
			int indexOfWhere = query.indexOf("where");
			if (indexOfWhere != -1) {
				afterWhereQuery = query.substring(indexOfWhere + 6, query.length());

			} else {
				afterWhereQuery = null;
			}
		} else {
			afterWhereQuery = null;
		}
		return afterWhereQuery;
	}

	// function to set the file name
	public ArrayList<String> setFileName(String query) {
		if (!query.equals("")) {
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]+\\.(csv)");
			Matcher matcher = pattern.matcher(query);
			while (matcher.find()) {
				fileName.add(matcher.group());
			}
		}
		return fileName;
	}

	// function to get the conditions in the query
	public ArrayList<String> setConditions(String query) {
		if (query != null) {
			Pattern pattern = Pattern.compile("([A-Za-z0-9]+[ ]?)((<=)|(>=)|(<>)|=|>|<)([ ]?[']?[A-Za-z0-9]+[']?)");
			Matcher matcher = pattern.matcher(query);

			while (matcher.find()) {
				conditions.add(matcher.group());
			}
		}
		return conditions;
	}

	// function to get the operators in the query
	public ArrayList<String> setOperator(String query) {
		if (query != null) {
			Pattern pattern = Pattern.compile("(and)|(or){2}|(not)");
			Matcher matcher = pattern.matcher(query);

			while (matcher.find()) {
				operators.add(matcher.group());
			}
		}
		return operators;
	}

	// function to get all the condition of where clause
	public String[] setDesired(String query) {
		if (query != null) {
			int index_of_from = query.indexOf("from");
			int index_of_select = query.indexOf("select");

			String selected_fields = query.substring(index_of_select + 7, index_of_from);

			if (!(selected_fields.equals("*"))) {
				selectedFieldsSplit = selected_fields.split("[ ,]");
			}
		}
		return selectedFieldsSplit;
	}

	// finding the order by field of the query
	public String setOrderBy(String query) {
		if (query != null) {
			Pattern pattern = Pattern.compile("(order by)[ ]?[A-Za-z_]+");
			Matcher matcher = pattern.matcher(query);
			if (matcher.find()) {
				matcher.group();
				int index_of_by = matcher.group().indexOf("by");
				orderByField = matcher.group().substring(index_of_by + 3, matcher.group().length());
			}
		}
		return orderByField;
	}

	// finding the group by field of the query
	public String setGroupBy(String query) {
		if (query != null) {
			Pattern pattern = Pattern.compile("(group by)[ ]?[A-Za-z_]+");
			Matcher matcher = pattern.matcher(query);
			if (matcher.find()) {
				matcher.group();
				int index_of_by1 = matcher.group().indexOf("by");
				groupByField = matcher.group().substring(index_of_by1 + 3, matcher.group().length());
			}
		}
		return groupByField;
	}

	// finding the aggregate field the query
	public ArrayList<String> setAggregate(String query) {
		if (query != null) {
			Pattern pattern = Pattern.compile("(sum|avg|min|max)(\\([A-Za-z_]*\\))");
			Matcher matcher = pattern.matcher(query);
			while (matcher.find()) {
				aggregate.add(matcher.group());
			}
		}
		return aggregate;
	}
}