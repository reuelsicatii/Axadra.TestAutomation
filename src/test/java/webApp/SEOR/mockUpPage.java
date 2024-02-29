package webApp.SEOR;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;
import helper.webAppHelper;
import io.cucumber.java.en.When;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import testAuto.Service.CommonService;
import testAuto.Service.ExtentReportService;

public class mockUpPage extends webAppHelper {

	// Page Elements
	// ==========================================

	// Declare Driver Instance
	// ==========================================
	private webAppContext context;

	public mockUpPage(webAppContext context) {
		super();
		this.context = context;
	}
	

	// Declare Services
	// ==========================================
	ExtentReportService extentReportService = new ExtentReportService();
	
	// Declare Variables
	// ==========================================
	ArrayList<String> details = new ArrayList<String>();
	String pageURL = null;
	String networkResponse = null;
	Integer statuCode = null;
	

	// Page Step Definition
	// =================================================
	@When("User loads {string} category sites")
	public void userLoadsCategorySites(String category) throws Throwable {

		ArrayList<String> urls = new ArrayList<String>();

		// Step Definition
		try {

			// Click the Category Name
			context.getDriver()
					.findElement(
							By.xpath("//div[@id='mockup-category-wrapper']//a[contains(text(), '" + category + "')]"))
					.click();

			// sleep no anchor
			Thread.sleep(5000);

			// Retrieve all URL paths
			for (int i = 0; i < context.getDriver().findElements(By.xpath("//a[text()='Live Demo']")).size(); i++) {

				urls.add(context.getDriver().findElements(By.xpath("//a[text()='Live Demo']")).get(i)
						.getAttribute("href"));
				// System.out.println(urls.toString());
			}

			OkHttpClient client = new OkHttpClient();

			for (int i = 0; i < urls.size(); i++) {			
				

				try {

					context.getDriver().get(urls.get(i));

					// sleep no anchor
					Thread.sleep(5000);

					Request request = new Request.Builder().url(urls.get(i)).get().build();
					Response response = client.newCall(request).execute();
					pageURL = urls.get(i);
					networkResponse = response.networkResponse().toString();
					statuCode = response.code();
					response.close();

					if (statuCode == 200) {

						// Extent Report				
						details.clear();
						details.add("Page URL: " + pageURL);
						details.add("Status Code: " + statuCode);
						details.add("Network Respose: " + networkResponse);
						extentReportService.insertPassedStep(context, "User loads " + urls.get(i) + " from " + category + " category", details);						

						context.getExtentTestScenario().log(Status.PASS, "PASSED");
						extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

					}

					else {

						// Extent Report
						details.clear();
						details.add("Page URL: " + pageURL);
						details.add("Status Code: " + statuCode);
						details.add("Network Respose: " + networkResponse);
						extentReportService.insertFailedStep(context, "User loads " + urls.get(i) + " from " + category + " category", details);						

						context.getExtentTestScenario().log(Status.FAIL, "FAILED");
						extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

					}

				} catch (Exception e) {
					
					// Extent Report
					details.clear();
					details.add("Page URL: " + pageURL);
					details.add("Status Code: " + statuCode);
					details.add("Network Respose: " + networkResponse);
					extentReportService.insertFailedStep(context, "User loads " + urls.get(i) + " from " + category + " category", details);					

					context.getExtentTestScenario().log(Status.FAIL, "FAILED");
					extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				}
				
			}
			
			

		} catch (Exception e) {

			// Extent Report
			try {
				
				details.clear();
				details.add("Page URL: " + pageURL);
				details.add("Status Code: " + statuCode);
				details.add("Network Respose: " + networkResponse);
				extentReportService.insertFailedStep(context, "User loads " +pageURL + " from " + category + " category", details);
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");

				context.getExtentTestScenario().log(Status.FAIL, "FAILED");
				extentReportService.attachedScreenshotToReport(context, "https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true");
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
