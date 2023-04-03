package webOther.AppName;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Then;

public class dummy2 {

	SoftAssert softAssetion = new SoftAssert();

	@Then("Test HardAssert01 {string} {string} {string}")
	public void testHardAsset01(String string, String string2, String string3) {

		// hardAssertion 01
		try {
			assertEquals(string, "true");
		} catch (Exception e) {
			System.out.println("hardAssertion 01: FAILED");
		}

		// hardAssertion 02
		try {
			assertEquals(string2, "true");
		} catch (Exception e) {
			System.out.println("hardAssertion 02: FAILED");
		}

		// hardAssertion 03
		try {
			assertEquals(string3, "true");
		} catch (Exception e) {
			System.out.println("hardAssertion 03: FAILED");
		}

	}

	@Then("Test HardAssert02 {string} {string} {string}")
	public void testHardAsset02(String string, String string2, String string3) {

		// hardAssertion 01
		try {
			assertEquals(string, "true");
		} catch (Exception e) {
			System.out.println("hardAssertion 01: FAILED");
		}

		// hardAssertion 02
		try {
			assertEquals(string2, "true");
		} catch (Exception e) {
			System.out.println("hardAssertion 02: FAILED");
		}

		// hardAssertion 03
		try {
			assertEquals(string3, "true");
		} catch (Exception e) {
			System.out.println("hardAssertion 03: FAILED");
		}

	}

	@Then("Test SoftAsset01 {string} {string} {string}")
	public void testSoftAsset01(String string, String string2, String string3) {

		// softAssertion 01
		softAssetion.assertEquals(string, "true", "softAssertion01 01");

		// softAssertion 02
		softAssetion.assertEquals(string, "true", "softAssertion01 02");

		// softAssertion 03
		softAssetion.assertEquals(string, "true", "softAssertion01 03");

	}

	@Then("Test SoftAsset02 {string} {string} {string}")
	public void testSoftAsset02(String string, String string2, String string3) {

		// softAssertion 01
		softAssetion.assertEquals(string, "true", "softAssertion02 01");

		// softAssertion 02
		softAssetion.assertEquals(string, "true", "softAssertion02 02");

		// softAssertion 03
		softAssetion.assertEquals(string, "true", "softAssertion02 03");

	}

	@Then("Test SoftAsset03")
	public void testSoftAsset03() {

		softAssetion.assertAll();

	}

}
