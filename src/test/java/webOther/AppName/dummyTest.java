package webOther.AppName;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import helper.webAppContextDriver;
import net.minidev.json.JSONArray;

public class dummyTest {

	@Test
	void main() throws IOException {
		File file = new File(
				System.getProperty("user.dir") + "\\\\Data\\\\webApp.SEOR.webAudit\\\\webAuditReportVerbiages.json");
		String json = new String(Files.readAllBytes(file.toPath()));
		JSONArray test = JsonPath.read(json, "$.['Usability']['Page Speed Insights']['For Improvement']");

		// System.out.println(test.get(0).toString());
		// assertEquals(test.get(0).toString(), "Description Here");

		// System.out.println(test);

		for (int i = 0; i < test.size(); i++) {

			System.out.println(test.get(i));

		}
	}

}
