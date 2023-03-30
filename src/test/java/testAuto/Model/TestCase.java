package testAuto.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class TestCase {

	private String TestSuiteName;
	private String TestCaseName;
	private String TestCaseStatusDesc;
	private int TestCaseStatusValue;
	private String TestCaseTimeStamp;

	public TestCase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestCase(String testSuiteName, String testCaseName, String testCaseStatusDesc, int testCaseStatusValue,
			String testCaseTimeStamp) {
		TestSuiteName = testSuiteName;
		TestCaseName = testCaseName;
		TestCaseStatusDesc = testCaseStatusDesc;
		TestCaseStatusValue = testCaseStatusValue;
		TestCaseTimeStamp = testCaseTimeStamp;
	}

	public String getTestSuiteName() {
		return TestSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		TestSuiteName = testSuiteName;
	}

	public String getTestCaseName() {
		return TestCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		TestCaseName = testCaseName;
	}

	public String getTestCaseStatusDesc() {
		return TestCaseStatusDesc;
	}

	public void setTestCaseStatusDesc(String testCaseStatusDesc) {
		TestCaseStatusDesc = testCaseStatusDesc;
	}

	public int getTestCaseStatusValue() {
		return TestCaseStatusValue;
	}

	public void setTestCaseStatusValue(int testCaseStatusValue) {
		TestCaseStatusValue = testCaseStatusValue;
	}

	public String getTestCaseTimeStamp() {
		return TestCaseTimeStamp;
	}

	public void setTestCaseTimeStamp(String testCaseTimeStamp) {
		TestCaseTimeStamp = testCaseTimeStamp;
	}

	@Override
	public String toString() {
		return "TestCase [TestSuiteName=" + TestSuiteName + ", TestCaseName=" + TestCaseName + ", TestCaseStatusDesc="
				+ TestCaseStatusDesc + ", TestCaseStatusValue=" + TestCaseStatusValue + ", TestCaseTimeStamp="
				+ TestCaseTimeStamp + "]";
	}

	public List<TestCase> createObject(File jsonfile) throws Throwable {

		// Read JSON Report - Layer 1
		List<TestCase> TestCases = new ArrayList<TestCase>();
		List<String> TestSuiteScenarioCaseNames = JsonPath.read(jsonfile, "$..elements[*]..id");
		for (int i = 0; i < TestSuiteScenarioCaseNames.size(); i++) {
			String[] TestSuiteScenarioCaseNamesSplit = TestSuiteScenarioCaseNames.get(i).toString().split(";");
			TestCase[] TestCase = new TestCase[TestSuiteScenarioCaseNames.size()];

			// Read JSON Report - Layer 2
			List<String> TestStepStatuses = JsonPath.read(jsonfile, "$..elements[" + i + "]..steps[*]..status");

			int TestCaseStatusValue = 1;
			String TestCaseStatusDesc = "passed";
			for (int x = 0; x < TestStepStatuses.size(); x++) {
				if (TestStepStatuses.get(x).toString().contentEquals("failed")
						|| TestStepStatuses.get(x).toString().contentEquals("skipped")) {
					TestCaseStatusDesc = "failed";
					TestCaseStatusValue = 0;
					break;
				}
			}

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			TestCase[i] = new TestCase(TestSuiteScenarioCaseNamesSplit[0].toUpperCase(),
					TestSuiteScenarioCaseNamesSplit[1].toUpperCase() + "_"
							+ (Integer.valueOf(TestSuiteScenarioCaseNamesSplit[3]) - 1),
					TestCaseStatusDesc, TestCaseStatusValue, sdf.format(date));

			TestCases.add(TestCase[i]);
		}

		return TestCases;

	}

}
