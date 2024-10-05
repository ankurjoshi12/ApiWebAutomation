package LoginApi;

import EndPoints.ApiEndpoints;
import apiConfig.ApiRequestSpecification;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class login {

	/**
	 * 
	 * @param payload 
	 * @return
	 */
	public Response LoginApi(String payload) {
		return RestAssured
		.given(ApiRequestSpecification.getRequestSpecification())
//		.body(payload)
		.get(ApiEndpoints.fact)
		.then().extract().response();

	}
}
