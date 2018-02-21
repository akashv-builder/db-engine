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
	void firstTest() {
		// testing get query method
		assertEquals("select * from ipl.csv where id>100 ;", qp.getQuery());
		System.out.println("First test method");
	}

	@Test
	void secondTest() {
		// testing tokenize query method
		String query = "select * from ipl.csv where id>100 ;";
		String[] arr = { "select", "*", "from", "ipl.csv", "where", "id>100", ";" };
		assertArrayEquals(arr, qp.tokenizeQuery(query));
		System.out.println("Second test method");
	}

	@Test
	void thirdTest() {
		// testing set base part method
		String query = "select * from ipl.csv where id>100 ;";
		assertEquals("select * from ipl.csv", qp.setBasePart(query));
		System.out.println("Third test method");
	}

	@Test
	void fourthTest() {
		// testing after where part method
		String query = "select * from ipl.csv where id>100 ;";
		assertEquals("id>100 ;", qp.setAfterWherePart(query));
		System.out.println("Fourth test method");
	}

	@Test
	void fifthTest() {
		// testing set file name method
		String query = "select * from ipl.csv where id>100 ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("ipl.csv");
		assertEquals(al, qp.setFileName(query));
		System.out.println("Fifth test method");
	}

	@Test
	void sixthTest() {
		// testing set condition method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("id>100");
		al.add("city='Banglore'");
		assertEquals(al, qp.setConditions(query));
		System.out.println("Sixth test method");
	}

	@Test
	void seventhTest() {
		// testing set operator method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("and");
		assertEquals(al, qp.setOperator(query));
		System.out.println("Seventh test method");
	}

	@Test
	void eighthTest() {
		// testing set desired method
		String query = "select * from ipl.csv where id>100 and city='Banglore' ;";
		String[] al = { "*" };
		assertArrayEquals(al, qp.setDesired(query));
		System.out.println("Eighth test method");
	}

	@Test
	void ninthTest() {
		// testing set order by method
		String query = "select * from ipl.csv where id>100 and city='Banglore' order by city ;";
		String al = "city";
		assertEquals(al, qp.setOrderBy(query));
		System.out.println("Nigth test method");
	}

	@Test
	void tenthTest() {
		// testing set group by method
		String query = "select * from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
		String al = "id";
		assertEquals(al, qp.setGroupBy(query));
		System.out.println("Tenth test method");
	}

	@Test
	void eleventhTest() {
		// testing set aggregate method
		String query = "select * sum(id) from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
		ArrayList<String> al = new ArrayList<String>();
		al.add("sum(id)");
		assertEquals(al, qp.setAggregate(query));
		System.out.println("Eleventh test method");
	}

	// after each will execute after each test case
	@AfterEach
	void afterEach() {
		System.out.println("After each test method");
		qp = null;
	}
}
