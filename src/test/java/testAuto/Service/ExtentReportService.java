package testAuto.Service;

import java.util.ArrayList;
import com.aventstack.extentreports.GherkinKeyword;
import helper.webAppContext;

public class ExtentReportService {

	public void insertPassedStep(webAppContext context, String title, ArrayList<String> details) throws ClassNotFoundException {

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

	public void insertFailedStep(webAppContext context, String title, ArrayList<String> details) throws ClassNotFoundException {

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

	public void insertWarningStep(webAppContext context, String title, ArrayList<String> details) throws ClassNotFoundException {

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

	public void insertInfoStep(webAppContext context, String title, ArrayList<String> details) throws ClassNotFoundException {

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

}
