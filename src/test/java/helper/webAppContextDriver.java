package helper;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;

public class webAppContextDriver {

	private RemoteWebDriver driver;
	private WebDriverWait wait;
	private Scenario scenario;
	private SoftAssert softAssert;
	private ExtentTest extentTestFeature;
	private ExtentTest extentTestScenario;

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

	public SoftAssert getSoftAssert() {
		return softAssert;
	}

	public void setSoftAssert() {
		this.softAssert = new SoftAssert();
	}

}
