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

@RunWith(JUnitPlatform.class)
class TestQueryParameter {
	QueryParameters qp=null;
	
	@BeforeEach
    void beforeEach() {
    	
    	qp=new QueryParameters();
    	qp.setQuery("select * from ipl.csv where id>100 ;");
    	
        System.out.println("Before each test method");
    }
    
    @Test
    void firstTest() {
    	
    	assertEquals("select * from ipl.csv where id>100 ;",qp.getQuery());
        System.out.println("First test method");
    }
 
    @Test
    void secondTest() {
    	String query="select * from ipl.csv where id>100 ;";
    	String []arr=  {"select", "*", "from", "ipl.csv", "where", "id>100", ";"};
    	assertArrayEquals(arr,qp.tokenizeQuery(query));
        System.out.println("Second test method");
    }
    
    @Test
    void thirdTest() {
    	String query="select * from ipl.csv where id>100 ;";
    	assertEquals("select * from ipl.csv",qp.setBasePart(query));
        System.out.println("Third test method");
    }
    @Test
    void fourthTest() {
    	String query="select * from ipl.csv where id>100 ;";
    	assertEquals("id>100 ;",qp.setAfterWherePart(query));
        System.out.println("Fourth test method");
    }
    @Test
    void fifthTest() {
    	String query="select * from ipl.csv where id>100 ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add("ipl.csv");
    	assertEquals(al,qp.setFileName(query));
        System.out.println("Fifth test method");
    }
    @Test
    void sixthTest() {
    	String query="select * from ipl.csv where id>100 and city='Banglore' ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add("id>100");
    	al.add("city='Banglore'");
    	assertEquals(al,qp.setConditions(query));
        System.out.println("Sixth test method");
    }
    @Test
    void seventhTest() {
    	String query="select * from ipl.csv where id>100 and city='Banglore' ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add(">");
    	al.add("=");
    	assertEquals(al,qp.setOperator(query));
        System.out.println("Seventh test method");
    }
    @Test
    void eighthTest() {
    	String query="select * from ipl.csv where id>100 and city='Banglore' ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add("*");
    	assertEquals(al,qp.setDesired(query));
        System.out.println("Eighth test method");
    }
    @Test
    void ninthTest() {
    	String query="select * from ipl.csv where id>100 and city='Banglore' order by city ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add("city");
    	assertEquals(al,qp.setOrderBy(query));
        System.out.println("Nigth test method");
    }
    @Test
    void tenthTest() {
    	String query="select * from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add("id");
    	assertEquals(al,qp.setGroupBy(query));
        System.out.println("Tenth test method");
    }
    @Test
    void eleventhTest() {
    	String query="select * sum(id) from ipl.csv where id>100 and city='Banglore' order by city group by id ;";
    	ArrayList<String> al= new ArrayList<String>();
    	al.add("sum(id)");
    	assertEquals(al,qp.setGroupBy(query));
        System.out.println("Eleventh test method");
    }
    
    
    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
        qp=null;
    }
}

