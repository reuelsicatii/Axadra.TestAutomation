package webApi.LUM;

import java.net.MalformedURLException;
import java.util.Map;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;

import helper.webApiContext;
import io.cucumber.java.en.And;

public class buildRequestStep {

	// Declare Driver Instance
	// ==========================================
	private webApiContext context;

	public buildRequestStep(webApiContext context) {
		super();
		this.context = context;
	}

	// Page Step Definition
	// =================================================

	@And("I build a {string} request")
	public void iBuildARequest(String method) throws MalformedURLException, ClassNotFoundException {

		try {

			if (method.equals("GET")) {

				// Build URL
				// ==============================
				if (!context.getBaseParameter().entrySet().isEmpty()) {

					// Build URL with Parameters
					// ==============================
					boolean isFirst = true;
					for (Map.Entry<String, String> baseParameterSet : context.getBaseParameter().entrySet()) {

						if (isFirst) {

							context.setFullURL(context.getBaseURL() + context.getBasePath() + "?"
									+ baseParameterSet.getKey() + "=" + baseParameterSet.getValue());
							isFirst = false;

						} else {
							context.setFullURL(context.getFullURL() + "&" + baseParameterSet.getKey() + "="
									+ baseParameterSet.getValue());
						}

					}

				}

				else {

					// Build URL without Parameters
					// ==============================
					context.setFullURL(context.getBaseURL() + context.getBasePath());
				}

				// Build REQ
				// ==============================
				context.setRequest(context.getRequestBuilder().url(context.getFullURL()).get().build());

				// Execute REQ
				// ==============================
				context.setResponse(context.getOkHttpClient().newCall(context.getRequest()).execute());

				// Retrieve RESP
				// ==============================
				context.setResponseHeader(context.getResponse().headers());
				context.setResponseBody(context.getResponse().body().string());
				context.setResponseMessage(context.getResponse().message());

				System.out.println("============================================");
				System.out.println("HEADER");
				System.out.println(context.getResponseHeader().toString());
				System.out.println("============================================");
				System.out.println("MESSAGE");
				System.out.println(context.getResponseMessage().toString());
				System.out.println("============================================");
				System.out.println("BODY");
				System.out.println(context.getResponseBody().toString());

			}

			else if (method.equals("POST")) {

				// Build URL
				// ==============================
				context.setFullURL(context.getBaseURL() + context.getBasePath());

				// Build REQ
				// ==============================
				context.setRequest(
						context.getRequestBuilder().url(context.getFullURL()).post(context.getRequestBody()).build());

				// Execute REQ
				// ==============================
				context.setResponse(context.getOkHttpClient().newCall(context.getRequest()).execute());

				// Retrieve RESP
				// ==============================
				context.setResponseHeader(context.getResponse().headers());
				context.setResponseBody(context.getResponse().body().string());
				context.setResponseMessage(context.getResponse().message());

				System.out.println("============================================");
				System.out.println("HEADER");
				System.out.println(context.getResponseHeader().toString());
				System.out.println("============================================");
				System.out.println("MESSAGE");
				System.out.println(context.getResponseMessage().toString());
				System.out.println("============================================");
				System.out.println("BODY");
				System.out.println(context.getResponseBody().toString());
			}

			else if (method.equals("PUT")) {

				// Build URL
				// ==============================
				context.setFullURL(context.getBaseURL() + context.getBasePath());

				// Build REQ
				// ==============================
				context.setRequest(
						context.getRequestBuilder().url(context.getFullURL()).put(context.getRequestBody()).build());

				// Execute REQ
				// ==============================
				context.setResponse(context.getOkHttpClient().newCall(context.getRequest()).execute());

				// Retrieve RESP
				// ==============================
				context.setResponseHeader(context.getResponse().headers());
				context.setResponseBody(context.getResponse().body().string());
				context.setResponseMessage(context.getResponse().message());

				System.out.println("============================================");
				System.out.println("HEADER");
				System.out.println(context.getResponseHeader().toString());
				System.out.println("============================================");
				System.out.println("MESSAGE");
				System.out.println(context.getResponseMessage().toString());
				System.out.println("============================================");
				System.out.println("BODY");
				System.out.println(context.getResponseBody().toString());
			}

			else {
				// Do Nothing
			}

			// Extent Report
			context.getExtentTestScenario().createNode(new GherkinKeyword("Given"), "I build a " + method + " request")
					.pass("PASSED");

		} catch (Exception e) {

			try {
				// Extent Report
				context.getExtentTestScenario()
						.createNode(new GherkinKeyword("Given"), "I build a " + method + " request")
						.fail("FAILED: " + e.getMessage());
				context.getExtentTestScenario().log(Status.FAIL, "Failed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
