package webOther.AppName;

import org.openqa.selenium.By;

import com.aventstack.extentreports.GherkinKeyword;

public class LoopBreak {

	public static void main(String[] args) throws InterruptedException {

		int x = 0;
		while (true) {

			if (x >= 410) {

				System.out.println("FAILED: " + "WebAudit Report generation, waiting for " + x + "sec");

			}

			else if (x >= 420) {

				System.out.println("FAILED: " + "WebAudit Report generation, waiting for " + x + "sec");

				// exit the loop
				System.out.println("Exiting whileloop");
				break;

			}

			Thread.sleep(100);
			x = x + 10;
			System.out.println("WebAudit Report generation, waiting for " + x + "sec");

		}
	}

}
