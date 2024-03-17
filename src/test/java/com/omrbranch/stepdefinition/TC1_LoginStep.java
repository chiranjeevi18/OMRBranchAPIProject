package com.omrbranch.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.omrbranch.base.BaseClass_API;
import com.omrbranch.endpoints.EndPoints;
import com.omrbranch.login.pojo.Login_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass_API{
	Response response;
	static GlobalDatas globalDatas = new GlobalDatas();
	@Given("User add header")
	public void userAddHeader() {
		addHeader("accept", "application/json");
	}
	@When("User add basic authentication for Login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {
		addBasicAuth(getProperityFileValue("username"), getProperityFileValue("password"));
	}
	@When("User send {string} request for Login endpoint")
	public void userSendRequestForLoginEndpoint(String type) {
		response = addReqType(type, EndPoints.LOGIN);
		int statusCode = getResponseCode(response);
		System.out.println(statusCode);
		globalDatas.setStatusCode(statusCode);
		System.out.println(getAsString(response));
	}
	
	@Then("User should verify the login response body firstName present as {string} and get the LogToken saved")
	public void userShouldVerifyTheLoginResponseBodyFirstNamePresentAsAndGetTheLogTokenSaved(String expFirstName) {
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String actFirstName = login_Output_Pojo.getData().getFirst_name();
		System.out.println(actFirstName);
		String logtoken = login_Output_Pojo.getData().getLogtoken();
		TC1_LoginStep.globalDatas.setLogToken(logtoken);
		Assert.assertEquals("Verify First Name", actFirstName, expFirstName);
		
	}



}
