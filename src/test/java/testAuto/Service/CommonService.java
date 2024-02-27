package testAuto.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;

import helper.webAppContext;

public class CommonService {

	public void attachedScreenshotToReport(String description, webAppContext context)
			throws IOException, ClassNotFoundException {

		String date = new SimpleDateFormat("_yyMMdd_HHmmssSSS").format(new Date());

		// XAMPP htdocs Folder - Image not resolving
		// ====================================================
		String DestFile = "C:/xampp/htdocs/AutomationProject/screenshots/mockUp/" + date
				+ description.replaceFirst("^https?://", "").replaceAll("\\.com$", "").replace("/", "") + ".png";
		context.setSrcFile(((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.FILE));

		// SrcFile = ((TakesScreenshot)
		// context.getDriver()).getScreenshotAs(OutputType.FILE);

		// Generating and Copying Screenshot to DestFile
		FileUtils.copyFile(context.getSrcFile(), new File(DestFile));

		// Attaching screenshot to Cucumber Report
		// context.getScenario().attach(FileUtils.readFileToByteArray(context.getSrcFile()), "image/png",
		// 		context.getScenario().getStatus().toString());

		
		// Attached Screenshot to Extent Report
		// context.getExtentTestScenario().createNode(" ===================== Actual Result =================== ").info(
		//		"Captured Screenshot: " + description,
		//		MediaEntityBuilder.createScreenCaptureFromPath(DestFile.replace("C:/xampp/htdocs", "")).build());
		
		
		// Attached Screenshot to Extent Report
		context.getExtentTestScenario().createNode(""
				+ "<div style=\"display: flex;\">\r\n"
				+ "        <div style=\"flex: 1;\r\n"
				+ "            border: 1px solid #ccc;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            background-color: #f0f0f0;\r\n"
				+ "            max-width: 50%;\">\r\n"
				+ "            <h2>Expected Result</h2>\r\n"
				+ 			   "<img data-featherlight=\"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true\" src=\"https://github.com/reuelsicatii/Axadra.TestAutomation/blob/master/screenshots/ExpectedResult.jpg?raw=true\">\r\n"
				+ "        </div>\r\n"
				+ "        <div style=\"flex: 1;\r\n"
				+ "            border: 1px solid #ccc;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            background-color: #e0e0e0;\r\n"
				+ "            max-width: 50%;\">\r\n"
				+ "            <h2>Actual Result</h2>\r\n"
				+ 			   "<img data-featherlight="
				+			   "\"" + DestFile.replace("C:/xampp/htdocs", "") + "\""
				+ 			   "src="
				+				"\"" + DestFile.replace("C:/xampp/htdocs", "") + "\""
				+				">\r\n"					
				+ "        </div>\r\n"
				+ "</div>");
		
		context.getExtentTestScenario().createNode("<hr>");

	}

	public String generateRandomString(int length) throws Throwable {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

		return generator.generate(length);

	}

	public Integer generateNumber(int start, int end) throws Throwable {
		Random random = new Random();

		return start + random.nextInt(end);

	}

}
