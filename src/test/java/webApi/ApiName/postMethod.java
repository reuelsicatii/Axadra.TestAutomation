package webApi.ApiName;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import helper.webApiHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postMethod extends webApiHelper {

	@BeforeMethod
	public void setup() {

		// setUp function here
		System.out.println("BEFORE METHOD - POST");
		System.out.println("================================================");
	}

	@Test
	public void createUser() {

		// Create POST Request
		// ===========================================
		RestAssured.baseURI = "https://reqres.in/api/users";
		RequestSpecification httpRequest = RestAssured.given(); 
		JsonObject requestParams = new JsonObject(); 
		requestParams.addProperty("name", "morpheus"); 
		requestParams.addProperty("job", "leader"); 
		httpRequest.header("Content-Type", "application/json"); 
		httpRequest.body(requestParams.toString()); 
		Response response = httpRequest.post();
		
		
		// Store Response
		// ===========================================
		System.out.println("Create User");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
		System.out.println(response.prettyPrint());

	}
	
	@Test
	public void registerUser() {

		// Create POST Request
		// ===========================================
		RestAssured.baseURI = "https://reqres.in/api/register";
		RequestSpecification httpRequest = RestAssured.given(); 
		JsonObject requestParams = new JsonObject(); 
		requestParams.addProperty("email", "eve.holt@reqres.in"); 
		requestParams.addProperty("password", "pistol"); 
		httpRequest.header("Content-Type", "application/json"); 
		httpRequest.body(requestParams.toString()); 
		Response response = httpRequest.post();
		
		
		// Store Response
		// ===========================================
		System.out.println("Register User");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
		System.out.println(response.prettyPrint());

	}

	@AfterMethod
	public void tearDown() {

		// tearDown function here
		System.out.println("AFTER METHOD - POST");
		System.out.println("================================================");

	}

}
