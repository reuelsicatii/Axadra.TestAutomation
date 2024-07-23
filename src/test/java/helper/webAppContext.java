package helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;

public class webAppContext {

	
	private RemoteWebDriver driver;
	private WebDriverWait wait;
	private FluentWait fluentWait;
	private Scenario scenario;
	private SoftAssert softAssert;
	private ExtentTest extentTestFeature;
	private ExtentTest extentTestScenario;
	private File SrcFile;
	private HashMap<String, Long> loadTime = new HashMap<String, Long>();
	private HashMap<String, String> textExecutionDetails = new HashMap<String, String>();

	public HashMap<String, Long> getLoadTime() {
		return loadTime;
	}

	public ExtentTest getExtentTestFeature() {
		return extentTestFeature;
	}

	public void setExtentTestFeature(ExtentTest extentTestFeature) {
		this.extentTestFeature = extentTestFeature;
	}

	public ExtentTest getExtentTestScenario() {
		return extentTestScenario;
	}

	public void setExtentTestScenario(ExtentTest extentTestScenario) {
		this.extentTestScenario = extentTestScenario;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	@SuppressWarnings("rawtypes")
	public FluentWait getFluentWait() {
		return fluentWait;
	}

	@SuppressWarnings("rawtypes")
	public void setFluentWait(FluentWait fluentWait) {
		this.fluentWait = fluentWait;
	}

	public SoftAssert getSoftAssert() {
		return softAssert;
	}

	public void setSoftAssert() {
		this.softAssert = new SoftAssert();
	}

	public File getSrcFile() {
		return SrcFile;
	}

	public void setSrcFile(File SrcFile) {
		this.SrcFile = SrcFile;
	}

	public HashMap<String, String> getTextExecutionDetails() {
		return textExecutionDetails;
	}



}
