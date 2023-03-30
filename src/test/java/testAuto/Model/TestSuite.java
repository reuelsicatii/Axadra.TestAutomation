package testAuto.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class TestSuite {

	private String TestSuiteName;
	private String TestSuiteStatusDesc;
	private int TestSuiteStatusValue;
	private String TestSuiteTimeStamp;

	public TestSuite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestSuite(String testSuiteName, String testSuiteStatusDesc, int testSuiteStatusValue,
			String testSuiteTimeStamp) {
		TestSuiteName = testSuiteName;
		TestSuiteStatusDesc = testSuiteStatusDesc;
		TestSuiteStatusValue = testSuiteStatusValue;
		TestSuiteTimeStamp = testSuiteTimeStamp;
	}

	public String getTestSuiteName() {
		return TestSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		TestSuiteName = testSuiteName;
	}

	public String getTestSuiteStatusDesc() {
		return TestSuiteStatusDesc;
	}

	public void setTestSuiteStatusDesc(String testSuiteStatusDesc) {
		TestSuiteStatusDesc = testSuiteStatusDesc;
	}

	public int getTestSuiteStatusValue() {
		return TestSuiteStatusValue;
	}

	public void setTestSuiteStatusValue(int testSuiteStatusValue) {
		TestSuiteStatusValue = testSuiteStatusValue;
	}

	public String getTestSuiteTimeStamp() {
		return TestSuiteTimeStamp;
	}

	public void setTestSuiteTimeStamp(String testSuiteTimeStamp) {
		TestSuiteTimeStamp = testSuiteTimeStamp;
	}

	@Override
	public String toString() {
		return "TestSuite [TestSuiteName=" + TestSuiteName + ", TestSuiteStatusDesc=" + TestSuiteStatusDesc
				+ ", TestSuiteStatusValue=" + TestSuiteStatusValue + ", TestSuiteTimeStamp=" + TestSuiteTimeStamp + "]";
	}

	public TestSuite createObject(File jsonfile) throws Throwable {

		// Read JSON Report - Layer 1
		String TestSuiteScenarioCaseNames = JsonPath.read(jsonfile, "$[0]['name']");
		List<String> TestStepStatuses = JsonPath.read(jsonfile, "$..elements[*]..steps[*]..status");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String TestSuiteStatusDesc = "passed";
		int TestSuiteStatusValue = 1;
		for (int x = 0; x < TestStepStatuses.size(); x++) {
			if (TestStepStatuses.get(x).toString().contentEquals("failed")
					|| TestStepStatuses.get(x).toString().contentEquals("skipped")) {
				TestSuiteStatusDesc = "failed";
				TestSuiteStatusValue = 0;
				// System.out.println(TestStepStatuses.get(x).toString());
				break;
			}
		}

		TestSuite TestSuite = new TestSuite(TestSuiteScenarioCaseNames, TestSuiteStatusDesc, TestSuiteStatusValue,
				sdf.format(date));
		return TestSuite;
	}
}
