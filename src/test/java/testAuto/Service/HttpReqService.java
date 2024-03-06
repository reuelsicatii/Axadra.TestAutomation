package testAuto.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import java.io.IOException;

public class HttpReqService {

	public String checkURLStatus200(String URL) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Response response = null;
		String URLStatus = null;

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

		System.out.println("==================HttpReqService: Header==================");
		System.out.println(response.headers());
		
		System.out.println("==================HttpReqService: Body==================");
		System.out.println(response.body().string());
	
		return URLStatus;

	}

}
