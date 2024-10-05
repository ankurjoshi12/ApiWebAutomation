package apiConfig;

import org.apache.log4j.Logger;

import BaseSetting.BaseController;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiRequestSpecification extends BaseController{
	public static Logger logger = Logger.getLogger(ApiRequestSpecification.class);
	 static RequestSpecBuilder specBuilder = new RequestSpecBuilder();
	public static RequestSpecification getRequestSpecification(){
		logger.info("Suite and Server Name : "+SERVER +" , suiteName : "+SUITE_NAME);
		return specBuilder.setBaseUri(SERVER)
				.addHeader("User-Agent", "MyApp/1.0")
				.addHeader("Accept" , "application/json")
				.addHeader("Content-Type" , "application/json")
				.build().log().all();
	}
}
