package webApp.COMP;

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
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import helper.webAppContextDriver;
import helper.webAppHelper;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class webAppHook extends webAppHelper {

	// Declare Driver Instance
	// ==========================================
	private webAppContextDriver context;
	private String DestFile, SrcImage;

	private ExtentTest featureExtentTest;
	private ExtentTest scenarioExtentTest;

	public webAppHook(webAppContextDriver context) {
		super();
		this.context = context;
	}

	private static ExtentSparkReporter extentSparkReporter;
	private static ExtentReports extentReports = new ExtentReports();
	private static String scenarioName;

	@BeforeAll
	public static void beforeALl() throws ClassNotFoundException {

		System.out.println("Im in a BeforeAll Scenario");
		System.out.println("BeforeScenario - Thread ID" + Thread.currentThread().getId());

		// Define Extent Report XAMPP htdocs Folder - Image not resolving
		// ==============================================================================
		// extentSparkReporter = new ExtentSparkReporter("C:/xampp/htdocs/AutomationProject/reports/COMPASS_TestSuite/"
		//		+ new SimpleDateFormat("yyMMdd_HHmmss").format(new Date()) + ".html");
		// extentReports.attachReporter(extentSparkReporter);

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
		featureExtentTest = extentReports
				.createTest(new GherkinKeyword("Feature"),
						"Feature Name: " + scenario.getSourceTagNames().toArray()[0].toString().replace("@", "") 
						+ "<br>"
						+ " Scenario Name: " + scenario.getName() 
						+ "<br>"
						+ "TestCase ID: " + scenario.getLine(),
						"<br><br><br>"
						+ " Scenario Name: " + scenario.getName());

		// Set Test Scenario and Case Name
		scenarioExtentTest = featureExtentTest.createNode(new GherkinKeyword("Scenario"),
				" Scenario Name: " + scenario.getName()
				+ "<br>" 
				+ "TestCase ID: " + scenario.getLine(), scenario.getId());
		context.setExtentTestScenario(scenarioExtentTest);

	}

	@BeforeStep
	public void beforeStep() throws IOException, ClassNotFoundException {
		System.out.println("Im in a Before StepDefination");

	}

	@AfterStep
	public void afterStep(Scenario scenario) throws IOException, ClassNotFoundException {

		System.out.println("Im in a AfterStep StepDefination");

		try {

			/*
			 * 
			 * DestFile = System.getProperty("user.dir") + "\\screenshots\\" +
			 * scenario.getSourceTagNames().toArray()[0].toString().replace("@", "") + "_" +
			 * new SimpleDateFormat("_yyMMdd_HHmmss").format(new Date()) + ".png";
			 * 
			 * SrcFile = ((TakesScreenshot)
			 * context.getDriver()).getScreenshotAs(OutputType.FILE);
			 * 
			 * // Generating and Copying Screenshot to DestFile FileUtils.copyFile(SrcFile,
			 * new File(DestFile));
			 * 
			 * // Attaching screenshot to Cucumber Report
			 * context.getScenario().attach(FileUtils.readFileToByteArray(SrcFile),
			 * "image/png", context.getScenario().getStatus().toString());
			 * 
			 * // Attached Screenshot to Extent Report context.getExtentTestScenario().
			 * createNode(" ======================================== ")
			 * .info("Captured Screenshot: ",
			 * MediaEntityBuilder.createScreenCaptureFromPath(DestFile).build());
			 * 
			 */
			
			Random generator = new Random();
			int randomIndex = generator.nextInt(2000);
			Thread.sleep(randomIndex);

			String date = new SimpleDateFormat("_yyMMdd_HHmmssSSS").format(new Date());

			// XAMPP htdocs Folder - Image not resolving
			// ====================================================
			DestFile = "C:/xampp/htdocs/AutomationProject/screenshots/"
					+ scenario.getSourceTagNames().toArray()[0].toString().replace("@", "") + "_"
					+ date + ".png";

			SrcImage = "/AutomationProject/screenshots/"
					+ scenario.getSourceTagNames().toArray()[0].toString().replace("@", "") + "_"
					+ date + ".png";

			context.setSrcFile(((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.FILE));

			// SrcFile = ((TakesScreenshot)
			// context.getDriver()).getScreenshotAs(OutputType.FILE);

			// Generating and Copying Screenshot to DestFile
			FileUtils.copyFile(context.getSrcFile(), new File(DestFile));

			// Attaching screenshot to Cucumber Report
			context.getScenario().attach(FileUtils.readFileToByteArray(context.getSrcFile()), "image/png",
					context.getScenario().getStatus().toString());

			// Attached Screenshot to Extent Report
			context.getExtentTestScenario().createNode(" ======================================== ")
					.info("Captured Screenshot: ", MediaEntityBuilder.createScreenCaptureFromPath(DestFile.replace("C:/xampp/htdocs", "")).build());

		} catch (Exception e) {
			// Extent Report
			context.getExtentTestScenario().createNode(" ======================================== ")
					.warning(e.getMessage());
		}

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
		extentSparkReporter = new ExtentSparkReporter("C:/xampp/htdocs/AutomationProject/reports/"
				+ scenarioName + "/" + new SimpleDateFormat("yyMMdd_HHmmss").format(new Date()) + ".html");
		extentReports.attachReporter(extentSparkReporter);
		extentReports.flush();

	}

}
