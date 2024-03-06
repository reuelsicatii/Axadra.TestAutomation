package testAuto.Service;

import java.io.IOException;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpReqService {

	public String checkURLStatus200(String URL) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Response response = null;
		String URLStatus = null;

		if (URL.contains("http") || URL.contains("http")) {

			// Create a request object with the URL
			Request request = new Request.Builder().url(URL).build();

			try {
				// Execute the request and get the response
				response = client.newCall(request).execute();

				// Check if the response is successful
				if (response.isSuccessful()) {

					URLStatus = Integer.toString(response.code());

				} else {

					// Print the error message if the request was not successful
					System.out.println("Error: " + response.code() + " " + response.message());

				}
			} catch (IOException e) {
				// Print any exceptions that occur during the request
				e.printStackTrace();
			}

		}

		else {

			URLStatus = "Invalid URL";
		}

		return URLStatus;

	}

}
