package br.com.matrix.idioma;

import io.restassured.RestAssured;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.CoreMatchers.containsString;


import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.BeforeClass;
import org.junit.Test;

public class RestAssuredAPITest {

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	@Test
	public void getResquestAudioOK(){
		
		RestAssured.given().
		when().
			get("/audio/1").
		then().
			statusCode(200).
			body("id", is(1)).
			body("link", notNullValue()).
			body("description", notNullValue()).
			body("title", notNullValue()).
			body("duration", notNullValue()).
			body("creationDate", notNullValue()).
			body("englishTranscription", notNullValue()).
			body("portugueseTranscription", notNullValue());										
	}

	@Test
	public void getResquestUserWhenBodyOk() {
		RestAssured.given().
		when().
			get("/user/1").
			then().statusCode(200)
			.body("email", containsString("mario@unicarioca.edu.br"))
			.body("login", containsString("mario"))
			.body("name", containsString("Mario"))
			.body("password", containsString("123456"));			
	}

	
	@Test
	public void getResquestUserOK(){		
		RestAssured.given().
		when().
			get("/user/1").
		then().
			statusCode(200).
			body("id", is(1)).
			body("login", notNullValue()).
			body("password", notNullValue()).
			body("email", notNullValue()).
			body("name", notNullValue());										
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
	public void getResquestAudioNotFound(){
		
		RestAssured.given().
		when().
			get("/audio/999999999").
		then().
			statusCode(404).
			body("title", containsString("org.springframework.web.servlet.PageNotFound")).
			body("detail", containsString("O audio não existe.")).
			body("timeStamp", notNullValue()).			
			body("devMensagem", containsString("br.com.matrix.idioma.config.ResourceNotFoundException"));
													
	}	

	
}
