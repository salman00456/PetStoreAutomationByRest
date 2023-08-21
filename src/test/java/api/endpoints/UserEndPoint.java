package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint {

	public static Response createUser(User payload)
	{
		Response respones =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return respones;
		
	}
	
	public static Response readUser(String userName) 
	{
		Response respones =given()
		.pathParam("username",userName )
		.when()
		.get(Routes.get_url);
		
		return respones;
		
	}
	
	public static Response updateUser(String userName,User payload)
	{
	  Response	respones =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(Routes.update_url);
	  
	  return respones;
		
		
	}
	
	public static Response deleteUser(String userName) 
	{
		Response respones =given()
		.pathParam("username",userName )
		.when()
		.get(Routes.delete_url);
		
		return respones;
		
}
	
	
}
