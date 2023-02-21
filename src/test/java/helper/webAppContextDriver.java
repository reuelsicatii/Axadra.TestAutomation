package helper;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.Scenario;

public class webAppContextDriver {
	
	private RemoteWebDriver driver;
	private WebDriverWait wait;
	private Scenario scenario;
	
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


}
