package webApp.SEOR;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class webAppHook extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;
	private String DestFile, SrcImage;

	private ExtentTest featureExtentTest;
	private ExtentTest scenarioExtentTest;
	private ExtentTest stepExtentTest;

	public webAppHook(webAppContext context) {
		super();
		this.context = context;
	}

	// Declare Report Instance
	// ==========================================

	private static ExtentSparkReporter extentSparkReporter;
	private static ExtentReports extentReports = new ExtentReports();
	private static String scenarioName;

	@BeforeAll
	public static void beforeAll() throws ClassNotFoundException {

		System.out.println("Im in a BeforeAll Scenario");
		System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());

	}

	@Before
	public void before(Scenario scenario) throws ClassNotFoundException {
		System.out.println("Im in a Before Scenario");
		System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());
		context.setScenario(scenario);

		// Set SoftAssert
		context.setSoftAssert();
		scenarioName = scenario.getSourceTagNames().toArray()[0].toString().replace("@", "");

		// Set Feature Name
		featureExtentTest = extentReports.createTest(new GherkinKeyword("Feature"),
				"Feature Name: " + scenario.getSourceTagNames().toArray()[0].toString().replace("@", "") + "<br>"
						+ " Scenario Name: " + scenario.getName() + "<br>" + "TestCase ID: " + scenario.getLine(),
				"<br><br><br>" + " Scenario Name: " + scenario.getName());


		// Set Test Scenario and Case Name
		scenarioExtentTest = featureExtentTest.createNode(new GherkinKeyword("Scenario"),
				" Scenario Name: " + scenario.getName() + "<br>" + "TestCase ID: " + scenario.getLine());		
		context.setExtentTestScenario(scenarioExtentTest);

		// Set Test Step
		context.getExtentTestScenario().createNode(
				"<div style=\"padding: 2px 5px 5px 5px;text-align: center; margin: 25px 25px 25px 25px; border-bottom-style: solid; \">\r\n"
				+ "   <h5> Test Steps <h5>\r\n"
				+ "</div>");

	}

	@BeforeStep
	public void beforeStep() throws IOException, ClassNotFoundException {
		
		System.out.println("Im in a Before StepDefination");

	}

	@AfterStep
	public void afterStep(Scenario scenario) throws IOException, ClassNotFoundException {

		System.out.println("Im in a AfterStep StepDefination");

	}

	@After
	public void after() throws IOException {
		System.out.println("Im in a After Scenario");
		System.out.println("AfterScenario - Thread ID" + Thread.currentThread().getId());

		context.getDriver().close();
		context.getDriver().quit();
		// context.getSoftAssert().assertAll();
	}

	@AfterAll
	public static void afterAll() {

		System.out.println("Im in a After Scenario");

		// Create Extent Report over XAMPP htdocs Folder
		// ==============================================================================
		String date = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
		extentSparkReporter = new ExtentSparkReporter(
				"C:/xampp/htdocs/AutomationProject/reports/" + scenarioName + "/" + date + ".html");
		extentReports.attachReporter(extentSparkReporter);
		extentReports.flush();

		// SLACK NOTIFICATION
		OkHttpClient client = new OkHttpClient();
		
		System.err.println(extentReports.getReport().toString());

		// JSON payload as a string
		String jsonPayload = "{\"text\": \" SELENIUM - Automation" + "\\n ===================== " + "\\n Feature Name: "
				+ scenarioName + "\\n Report Link: http://automation-report.cloud/AutomationProject/reports/" + scenarioName + "/"
				+ date + ".html" 
				+ "\\n Test Scenario - Status: " + extentReports.getReport().getStats().getParent()
				+ "\\n Test Case - Status: " + extentReports.getReport().getStats().getChild()
				+ "\\n Test Step - Status: " + extentReports.getReport().getStats().getGrandchild() + " \"}";

		RequestBody requestBody = RequestBody.create(jsonPayload, MediaType.get("application/json"));
		Request request = new Request.Builder()
				.url("https://hooks.slack.com//services/T94TNR6JX/B05TF33U3SM/GNYWxDN6wq8xP0P1Zsnov49a")
				.post(requestBody).build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				String responseBody = response.body().string();
				System.out.println("Response: " + responseBody);
			} else {
				System.err.println("Request failed with status code: " + response.code());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
