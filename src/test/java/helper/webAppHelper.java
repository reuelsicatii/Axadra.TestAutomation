package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class webAppHelper {

	public static Properties properties;

	public static String GetPropertValue(String Path, String Key) {

		properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(Path);
			properties.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties.getProperty(Key);

	}

	public RemoteWebDriver initializeBrowser(String browserName) throws MalformedURLException {
		RemoteWebDriver driver = null;
		DesiredCapabilities dc = new DesiredCapabilities();

		if (GetPropertValue("data/TestProperties.xml", "Grid").equalsIgnoreCase("Browser")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				
				// this option is for the pop-up blocker in PROD
				ChromeOptions options = new ChromeOptions();
		        options.addArguments("--load-extension=" + "C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\cjpalhdlnbpafiamejdnhcphjbkeiagm\\1.52.0_0");		        
		        dc.setCapability(ChromeOptions.CAPABILITY, options);
		        
		        
				dc.setBrowserName("chrome");

			} else if (browserName.equalsIgnoreCase("firefox")) {
				dc.setBrowserName("firefox");

			} else if (browserName.equalsIgnoreCase("edge")) {
				dc.setBrowserName("MicrosoftEdge");

			} else if (browserName.equalsIgnoreCase("oepra")) {
				dc.setBrowserName("opera");

			} else if (browserName.equalsIgnoreCase("ie")) {
				dc.setBrowserName("ie");
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444"), dc);

		} else if (GetPropertValue("data/TestProperties.xml", "Grid").equalsIgnoreCase("BrowserStack")) {
			dc.setCapability("os", GetPropertValue("Data/TestProperties.xml", "os"));
			dc.setCapability("os_version", GetPropertValue("Data/TestProperties.xml", "os_version"));
			dc.setCapability("browser", browserName);
			dc.setCapability("browser_version", GetPropertValue("Data/TestProperties.xml", "browser_version"));
			dc.setCapability("name", "BrowserStack - Grid AutoDemo");

			driver = new RemoteWebDriver(
					new URL("https://licenses_l5aIeD:QYTmGxxvTJ9bxLAERqYU@hub-cloud.browserstack.com/wd/hub"), dc);

		}

		return driver;
	}

	public WebDriverWait initializeBrowserWait(RemoteWebDriver driver, long duration) throws MalformedURLException {

		return new WebDriverWait(driver, Duration.ofSeconds(duration));

	}

	@SuppressWarnings("rawtypes")
	public FluentWait initializeFluentWait(RemoteWebDriver driver) throws MalformedURLException {

		FluentWait<RemoteWebDriver> fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(org.openqa.selenium.NoSuchElementException.class);

		return fluentWait;

	}

	public void getScreenshot(RemoteWebDriver driver, Scenario scenario) throws IOException {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmssa");
		String timeStamp = sdf.format(date);
		String DestFile = System.getProperty("user.dir") + "\\screenshots\\" + scenario.getName() + "_" + timeStamp
				+ ".png";

		File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File(DestFile));

	}

	public String getWebAuditReportVerbiages() throws IOException {
		// Object Declarations
		// ==========================================
		File file = new File(
				System.getProperty("user.dir") + "\\Data\\webApp.SEOR.webAudit\\webAuditReportVerbiages.json");
		String json = new String(Files.readAllBytes(file.toPath()));

		return json;
	}

	public String getGbpScorerReportVerbiages() throws IOException {
		// Object Declarations
		// ==========================================
		File file = new File(
				System.getProperty("user.dir") + "\\Data\\webApp.SEOR.gbpScorer\\gbpScorerReportVerbiages.json");
		String json = new String(Files.readAllBytes(file.toPath()));

		return json;
	}

}
