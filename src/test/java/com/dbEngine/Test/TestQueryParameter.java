package com.dbEngine.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import com.dbEngine.queryParameter.QueryParameters;

@RunWith(JUnitPlatform.class)
class TestQueryParameter {
	QueryParameters qp=null;
	String query="select * from ipl.csv where id>100 ;";
	@BeforeEach
    void beforeEach() {
    	
    	qp=new QueryParameters();
    	qp.setQuery("select * from ipl.csv where id>100 ;");
    	
        System.out.println("Before each test method");
    }
    
    @Test
    void firstTest() {
    	
    	assertEquals("select * from ipl.csv where id>100 and city='Banglore",qp.getQuery());
        System.out.println("First test method");
    }
 
    @Test
    void secondTest() {
    	String []arr=  {"select", "*", "from", "ipl.csv", "where", "id>100", ";"};
    	assertEquals(arr,qp.tokenizeQuery(query));
        System.out.println("First test method");
    }
    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
        qp=null;
    }
}

