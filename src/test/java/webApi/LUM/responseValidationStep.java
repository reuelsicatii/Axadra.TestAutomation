package webApi.LUM;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import helper.webApiContext;
import io.cucumber.java.en.And;

public class responseValidationStep {

	// Declare Driver Instance
	// ==========================================
	private webApiContext context;

	public responseValidationStep(webApiContext context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@And("I validate response status against {string}")
	public void iValidateResponseStatusSgainst(String expectedStatus)
			throws MalformedURLException, ClassNotFoundException {

		try {

			String actualStatus = Integer.toString(context.getResponse().code());

			if (actualStatus.equals(expectedStatus)) {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I validate response status against " + expectedStatus)
						.pass("PASSED");
			}

			else {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I validate response status against " + expectedStatus)
						.fail("FAILED");

			}

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I validate response status against " + expectedStatus)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I validate response body against schema {string}")
	public void iValidateResponseBodyAgainstSchema(String expectedSchemaBody)
			throws ClassNotFoundException, IOException {

		String expectedPrettyJsonRespBody = null;
		String actualPrettyJsonRespBody = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {

			// Actual pretty JSON raw
			JsonObject actualJsonRespBody = gson.fromJson(context.getResponseBody(), JsonObject.class);
			actualPrettyJsonRespBody = gson.toJson(actualJsonRespBody);

			// Expected pretty JSON Schema
			JsonObject expectedJsonRespBody = gson.fromJson(
					new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + expectedSchemaBody)),
							StandardCharsets.UTF_8),
					JsonObject.class);
			expectedPrettyJsonRespBody = gson.toJson(expectedJsonRespBody);

			// Parse JSON schema and data using Jackson ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode schemaNode = objectMapper.readTree(expectedPrettyJsonRespBody);
			JsonNode dataNode = objectMapper.readTree(actualPrettyJsonRespBody);

			// Create a JsonSchemaFactory
			JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

			// Compile the schema
			JsonSchema schema = factory.getJsonSchema(schemaNode);

		
			
			

			// Check if validation passed
			if (schema.validInstance(dataNode)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"),
								"I validate response body against schema " + expectedSchemaBody)
						.pass("PASSED" + "<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
								+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
								+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\r\n"
								+ "            max-width: 50%;\">\r\n" + "            <h2>Actual Result</h2>\r\n"
								+ "            <pre>" + actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "</div>");

			} else {
				
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"),
								"I validate response body against schema " + expectedSchemaBody)
						.fail("FAILED" 								
								+ "<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
								+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
								+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\r\n"
								+ "            max-width: 50%;\">\r\n" + "            <h2>Actual Result</h2>\r\n"
								+ "            <pre>" + actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "</div>");
			}

//			// Load the JSON schema from the file
//			try (FileReader expectedSchemaReader = new FileReader(
//					System.getProperty("user.dir") + expectedSchemaBody)) {
//
//				JSONObject expectedRawSchema = new JSONObject(new JSONTokener(expectedSchemaReader));
//
//				// Create a Schema from the loaded JSON schema
//				Schema expectedSchema = SchemaLoader.load(expectedRawSchema);
//
//				// Parse the JSON response
//				JSONObject actualJSONObject = new JSONObject(context.getResponseBody());
//
//				// Perform schema validation
//				expectedSchema.validate(actualJSONObject);
//
//				// Extent Report
//				context.getExtentTestScenario()
//						.createNode(new GherkinKeyword("Given"),
//								"I validate response body against schema " + expectedSchemaBody)
//						.pass("PASSED" + "<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
//								+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
//								+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
//								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
//								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
//								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
//								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\r\n"
//								+ "            max-width: 50%;\">\r\n" + "            <h2>Actual Result</h2>\r\n"
//								+ "            <pre>" + actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
//								+ "</div>");
//
//			} catch (ValidationException e) {
//
//				// Extent Report
//				context.getExtentTestScenario()
//						.createNode(new GherkinKeyword("Given"),
//								"I validate response body against schema " + expectedSchemaBody)
//						.fail("FAILED: " + "<br>" + e.getMessage() + "<br>" + "<div style=\"display: flex\">\r\n"
//								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
//								+ "            padding: 20px;\r\n" + "            background-color: #f0f0f0;\">\r\n"
//								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
//								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
//								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
//								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\">\r\n"
//								+ "            <h2>Actual Result</h2>\r\n" + "            <pre>"
//								+ actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n" + "</div>");
//
//			}
		} catch (Exception e) {

			try {
				/// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"),
								"I validate response body against schema " + expectedSchemaBody)
						.fail("FAILED:" + "<br>" + e.getMessage() + "<br>" + "<div style=\"display: flex\">\r\n"
								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
								+ "            padding: 20px;\r\n" + "            background-color: #f0f0f0;\">\r\n"
								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\">\r\n"
								+ "            <h2>Actual Result</h2>\r\n" + "            <pre>"
								+ actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n" + "</div>");
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@And("I validate response body against raw {string}")
	public void iValidateResponseBodyAgainstRaw(String expectedBody) throws ClassNotFoundException, IOException {

		String expectedPrettyJsonRespBody = null;
		String actualPrettyJsonRespBody = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {

			// Actual Pretty JSON
			JsonObject actualJsonRespBody = gson.fromJson(context.getResponseBody(), JsonObject.class);
			actualPrettyJsonRespBody = gson.toJson(actualJsonRespBody);

			// Expected Pretty JSON
			JsonObject expectedJsonRespBody = gson
					.fromJson(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + expectedBody)),
							StandardCharsets.UTF_8), JsonObject.class);
			expectedPrettyJsonRespBody = gson.toJson(expectedJsonRespBody);

			// Actual JSON
			JSONObject actualJSONObject = new JSONObject(context.getResponseBody());

			// Expected JSON
			JSONObject expectedJSONObject = new JSONObject(
					new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + expectedBody)),
							StandardCharsets.UTF_8));

			if (expectedJSONObject.similar(actualJSONObject)) {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I validate response body against raw " + expectedBody)
						.pass("PASSED" + "<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
								+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
								+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\r\n"
								+ "            max-width: 50%;\">\r\n" + "            <h2>Actual Result</h2>\r\n"
								+ "            <pre>" + actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "</div>");

			} else {

				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I validate response body against raw " + expectedBody)
						.fail("FAILED" + "<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
								+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
								+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
								+ "            <h2>Expected Result</h2>\r\n" + "            <pre>"
								+ expectedPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
								+ "            padding: 20px;\r\n" + "            background-color: #e0e0e0;\r\n"
								+ "            max-width: 50%;\">\r\n" + "            <h2>Actual Result</h2>\r\n"
								+ "            <pre>" + actualPrettyJsonRespBody + "</pre>\r\n" + "        </div>\r\n"
								+ "</div>");

			}

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I validate response body against raw " + expectedBody)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) { // TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
