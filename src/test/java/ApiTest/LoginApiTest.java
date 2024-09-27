package ApiTest;

import SignupTest.UserRequestyPojo;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LoginApi.login;

import java.io.IOException;
import java.util.Properties;

public class LoginApiTest {
	
	@DataProvider(name = "testDS")
	public Object[][] credentials(){
		return new Object[][]
				{
			{"payload1"},
			{"payload2"}
		};
	}

	@Test(groups = {"regression","sanity"} , dataProvider = "testDS" , dataProviderClass = LoginApiTest.class )
	public void clickLogin(String payload) {
		try{
		login log = new login();
		Response res = log.LoginApi(payload);
		System.out.println("Log api request : "+res.getBody().asString());
		String factKey = null ;
		Reporter.log("" , false);

			factKey = res.as(UserRequestyPojo.class).getFact();
			System.out.println("Fact Value  : "+factKey);
		}
		catch (Exception e){
			System.out.println("change in api contract");
			e.printStackTrace();
			Assert.assertTrue(false , "test");
		}

//		JsonPath path =  res.jsonPath();
//		int count =  path.getInt("info.count") ;
//		Reporter.log("Count is : "+count);s
	}
	@Test(groups={"property"})
	public void click() throws IOException {
		Properties prop = new Properties();
		prop.load(LoginApiTest.class.getResourceAsStream("/user.properties"));
		System.out.println("Properties values : "+prop.getProperty("name"));
		Reporter.log("Name "+prop.getProperty("name"));
		Reporter.log("place "+prop.getProperty("place"));
		Reporter.log("country "+prop.getProperty("country"));
	}
}
