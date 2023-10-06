package testAuto.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;

import helper.webAppContext;

public class CommonService {

	public void attachedScreenshotToReport(String url, webAppContext context)
			throws IOException, ClassNotFoundException {

		String date = new SimpleDateFormat("_yyMMdd_HHmmssSSS").format(new Date());

		// XAMPP htdocs Folder - Image not resolving
		// ====================================================
		String DestFile = "C:/xampp/htdocs/AutomationProject/screenshots/mockUp/" + date
				+ url.replaceFirst("^https?://", "").replaceAll("\\.com$", "").replace("/", "") + ".png";
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

		/*
		context.getExtentTestScenario()
			.createNode("<div class = \"step pass-bg\" style = \"background-color: white\">\r\n" 
					+ "Captured ScreenShot"
					+"</div>")
				.info("<div class = \"step pass-bg\" style = \"background-color: white\">\r\n" + 
						"<span>  ========================================  </span>\r\n" + 				
						"<div class = \"row mb-3\">  \r\n" + 
						"<div class = \"col-md-3\">\r\n" + 
						"<img \r\n" + 
						"	 data - featherlight = \""+ DestFile.replace("C:/xampp/htdocs", "") + "\" \r\n" + 
						"	 src = \"" + DestFile.replace("C:/xampp/htdocs", "") +"\">\r\n" + 		
						"</div>\r\n" + 
						"</div>\r\n" + 
						"</div>");
		*/
	}

}
