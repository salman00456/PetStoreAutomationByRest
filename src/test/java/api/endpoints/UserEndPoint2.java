package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint2 {
	
	public static ResourceBundle getUrl()
	{
		//This line of code will load the properties file and return the url.
		ResourceBundle routes= ResourceBundle.getBundle("configuration");
		return routes;
	}
	

	public static Response createUser(User payload)
	{
		String post_url = getUrl().getString("post_url");
		
		Response respones =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);
		
		return respones;
		
	}
	
	public static Response readUser(String userName) 
	{
		String get_url = getUrl().getString("get_url");
		
		Response respones =given()
		.pathParam("username",userName )
		.when()
		.get(get_url);
		
		return respones;
		
	}
	
	public static Response updateUser(String userName,User payload)
	{
		String put_url = getUrl().getString("put_url");
		
	  Response	respones =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(put_url);
	  
	  return respones;
		
		
	}
	
	public static Response deleteUser(String userName) 
	{
		String delete_url = getUrl().getString("delete_url");
		
		Response respones =given()
		.pathParam("username",userName )
		.when()
		.get(delete_url);
		
		return respones;
		
}
	
	
}
