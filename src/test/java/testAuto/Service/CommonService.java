package testAuto.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;

import helper.webAppContext;

public class CommonService {

	public void attachedScreenshotToReport(String url, webAppContext context) throws IOException {

		String date = new SimpleDateFormat("_yyMMdd_HHmmssSSS").format(new Date());

		// XAMPP htdocs Folder - Image not resolving
		// ====================================================
		String DestFile = "C:/xampp/htdocs/AutomationProject/screenshots/mockUp/" + date
				+ url.replaceFirst("^https?://", "").replaceAll("\\.com$", "").replace("/","") + ".png";
		context.setSrcFile(((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.FILE));

		// SrcFile = ((TakesScreenshot)
		// context.getDriver()).getScreenshotAs(OutputType.FILE);

		// Generating and Copying Screenshot to DestFile
		FileUtils.copyFile(context.getSrcFile(), new File(DestFile));

		// Attaching screenshot to Cucumber Report
		context.getScenario().attach(FileUtils.readFileToByteArray(context.getSrcFile()), "image/png",
				context.getScenario().getStatus().toString());

		// Attached Screenshot to Extent Report
		context.getExtentTestScenario().createNode(" ======================================== ").info(
				"Captured Screenshot: ",
				MediaEntityBuilder.createScreenCaptureFromPath(DestFile.replace("C:/xampp/htdocs", "")).build());

	}

}
