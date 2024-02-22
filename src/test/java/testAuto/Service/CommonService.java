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
		context.getScenario().attach(FileUtils.readFileToByteArray(context.getSrcFile()), "image/png",
				context.getScenario().getStatus().toString());

		// Attached Screenshot to Extent Report
		context.getExtentTestScenario().createNode("<hr>");
		context.getExtentTestScenario().createNode(" ===================== Actual Result =================== ").info(
				"Captured Screenshot: " + description,
				MediaEntityBuilder.createScreenCaptureFromPath(DestFile.replace("C:/xampp/htdocs", "")).build());

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
