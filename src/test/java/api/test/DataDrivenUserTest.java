package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenUserTest {
	
	@Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
	
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response respones =UserEndPoint.createUser(userpayload);
		respones.then().log().all();
		
		Assert.assertEquals(respones.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName)
	{
		Response respone = UserEndPoint.deleteUser(userName);
		
		
		Assert.assertEquals(respone.getStatusCode(), 200);
	}

}
