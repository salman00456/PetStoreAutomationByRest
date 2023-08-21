package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.endpoints.UserEndPoint2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		
	Response respones =UserEndPoint2.createUser(userPayload);
	respones.then().log().all();
	Assert.assertEquals(respones.getStatusCode(), 200);
		
	}
	@Test(priority = 2)
	public void testGetUserByName()
	{
		Response respones =UserEndPoint2.readUser(this.userPayload.getUsername());
		respones.then().log().all();
		Assert.assertEquals(respones.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		Response respones=UserEndPoint2.updateUser(this.userPayload.getUsername(), userPayload);
		respones.then().log().all();
		
		Assert.assertEquals(respones.getStatusCode(), 200);
		//checking the data after updation
		Response respones_update=UserEndPoint2.updateUser(this.userPayload.getUsername(), userPayload);
		Assert.assertEquals(respones_update.getStatusCode(), 200);
		
	}
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
		Response respones = UserEndPoint2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(respones.getStatusCode(), 200);
	}

}
