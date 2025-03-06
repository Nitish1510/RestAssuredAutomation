package api.test;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority=1)
	public void testPostUser(){
		
		logger.info("**Creating User info..**");
		
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**User is created..**");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("**Reading User info..**");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**User info is displayed..**");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		logger.info("**Updating User info..**");
		
		//update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		//response.then().log().all();
		//response.then().log().body().statusCode(200);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		//response.then().log().all();
		//System.out.println("responseAfterUpdate : "+responseAfterUpdate.getStatusCode());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			
		logger.info("**User info is updated..**");
	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("**Deleting User info..**");
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**User info is deleted..**");
		
	}

}
