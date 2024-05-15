package testAuto.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webAppContext;

public class ExtentReportService {

	private String DestFile, SrcImage;

	public void insertPassedStep(webAppContext context, String title, ArrayList<String> details)
			throws ClassNotFoundException {

		String htmlContentTitle = "<p style=\"font-weight: bold;\">" + title + " -- "
				+ "<button style=\"background-color:green; color: white;text-align: center;border-radius: 25%;\">PASSED</button></p>";
		String htmlContentDetails = "Details: ";

		if (!details.isEmpty()) {

			for (String detail : details) {
				htmlContentDetails = htmlContentDetails + "<br>" + detail;
			}
		}

		context.getExtentTestScenario().createNode(new GherkinKeyword("When"), htmlContentTitle)
				.pass(htmlContentDetails);

	}

	public void insertFailedStep(webAppContext context, String title, ArrayList<String> details)
			throws ClassNotFoundException {

		String htmlContentTitle = "<p style=\"font-weight: bold;\">" + title + " -- "
				+ "<button style=\"background-color:red; color: white;text-align: center;border-radius: 25%;\">FAILED</button></p>";
		String htmlContentDetails = "Details: ";

		if (!details.isEmpty()) {

			for (String detail : details) {
				htmlContentDetails = htmlContentDetails + "<br>" + detail;
			}
		}

		context.getExtentTestScenario().createNode(new GherkinKeyword("When"), htmlContentTitle)
				.fail(htmlContentDetails);

	}

	public void insertWarningStep(webAppContext context, String title, ArrayList<String> details)
			throws ClassNotFoundException {

		String htmlContentTitle = "<p style=\"font-weight: bold;\">" + title + " -- "
				+ "<button style=\"background-color:orange; color: white;text-align: center;border-radius: 25%;\">WARNING</button></p>";
		String htmlContentDetails = "Details: ";

		if (!details.isEmpty()) {

			for (String detail : details) {
				htmlContentDetails = htmlContentDetails + "<br>" + detail;
			}
		}

		context.getExtentTestScenario().createNode(new GherkinKeyword("When"), htmlContentTitle)
				.warning(htmlContentDetails);

	}

	public void insertInfoStep(webAppContext context, String title, ArrayList<String> details)
			throws ClassNotFoundException {

		String htmlContentTitle = "<p style=\"font-weight: bold;\">" + title + " -- "
				+ "<button style=\"background-color:blue; color: white;text-align: center;border-radius: 25%;\">INFO</button></p>";
		String htmlContentDetails = "Details: ";

		if (!details.isEmpty()) {

			for (String detail : details) {
				htmlContentDetails = htmlContentDetails + "<br>" + detail;
			}
		}

		context.getExtentTestScenario().createNode(new GherkinKeyword("When"), htmlContentTitle)
				.info(htmlContentDetails);

	}

	public void attachedScreenshotToReport(webAppContext context, String expectedResultURL) {

		try {

			String date = new SimpleDateFormat("_yyMMdd_HHmmssSSS").format(new Date());

			// XAMPP htdocs Folder - Image not resolving
			// ====================================================
			DestFile = "C:/xampp/htdocs/AutomationProject/screenshots/"
					+ context.getScenario().getSourceTagNames().toArray()[0].toString().replace("@", "") + "_" + date
					+ ".png";

			SrcImage = "/AutomationProject/screenshots/"
					+ context.getScenario().getSourceTagNames().toArray()[0].toString().replace("@", "") + "_" + date
					+ ".png";

			context.setSrcFile(((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.FILE));

			// Generating and Copying Screenshot to DestFile
			FileUtils.copyFile(context.getSrcFile(), new File(DestFile));

			// Attached Screenshot to Extent Report
			context.getExtentTestScenario().createNode("" + "<div style=\"display: flex;\">\r\n"
					+ "        <div style=\"flex: 1;\r\n" + "            border: 1px solid #ccc;\r\n"
					+ "            padding: 20px;\r\n" + "            background-color: #f0f0f0;\r\n"
					+ "            max-width: 50%;\">\r\n" + "            <h2>Expected Result</h2>\r\n"
					+ "<img data-featherlight=" + "\"" + expectedResultURL + "\"" + "src=" + "\"" + expectedResultURL
					+ "\"" + ">\r\n" + "        </div>\r\n" + "        <div style=\"flex: 1;\r\n"
					+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
					+ "            background-color: #e0e0e0;\r\n" + "            max-width: 50%;\">\r\n"
					+ "            <h2>Actual Result</h2>\r\n" + "<img data-featherlight=" + "\""
					+ DestFile.replace("C:/xampp/htdocs", "") + "\"" + "src=" + "\""
					+ DestFile.replace("C:/xampp/htdocs", "") + "\"" + ">\r\n" + "        </div>\r\n" + "</div>");

			context.getExtentTestScenario().createNode("<hr>");

		} catch (Exception e) {
			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						" ===================== Error Message =================== ").warning(e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void attachedPageLoadToReport(webAppContext context, int expectedPageLoad, long actualPageLoad) {

		try {

			// Extent Report
			context.getExtentTestScenario()
					.createNode("<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
							+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
							+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
							+ "            <h2>Expected Page Load</h2>\r\n" + "            <pre>" + expectedPageLoad
							+ "</pre>\r\n" + "        </div>\r\n" + "        <div style=\"flex: 1;\r\n"
							+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
							+ "            background-color: #e0e0e0;\r\n" + "            max-width: 50%;\">\r\n"
							+ "            <h2>Actual Page Load</h2>\r\n" + "            <pre>" + actualPageLoad
							+ "</pre>\r\n" + "        </div>\r\n" + "</div>");

		} catch (

		Exception e) {
			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						" ===================== Error Message =================== ").warning(e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public void attachedFinishLoadToReport(webAppContext context, int expectedPageLoad, long actualPageLoad) {

		try {

			// Extent Report
			context.getExtentTestScenario()
					.createNode("<div style=\"display: flex;\">\r\n" + "        <div style=\"flex: 1;\r\n"
							+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
							+ "            background-color: #f0f0f0;\r\n" + "            max-width: 50%;\">\r\n"
							+ "            <h2>Expected Page Load</h2>\r\n" + "            <pre>" + expectedPageLoad
							+ "</pre>\r\n" + "        </div>\r\n" + "        <div style=\"flex: 1;\r\n"
							+ "            border: 1px solid #ccc;\r\n" + "            padding: 20px;\r\n"
							+ "            background-color: #e0e0e0;\r\n" + "            max-width: 50%;\">\r\n"
							+ "            <h2>Actual Page Load</h2>\r\n" + "            <pre>" + actualPageLoad
							+ "</pre>\r\n" + "        </div>\r\n" + "</div>");

		} catch (

		Exception e) {
			// Extent Report
			try {
				context.getExtentTestScenario().createNode(new GherkinKeyword("When"),
						" ===================== Error Message =================== ").warning(e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
