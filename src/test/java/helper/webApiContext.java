package helper;

import java.util.LinkedHashMap;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class webApiContext {


	private OkHttpClient okHttpClient;
	private Request.Builder requestBuilder;
	private Request request;
	private RequestBody requestBody;
	private Response response;

	private String fullURL;
	

	private String baseURL;
	private String basePath;
	private LinkedHashMap<String, String> baseParameter;
	
	private Headers responseHeader;
	private String responseBody;
	private String responseMessage;
	

	private Scenario scenario;
	private ExtentTest extentTestFeature;
	private ExtentTest extentTestScenario;
	

	public Request.Builder getRequestBuilder() {
		return requestBuilder;
	}

	public void setRequestBuilder(Request.Builder requestBuilder) {
		this.requestBuilder = requestBuilder;
	}

	public String getFullURL() {
		return fullURL;
	}

	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
	}
	
	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public LinkedHashMap<String, String> getBaseParameter() {
		return baseParameter;
	}

	public void setBaseParameter(LinkedHashMap<String, String> baseParameter) {
		this.baseParameter = baseParameter;
	}
	
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}

	public void setOkHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	public Headers getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(Headers responseHeader) {
		this.responseHeader = responseHeader;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public ExtentTest getExtentTestFeature() {
		return extentTestFeature;
	}

	public void setExtentTestFeature(ExtentTest extentTestFeature) {
		this.extentTestFeature = extentTestFeature;
	}

	public ExtentTest getExtentTestScenario() {
		return extentTestScenario;
	}

	public void setExtentTestScenario(ExtentTest extentTestScenario) {
		this.extentTestScenario = extentTestScenario;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}
