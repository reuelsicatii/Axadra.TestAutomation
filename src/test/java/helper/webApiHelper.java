package helper;

import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import com.google.gson.JsonParser;


public class webApiHelper{
	
	public static JsonPath jp;
	public static String Token;
	public static Response Resp;
	public static Properties properties;
	public static RequestSpecBuilder reqB;
	public static JsonParser parser;

	
	
    public static String randomgenerator() {
    	return UUID.randomUUID().toString().replace("-", "");

    }
    
	public static String GetPropertValue(String Path, String Key)
	 {
		 
		properties = new Properties();
		FileInputStream fis;
			try {
				fis = new FileInputStream(Path);
				 properties.loadFromXML(fis);
				}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}	
			
		return properties.getProperty(Key);
		 
	 }
	
	
    public static RequestSpecBuilder getRequestSpecBuilder() {
    	return 	new RequestSpecBuilder();

    }
	

}
