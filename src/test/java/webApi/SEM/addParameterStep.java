package webApi.SEM;

import java.net.MalformedURLException;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webApiContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import okhttp3.Request;

public class addParameterStep {

	// Declare Driver Instance
	// ==========================================
	private webApiContext context;

	public addParameterStep(webApiContext context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

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
			context.getExtentTestScenario()
					.createNode(new GherkinKeyword("Given"), "I remove parameter Key as " + key + " and Value as " + value)
					.pass("PASSED");

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

}
