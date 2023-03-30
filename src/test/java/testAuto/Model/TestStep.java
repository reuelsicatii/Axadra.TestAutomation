package testAuto.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class TestStep {

	private String TestSuiteName;
	private String TestCaseName;
	private String TestStepName;
	private String TestStepLocation;
	private String TestStepStatusDesc;
	private int TestStepStatusValue;
	private String TestStepTimeStamp;

	public TestStep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestStep(String testSuiteName, String testCaseName, String testStepName, String testStepLocation,
			String testStepStatusDesc, int testStepStatusValue, String testStepTimeStamp) {
		this.TestSuiteName = testSuiteName;
		this.TestCaseName = testCaseName;
		this.TestStepName = testStepName;
		this.TestStepLocation = testStepLocation;
		this.TestStepStatusDesc = testStepStatusDesc;
		this.TestStepStatusValue = testStepStatusValue;
		this.TestStepTimeStamp = testStepTimeStamp;
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

	public String getTestStepName() {
		return TestStepName;
	}

	public void setTestStepName(String testStepName) {
		TestStepName = testStepName;
	}

	public String getTestStepLocation() {
		return TestStepLocation;
	}

	public void setTestStepLocation(String testStepLocation) {
		TestStepLocation = testStepLocation;
	}

	public String getTestStepStatusDesc() {
		return TestStepStatusDesc;
	}

	public void setTestStepStatusDesc(String testStepStatusDesc) {
		TestStepStatusDesc = testStepStatusDesc;
	}

	public int getTestStepStatusValue() {
		return TestStepStatusValue;
	}

	public void setTestStepStatusValue(int testStepStatusValue) {
		TestStepStatusValue = testStepStatusValue;
	}

	public String getTestStepTimeStamp() {
		return TestStepTimeStamp;
	}

	public void setTestStepTimeStamp(String testStepTimeStamp) {
		TestStepTimeStamp = testStepTimeStamp;
	}

	@Override
	public String toString() {
		return "TestStep [TestSuiteName=" + TestSuiteName + ", TestCaseName=" + TestCaseName + ", TestStepName="
				+ TestStepName + ", TestStepLocation=" + TestStepLocation + ", TestStepStatusDesc=" + TestStepStatusDesc
				+ ", TestStepStatusValue=" + TestStepStatusValue + ", TestStepTimeStamp=" + TestStepTimeStamp + "]";
	}

	public List<TestStep> createObject(File jsonfile) throws Throwable {

		// Read JSON Report - Layer 1
		List<TestStep> TestSteps = new ArrayList<TestStep>();
		List<String> TestSuiteScenarioCaseNames = JsonPath.read(jsonfile, "$..elements[*]..id");
		for (int i = 0; i < TestSuiteScenarioCaseNames.size(); i++) {

			String[] TestSuiteScenarioCaseNamesSplit = TestSuiteScenarioCaseNames.get(i).toString().split(";");

			// Read JSON Report - Layer 2
			List<String> TestStepNames = JsonPath.read(jsonfile, "$..elements[" + i + "]..steps[*].name");
			List<String> TestStepLocations = JsonPath.read(jsonfile, "$..elements[" + i + "]..steps[*]..location");
			List<String> TestStepStatuses = JsonPath.read(jsonfile, "$..elements[" + i + "]..steps[*]..status");

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			TestStep[] TestStep = new TestStep[TestStepNames.size()];
			for (int x = 0; x < TestStepNames.size(); x++) {
				
				int TestCaseStatusValue = 1;
				String TestCaseStatusDesc = "passed";
				if (TestStepStatuses.get(x).toString().contentEquals("failed")
						|| TestStepStatuses.get(x).toString().contentEquals("skipped")) {
					TestCaseStatusDesc = "failed";
					TestCaseStatusValue = 0;
				}
				
				
				TestStep[x] = new TestStep(
						TestSuiteScenarioCaseNamesSplit[0].toUpperCase(),
						TestSuiteScenarioCaseNamesSplit[1].toUpperCase() + "_" + (Integer.valueOf(TestSuiteScenarioCaseNamesSplit[3]) - 1),
						TestStepNames.get(x).toString(), 
						TestStepLocations.get(x).toString(),
						TestCaseStatusDesc, 
						TestCaseStatusValue, 
						sdf.format(date));

				TestSteps.add(TestStep[x]);
			}

		}

		return TestSteps;
	}

}
