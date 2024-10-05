package ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersApiTest {

    @Test(groups ={"apitest"})
    public void validateApi( ){
        JSONObject usersData = new JSONObject();
        usersData.put("name" , "morpheus");
        usersData.put("job" , "leader");
        Response res  = RestAssured.given().baseUri("https://reqres.in")
                .body(usersData.toString())
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();
        System.out.println("response : "+res.getBody().asString());
        Assert.assertTrue(res.getStatusCode()==201 ,"Api Failed ... Something Bad Happened!!" );
    }

}
