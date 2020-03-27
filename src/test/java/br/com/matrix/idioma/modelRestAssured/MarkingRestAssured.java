package br.com.matrix.idioma.modelRestAssured;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MarkingRestAssured {
	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080/";
	}
	
	@Test
	public void getResquestMarkingOK(){		
		RestAssured.given().
		when().
			get("/marking/1").
		then().
			statusCode(200).
			body("id", is(1)).
			body("audioId", notNullValue()).
			body("userId", notNullValue()).		
			body("begin", notNullValue()).
			body("end", notNullValue());										
	}
	
	@Test
	public void postRequestCreateMarking() {
		
		Map<String, Object> markingDetails = new HashMap<>();
		 
		markingDetails.put("audioId",  "1");
		markingDetails.put("userId", "1");
		markingDetails.put("begin", "00:01:03");
		markingDetails.put("end",  "00:01:08");
		
		Response response = RestAssured.given().
		contentType("application/json").
		accept("application/json").		 
		body(markingDetails).
		when().
		post("/marking").
		then().
		statusCode(201). 
		contentType("application/json").
		extract().
		response();
		
		Long id = response.jsonPath().getLong("id");
		assertNotNull(id);
		
		
	}

	
	@Test
	public void postRequestCreateAndDeleteById() {
		
		Map<String, Object> markingDetails = new HashMap<>();
		 
		markingDetails.put("audioId",  "1");
		markingDetails.put("userId", "1");
		markingDetails.put("begin", "00:01:03");
		markingDetails.put("end",  "00:01:08");
		
		Response response = RestAssured.given().
		contentType("application/json").
		accept("application/json").		 
		body(markingDetails).
		when().
		post("/marking").
		then().
		statusCode(201). 
		contentType("application/json").
		extract().
		response();
		
		Long id = response.jsonPath().getLong("id")	;	
		RestAssured.given().pathParam("id", id).
		when().delete("/marking/{id}").then().statusCode(200);
		
	}
	
	@Test
	public void getResquestMarkingNotFound(){
		
		RestAssured.given().
		when().
			get("/marking/999999999").
		then().
			statusCode(404).
			body("title", containsString("org.springframework.web.servlet.PageNotFound")).
			body("detail", containsString("A marcação não existe.")).
			body("timeStamp", notNullValue()).			
			body("devMessage", containsString("br.com.matrix.idioma.config.ResourceNotFoundException"));
													
	}	

	
}
