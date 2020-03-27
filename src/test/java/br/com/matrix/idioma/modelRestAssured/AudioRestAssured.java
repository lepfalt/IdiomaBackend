package br.com.matrix.idioma.modelRestAssured;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AudioRestAssured {

	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080/";
	}
	
	@Test
	public void getRequestAudioById() {
		
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
	public void postRequestCreateAudio() {
		
		Map<String, Object> audioDetails = new HashMap<>();
		 
		audioDetails.put("link",  "http://google.com.br/");
		audioDetails.put("title", "Casa Branca");
		audioDetails.put("description", "A house very cool");
		audioDetails.put("duration",  "00:01:03");
		audioDetails.put("englishTranscription","http://google.com.br/"); 
		audioDetails.put("portugueseTranscription", "http://google.com.br/");
		
		Response response = RestAssured.given().
		contentType("application/json").
		accept("application/json").		 
		body(audioDetails).
		when().
		post("/audio").
		then().
		statusCode(201). 
		contentType("application/json").
		extract().
		response();
		
		String id = response.jsonPath().getString("id");
		assertNotNull(id);
		
		
	}
	
	@Test
	public void postRequestCreateAndDeleteById() {
		
		Map<String, Object> audioDetails = new HashMap<>();
		 
		audioDetails.put("link",  "http://google.com.br/");
		audioDetails.put("title", "Casa Branca");
		audioDetails.put("description", "Saco cheio");
		audioDetails.put("duration",  "00:01:03");
		audioDetails.put("englishTranscription","http://leskas.com"); 
		audioDetails.put("portugueseTranscription", "http://leskas.com");
		
		
		Response response = RestAssured.
				given().
				contentType("application/json").				
				accept("application/json").				
				body(audioDetails).
				when().
				post("/audio").
				then().
				statusCode(201). 
				contentType("application/json").
				extract().
				response();
		
		Long id = response.jsonPath().getLong("id")	;	
		RestAssured.given().pathParam("id", id).
		when().delete("/audio/{id}").then().statusCode(200);
		
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
			body("devMessage", containsString("br.com.matrix.idioma.config.ResourceNotFoundException"));
													
	}	

}
