package br.com.matrix.idioma.modelRestAssured;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.matrix.idioma.model.security.AppUser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.bytebuddy.utility.RandomString;

 
public class UserRestAssured {
	String idTeste;
	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080/";
	}
	
	@Test
	public void postResquestUserWhenBody() {
		AppUser user = new AppUser();
		user.setEmail("mario@unicarioca.edu.br");		
		user.getPersonData().setFirstName("Mario");
		user.setPassword("123456");
		
		Response response = RestAssured.given().
		contentType("application/json").
		accept("application/json").		 
		body(user).
		when().
		post("/user").
		then().
		statusCode(201). 
		contentType("application/json").
		extract().
		response();
		
		String id = response.jsonPath().getString("id");
		idTeste = id;
		assertNotNull(id);
}
	
	
	
	@Test
	public void getResquestUser(){		
		RestAssured.given().
		when().
			get("/user/2").
		then().
			statusCode(200).
			body("id", is(2)).
			body("login", notNullValue()).
			body("password", notNullValue()).
			body("email", notNullValue()).
			body("name", notNullValue());										
	}

	
	@Test
	public void deleteUser() {
		AppUser user = new AppUser();
		user.setEmail("mario@unicarioca.edu.br");
		user.getPersonData().setFirstName("Mario");
		user.setPassword("123456");
		
		Response response = RestAssured.given().
		contentType("application/json").
		accept("application/json").		 
		body(user).
		when().
		post("/user").
		then().
		statusCode(201). 
		contentType("application/json").
		extract().
		response();
		
		Long id = response.jsonPath().getLong("id");
		
		RestAssured.given().pathParam("id", id).
		when().delete("/user/{id}").then().statusCode(200);
		
		
	}
	@Test 
	public void getResquest() {
		RestAssured.given().
		when().get("/user/6565656").
		then().
		statusCode(404).
		body("detail",containsString("Usuário não existe"));
		
		
	}

	
	
}
