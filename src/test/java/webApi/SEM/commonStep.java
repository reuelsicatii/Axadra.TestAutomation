package webApi.SEM;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webApiContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class commonStep {

	// Declare Driver Instance
	// ==========================================
	private webApiContext context;

	public commonStep(webApiContext context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@Given("I set a request")
	public void iSetARequest() throws MalformedURLException, ClassNotFoundException {

		try {

			context.setRequestBuilder(new Request.Builder());
			context.setOkHttpClient(new OkHttpClient());
			context.setBaseParameter(new LinkedHashMap<String, String>());
			context.setFormBuilder(new FormBody.Builder());

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I set a request").pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I set a request")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I set the baseURL to {string}")
	public void iSetTheBaseURLTo(String baseURL) throws MalformedURLException, ClassNotFoundException {

		try {

			context.setBaseURL(baseURL);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I set the baseURL to " + baseURL)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I set the baseURI to " + baseURL)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I reset the baseURL")
	public void iResetTheBaseURL() throws MalformedURLException, ClassNotFoundException {

		try {

			context.setBaseURL("");

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I reset the baseURL")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I reset the baseURL")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I set the basePath to {string}")
	public void iSetTheBasePathTo(String basePath) throws MalformedURLException, ClassNotFoundException {

		try {

			context.setBasePath(basePath);

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I set the basePath to " + basePath)
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I set the basePath to " + basePath)
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@And("I reset the basePath")
	public void iResetTheBasePath() throws MalformedURLException, ClassNotFoundException {

		try {

			context.setBasePath("");

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I reset the basePath")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I reset the basePath")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
