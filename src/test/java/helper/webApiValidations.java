//package helper;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.junit.Test;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//import com.squareup.okhttp.*;
//import cucumber.Framework.WEBHelper;
//
//
//public class webApiValidations extends WEBHelper {
//	Date date = new Date();
//	SimpleDateFormat sdf =new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
//	String timeStamp = sdf.format(date);
//	public ExtentReports extent;
//	public ExtentTest test ;
//
//	@Test
//	public void FSRValidation() throws IOException
//	 {
//		extent= new ExtentReports("target/FSR_Valid_"+timeStamp.replaceAll("[^\\w]", "_")+".html", true);
//		FSRValidation_1_5();
//		FSRValidation_3();
//	 }
//	
//	
//	
//	public void FSRValidation_1_5() throws IOException {
//		String URL="http://www.gqaustralia.edu.au/v1.5/handler.php?MAX_FILE_SIZE=4194304&fname=TEST777&has_quals=no&mobile=123456789&quals_recent=&industry=Automotive&experience=3%20to%205%20years&vouhcer=No&lname=TEST777&zc_gad=&state=123456&specific_source=&qual_demanded=Not%20Sure&uploadedResume=no&email=TEST%40gmail.com&heardAboutUs=TV&experience_place=Australian%20and%20Overseas";
//		 
//		  
//		
//		OkHttpClient client = new OkHttpClient();
//		
//		 test = extent.startTest("FSR1.5 Validation");
//		Request request = new Request.Builder()
//		  .url(URL)
//		  .post(null)
//		  .addHeader("cache-control", "no-cache")
//		  .addHeader("postman-token", "2b039c0e-307a-eb5d-bb8f-2f5606a9e4b8")
//		  .build();
//
//		Response response = client.newCall(request).execute();
//		if(response.code()==200)
//    	{
//    		//System.out.println("URL link Resopnse Code:200");
//    		
//    		 test.log(LogStatus.PASS,"FSR 1.5 Response validation and Response Codes is "+ response.code());
//    		 log.info("FSR 1.5 Response validation and Response Codes is 200");
//    	}
//    	
//    	else
//    	{
//    	//logger=logger+"::"+url+"("+Responsecode+")";
//   		 test.log(LogStatus.FAIL,"FSR 1.5 Response validation and Response Codes is "+ response.code());
//   		 log.warn("FSR 1.5 Response validation and Response Codes is "+ response.code());
//    
//    	}
//		
//		 extent.endTest(test);
//		  extent.flush();
//	}
//	
//	
//	public void FSRValidation_3() throws IOException {
//		String URL="http://fsrapi.gqaustralia.edu.au/front/response?id=550&token=58733e0202fe63.17873949&data%5B0%5D%5Bquestion_id%5D=3934&data%5B0%5D%5Banswer%5D=&data%5B1%5D%5Bquestion_id%5D=3935&data%5B1%5D%5Banswer%5D=Test123&data%5B2%5D%5Bquestion_id%5D=3936&data%5B2%5D%5Banswer%5D=test123&data%5B3%5D%5Bquestion_id%5D=3937&data%5B3%5D%5Banswer%5D=wqer%40gmail.com&data%5B4%5D%5Bquestion_id%5D=3938&data%5B4%5D%5Banswer%5D=Automotive&data%5B5%5D%5Bquestion_id%5D=3939&data%5B5%5D%5Banswer%5D=4%20-%206%20Years&data%5B6%5D%5Bquestion_id%5D=3943&data%5B6%5D%5Banswer%5D=Both&data%5B7%5D%5Bquestion_id%5D=3944&data%5B7%5D%5Banswer%5D=0412345678&utm_source=&utm_campaign=&utm_medium=&utm_term=&utm_content=";
//		 
//		  
//		
//		OkHttpClient client = new OkHttpClient();
//		
//		 test = extent.startTest("FSR3.0 Validation");
//		Request request = new Request.Builder()
//		  .url(URL)
//		  .post(null)
//		  .addHeader("cache-control", "no-cache")
//		  .addHeader("postman-token", "5990cb2d-d8c1-afe1-0e4c-bcc7922e8d7e")
//		  .build();
//
//		Response response = client.newCall(request).execute();
//		if(response.code()==200)
//    	{
//    		//System.out.println("URL link Resopnse Code:200");
//    		
//    		 test.log(LogStatus.PASS,"FSR 3.0 Response validation and Response Codes is "+ response.code());
//    		 log.info("FSR 3.0 Response validation and Response Codes is 200");
//    	}
//    	
//    	else
//    	{
//    	//logger=logger+"::"+url+"("+Responsecode+")";
//   		 test.log(LogStatus.FAIL,"FSR 3.0 Response validation and Response Codes is "+ response.code());
//   		 log.warn("FSR 3.0 Response validation and Response Codes is "+ response.code());
//    
//    	}
//		
//		 extent.endTest(test);
//		  extent.flush();
//	}
//	
//}
