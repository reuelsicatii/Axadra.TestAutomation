package webApi.ApiName;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helper.webApiHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getMethod extends webApiHelper {

	@BeforeMethod
	public void setup() {

		// setUp function here
		System.out.println("BEFORE METHOD - GET");
		System.out.println("================================================");
	}

	@Test
	public void listAllUser() {

		// Create GET Request
		// ===========================================
		RestAssured.baseURI = "https://reqres.in/api/users";
		RequestSpecification httpRequest = RestAssured.given(); 
		Response response = httpRequest.get();

		// Store Response
		// ===========================================
		System.out.println("List All  User");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
		System.out.println(response.prettyPrint());

	}

	
	@Test
	public void listOneUser() {

		// Create GET Request
		// ===========================================
		RestAssured.baseURI = "https://reqres.in/api/users/2";
		RequestSpecification httpRequest = RestAssured.given(); 
		Response response = httpRequest.get();

		// Store Response
		// ===========================================
		System.out.println("List One User");
		System.out.println("Thread ID: " + Thread.currentThread().getId());
		System.out.println(response.prettyPrint());

	}
	@AfterMethod
	public void tearDown() {

		// tearDown function here
		System.out.println("AFTER METHOD - GET");
		System.out.println("================================================");

	}

}
