package webApi.LUM;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import helper.webApiContext;
import io.cucumber.java.en.And;
import testAuto.Service.CommonService;

public class addParameterStep {

	CommonService commonService = new CommonService();

	// Declare Driver Instance
	// ==========================================
	private webApiContext context;

	public addParameterStep(webApiContext context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@And("I add formBody Key as {string} and Value as {string}")
	public void iAddFormBodyKeyAndValue(String key, String value) throws Throwable {

		try {

			if (value.equals("dynamicNumber")) {

				context.getFormBuilder().add(key, commonService.generateNumber(1000000, 9999999).toString());

			}

			else if (value.equals("dynamicURL")) {
				context.getFormBuilder().add(key, "https://www." + commonService.generateRandomString(6) + ".com/"
						+ commonService.generateRandomString(10));

			}

			else if (value.equals("SemSig")) {

				long epochTimestamp = (long) Math.ceil(System.currentTimeMillis() / 60000);
				String epochTimestampString = epochTimestamp + "";
				String input = "84479" + epochTimestampString + "6ca7b6b6aba867d";

				// Create a MessageDigest instance with SHA-256 algorithm
				MessageDigest digest = MessageDigest.getInstance("SHA-256");

				// Get the byte representation of the input string
				byte[] hashBytes = digest.digest(input.getBytes());

				// Convert the byte array to a hexadecimal string
				StringBuilder hexStringBuilder = new StringBuilder();
				for (byte b : hashBytes) {
					String hex = String.format("%02X", b);
					hexStringBuilder.append(hex);
				}

				context.getFormBuilder().add(key, hexStringBuilder.toString().toLowerCase());

			}

			else {
				context.getFormBuilder().add(key, value);
			}

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("Given"), "I add formBody Key as " + key + " and Value as " + value)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"),
								"I add formBody Key as " + key + " and Value as " + value)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I add parameter Key as {string} and Value as {string}")
	public void iAddParameterKeyAndValue(String key, String value)
			throws MalformedURLException, ClassNotFoundException {

		try {

			context.getBaseParameter().put(key, value);

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("Given"), "I add parameter Key as " + key + " and Value as " + value)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"),
								"I add parameter Key as " + key + " and Value as " + value)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I remove parameter Key as {string} and Value as {string}")
	public void iRemoveParameterKeyAndValue(String key, String value)
			throws MalformedURLException, ClassNotFoundException {

		try {

			context.getBaseParameter().remove(key);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"),
					"I remove parameter Key as " + key + " and Value as " + value).pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"),
								"I remove parameter Key as " + key + " and Value as " + value)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I update the JSON {string} whose {string} field is {string}")
	public void iUpdateTheRequestBodyWhoseFieldIs(String requestBodyPath, String requestBodyField,
			String requestBodyValue) throws Throwable {

		try {

			// Read the existing JSON file
			// ==========================================
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(new File(System.getProperty("user.dir") + requestBodyPath));

			// Update the value of a nested property
			// ==============================================================
			// Split the property path into individual property names
			String[] propertyNames = requestBodyField.split("\\.");

			// Traverse the JSON structure to find the nested property
			JsonNode currentNode = rootNode;

			for (int i = 0; i < propertyNames.length; i++) {

				if (currentNode.has(propertyNames[i]) && currentNode.get(propertyNames[i]).isArray()) {
					JsonNode ArrayNode = currentNode.get(propertyNames[i]);

					for (JsonNode indexArrayNode : ArrayNode) {

						if (indexArrayNode.has(propertyNames[i + 1])) {

							if (requestBodyValue.equals("dynamicURL")) {

								((ObjectNode) indexArrayNode).put(propertyNames[i + 1],
										"https://www." + commonService.generateRandomString(6) + ".com/"
												+ commonService.generateRandomString(10));

							} else {

								((ObjectNode) indexArrayNode).put(propertyNames[i + 1], requestBodyValue);

							}

						}

						else {
							System.out.println("Property field not found in links array element.");
							break;
						}
					}

					break;
				}

				else if (currentNode.has(propertyNames[i])) {

					if (requestBodyValue.equals("dynamicURL")) {

						System.err.println("iUpdateTheRequestBodyWhoseFieldIs -- 23");
						((ObjectNode) currentNode).put(propertyNames[i],
								"https://www." + commonService.generateRandomString(6) + ".com/"
										+ commonService.generateRandomString(10));

					} else {

						System.err.println("iUpdateTheRequestBodyWhoseFieldIs -- 24");
						((ObjectNode) currentNode).put(propertyNames[i], requestBodyValue);

					}

					break;
				}

				else {
					// Handle the case where the property is missing
					System.out.println("Property not found: " + propertyNames[i]);
					break;
				}

			}

			System.err.println("iUpdateTheRequestBodyWhoseFieldIs  -- 3");
			// Write the updated JSON data back to the file
			// =====================================================================
			objectMapper.writeValue(new File(System.getProperty("user.dir") + requestBodyPath), currentNode);

			System.err.println("iUpdateTheRequestBodyWhoseFieldIs  -- 4");

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"),
					"I update the " + requestBodyPath + " whose " + requestBodyField + " field is " + requestBodyValue)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I update the "
						+ requestBodyPath + " whose " + requestBodyField + " field is " + requestBodyValue)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I add the JSON {string} to the request")
	public void iAddTheRequestBodyToTheRequest(String requestBodyPath)
			throws MalformedURLException, ClassNotFoundException {

		try {

			// Set requestBody JSON
			context.setRequestBodyJSON(
					new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + requestBodyPath)),
							StandardCharsets.UTF_8));

			// Extent Report
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("Given"), "I add " + requestBodyPath + " to the request")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I add " + requestBodyPath + " to the request")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I add the formBody to the request")
	public void iAddTheFormBodyToTheRequest() throws MalformedURLException, ClassNotFoundException {

		try {

			// Set requestBody FORM
			context.setRequestBodyForm(context.getFormBuilder().build());

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I add formBody to the request")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I add formBody to the request")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
