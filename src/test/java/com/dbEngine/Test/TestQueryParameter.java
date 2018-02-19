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
   
	@BeforeEach
    void beforeEach() {
    	
    	qp=new QueryParameters();
        System.out.println("Before each test method");
    }
    
    @Test
    void firstTest() {
        System.out.println("First test method");
        assertEquals("hi", qp.getQuery());
    }
 
    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
        qp=null;
    }
}

