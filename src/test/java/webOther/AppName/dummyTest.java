package webOther.AppName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class dummyTest {

	@Test
	void main() throws IOException {
		File file = new File(
				System.getProperty("user.dir") + "\\\\Data\\\\webApp.SEOR.webAudit\\\\webAuditReportVerbiages.json");
		String json = new String(Files.readAllBytes(file.toPath()));

		// true if for array
		// =========================
		if (true) {
			JSONArray test = JsonPath.read(json, "$.['Usability']['Page Speed Insights']['For Improvement']");

			for (int i = 0; i < test.size(); i++) {
				System.out.println(test.get(i));
			}
		} else {
			String test = JsonPath.read(json, "$.['Usability']['Mobile Friendliness']['Critical']");
			System.out.println(test);
		}

	}

}
