package com.dbEngine.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.dbEngine.queryParameter.QueryParameters;

//annotation used to run junit test cases
@RunWith(JUnitPlatform.class)
class TestQueryParameter {
	QueryParameters qp = null;

	@BeforeEach
	void beforeEach() {
		// before each method will run before each test case
		qp = new QueryParameters();
		qp.setQuery("select * from ipl.csv where id>100 ;");
		System.out.println("Before each test method");
	}

	@Test
	void testingGetQuery() {
		// testing get query method
		assertEquals("select * from ipl.csv where id>100 ;", qp.getQuery());
		System.out.println("testing GetQuery()");
	}

	@Test
	void testingTokenizeQuery() {
		// testing tokenize query method
		String query = "select * from ipl.csv where id>100 ;";
		String[] arr = { "select", "*", "from", "ipl.csv", "where", "id>100", ";" };
		assertArrayEquals(arr, qp.tokenizeQuery(query));
		System.out.println("testing TokenizeQuery()");
	}
	
	@Test
	void testingTokenizeQueryFail() {
		// testing tokenize query method
		String query = "select * from ipl.csv where id>100 ;";
		//star is missing
		String[] arr = { "select", "from", "ipl.csv", "where", "id>100", ";" };
		assertArrayEquals(arr, qp.tokenizeQuery(query));
		System.out.println("testing Fail TokenizeQuery()");
	}


	@Test
	void testingSetBasePart() {
		// testing set base part method
		String query = "select * from ipl.csv where id>100 ;";
		assertEquals("select * from ipl.csv", qp.setBasePart(query));
		System.out.println("testing SetBaseWherePart()");
	}

	@Test
	void testingSetBasePartFail() {
		// testing set base part method
		String query = "select * from ipl.csv where id>100 ;";
		//from is missing
		assertEquals("select * ipl.csv", qp.setBasePart(query));
		System.out.println("testing Fail SetBaseWherePart()");
	}

	
	@Test
	void testingSetAfterWherePart() {
		// testing after where part method
		String query = "select * from ipl.csv where id>100 ;";
		assertEquals("id>100 ;", qp.setAfterWherePart(query));
		System.out.println("testing SetAfterWherePart()");
	}
	
	@Test
	void testingSetAfterWherePartFail() {
		// testing after where part method
		String query = "select * from ipl.csv where id>100 ;";
		//> operator is missing
		assertEquals("id 100 ;", qp.setAfterWherePart(query));
		System.out.println("testing Fail SetAfterWherePart()");
	}

	
	@Test
	void testingSetFileName() {
		// testing set file name method
		String query = "select * from ipl.csv where id>100 ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("ipl.csv");
		assertEquals(al, qp.setFileName(query));
		System.out.println("testing SetFileName()");
	}
	
	@Test
	void testingSetFileNameFail() {
		// testing set file name method
		String query = "select * from ipl.csv where id>100 ;";
		ArrayList<String> al = new ArrayList<String>();
		//extension is missing
		al.add("ipl");
		assertEquals(al, qp.setFileName(query));
		System.out.println("testing Fail SetFileName()");
	}

	@Test
	void testingSetConditions() {
		// testing set condition method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("id>100");
		al.add("city='Banglore'");
		assertEquals(al, qp.setConditions(query));
		System.out.println("testing SetConditions()");
	}
	
	@Test
	void testingSetConditionsFail() {
		// testing set condition method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		ArrayList<String> al = new ArrayList<String>();
		//only one condition added actual are two
		al.add("city='Banglore'");
		assertEquals(al, qp.setConditions(query));
		System.out.println("testing Fail SetConditions()");
	}

	@Test
	void testingSetOperator() {
		// testing set operator method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("and");
		assertEquals(al, qp.setOperator(query));
		System.out.println("testing SetOperator()");
	}
	
	@Test
	void testingSetOperatorFail() {
		// testing set operator method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		ArrayList<String> al = new ArrayList<String>();
		//condition is and but added or
		al.add("or");
		assertEquals(al, qp.setOperator(query));
		System.out.println("testing Fail SetOperator()");
	}

	@Test
	void testingSetDesired() {
		// testing set desired method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		String[] al = { "*" };
		assertArrayEquals(al, qp.setDesired(query));
		System.out.println("testing SetDesired()");
	}
	
	@Test
	void testingSetDesiredFail() {
		// testing set desired method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		String[] al = { "city" };
		//desired is * but giving city
		assertArrayEquals(al, qp.setDesired(query));
		System.out.println("testing Fail SetDesired()");
	}

	@Test
	void testingSetOrderBy() {
		// testing set order by method
		String query = "select * from ipl.csv where id>100 and city='Banglore' order by city ;";
		String al = "city";
		assertEquals(al, qp.setOrderBy(query));
		System.out.println("testing SetOrderBy()");
	}
	@Test
	void testingSetOrderByFail() {
		// testing set order by method
		String query = "select * from ipl.csv where id>100 and city='Banglore' order by city ;";
		String al = "id";
		//orderby field is city but giving id
		assertEquals(al, qp.setOrderBy(query));
		System.out.println("testing Fail SetOrderBy()");
	}

	@Test
	void testingSetGroupBy() {
		// testing set group by method
		String query = "select * from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
		String al = "id";
		assertEquals(al, qp.setGroupBy(query));
		System.out.println("testing SetGroupBy()");
	}


	@Test
	void testingSetGroupByFail() {
		// testing set group by method
		String query = "select * from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
		String al = "city";
		//group by field is id but giving city
		assertEquals(al, qp.setGroupBy(query));
		System.out.println("testing Fail SetGroupBy()");
	}

	
	@Test
	void testingSetAggregate() {
		// testing set aggregate method
		String query = "select * sum(id) from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("sum(id)");
		assertEquals(al, qp.setAggregate(query));
		System.out.println("testing SetAggregate()");
	}
	
	@Test
	void testingSetAggregateFail() {
		// testing set aggregate method
		String query = "select * sum(id) from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("avg(id)");
		//aggregate is sum but giving avg
		assertEquals(al, qp.setAggregate(query));
		System.out.println("testing Fail SetAggregate()");
	}

	// after each will execute after each test case
	@AfterEach
	void afterEach() {
		System.out.println("After each test method");
		qp = null;
	}
}
