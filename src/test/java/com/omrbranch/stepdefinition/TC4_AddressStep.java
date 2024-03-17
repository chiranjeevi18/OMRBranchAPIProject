package com.omrbranch.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.address.AddUserAddress_Input_Pojo;
import com.omrbranch.address.AddUserAddress_Output_Pojo;
import com.omrbranch.address.DeleteUserAddress_Input_Pojo;
import com.omrbranch.address.DeleteUserAddress_Output_Pojo;
import com.omrbranch.address.GetUserAddress_Output_Pojo;
import com.omrbranch.address.UpdateAddress_Input_Pojo;
import com.omrbranch.address.UpdateAddress_Output_Pojo;
import com.omrbranch.base.BaseClass_API;
import com.omrbranch.endpoints.EndPoints;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass_API {

	Response response;

	@Given("User add header and bearer authentication for accessing address endpoint")
	public void userAddHeaderAndBearerAuthenticationForAccessingAddressEndpoint() {
		String logToken = TC1_LoginStep.globalDatas.getLogToken();
		List<Header> list = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type", "application/json");
		list.add(h1);
		list.add(h2);
		list.add(h3);
		Headers headers = new Headers(list);
		addHeaders(headers);
	}

	@When("User add request body for add new address {string},{string},{string},{string}, {int} , {int} , {int} ,{string},{string} and {string}")
	public void userAddRequestBodyForAddNewAddressAnd(String firstName, String lastName, String mobile,
			String apartment, Integer state, Integer city, Integer country, String pinCode, String address,
			String addressType) {
		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo(firstName, lastName, mobile,
				apartment, state, city, country, pinCode, address, addressType);
		addBody(address_Input_Pojo);
	}

	@When("User send {string} request for addUserAddress endpoint")
	public void userSendRequestForAddUserAddressEndpoint(String type) {
		response = addReqType(type, EndPoints.ADDUSERADDR);
		int statusCode = getResponseCode(response);
		System.out.println(getAsPrettyString(response));
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User should verify the addUserAddress response message matches {string}")
	public void userShouldVerifyTheAddUserAddressResponseMessageMatches(String expAddrAddedMsg) {
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String message = addUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		String addressID = String.valueOf(address_id);
//		String string = Integer.toString(address_id);
		TC1_LoginStep.globalDatas.setAddressID(addressID);
		Assert.assertEquals("Verify Add User Address", expAddrAddedMsg, message);
	}

	@When("User add request body to update new address {string},{string},{string},{string}, {int} , {int} , {int} ,{string},{string} and {string}")
	public void userAddRequestBodyToUpdateNewAddressAnd(String firstName, String lastName, String mobile,
			String apartment, Integer state, Integer city, Integer country, String pinCode, String address,
			String addressType) {
		String addressID = TC1_LoginStep.globalDatas.getAddressID();
		System.out.println(addressID);
		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(addressID, firstName, lastName,
				mobile, apartment, state, city, country, pinCode, address, addressType);
		addBody(updateAddress_Input_Pojo);
	}

	@When("User send {string} request for update addUserAddress endpoint")
	public void userSendRequestForUpdateAddUserAddressEndpoint(String type) {
		response = addReqType(type, EndPoints.UPDATEUSERADDR);
		int responseCode = getResponseCode(response);
		System.out.println(getAsPrettyString(response));
		TC1_LoginStep.globalDatas.setStatusCode(responseCode);
	}

	@Then("User verify the update address response message matches {string}")
	public void userVerifyTheUpdateAddressResponseMessageMatches(String expUpdatAddressMsg) {
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Verify update user address", expUpdatAddressMsg, message);
	}

	@Given("User add Headers and bearer authentication for accessing GetAddress endpoints")
	public void userAddHeadersAndBearerAuthenticationForAccessingGetAddressEndpoints() {
		String logToken = TC1_LoginStep.globalDatas.getLogToken();
		List<Header> header = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		header.add(h1);
		header.add(h2);
		Headers headers = new Headers(header);
		addHeaders(headers);
	}

	@When("User send {string} request for GetUserAddress Endpoint")
	public void userSendRequestForGetUserAddressEndpoint(String type) {
		response = addReqType(type, EndPoints.GETUSERADDR);
		int responseCode = getResponseCode(response);
		System.out.println(getAsPrettyString(response));
		TC1_LoginStep.globalDatas.setStatusCode(responseCode);
	}

	@Then("User verify the GetUserAddress response message matches {string}")
	public void userVerifyTheGetUserAddressResponseMessageMatches(String expGetUserAddressMsg) {
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String message = getUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Get User address Verified", expGetUserAddressMsg, message);
	}

	@Given("User add Headers and bearer authentication for accessing address endpoint")
	public void userAddHeadersAndBearerAuthenticationForAccessingAddressEndpoint() {
		String logToken = TC1_LoginStep.globalDatas.getLogToken();
		List<Header> list = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		Header h3 = new Header("Content-Type", "application/json");
		list.add(h1);
		list.add(h2);
		list.add(h3);
		Headers headers = new Headers(list);
		addHeaders(headers);
	}

	@When("User add request body for address ID")
	public void userAddRequestBodyForAddressID() {
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(TC1_LoginStep.globalDatas.getAddressID());
		addBody(deleteUserAddress_Input_Pojo);
	}

	@When("User send {string} request for DeleteAddress endpoint")
	public void userSendRequestForDeleteAddressEndpoint(String type) {
		response = addReqType(type, EndPoints.DELETEUSERADDR);
		int responseCode = getResponseCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(responseCode);
	}

	@Then("User Verify the DeleteAddress response message Matches {string}")
	public void userVerifyTheDeleteAddressResponseMessageMatches(String expDeleteUserAddrMsg) {
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals("Verify Delete User address", expDeleteUserAddrMsg, message);
	}

}
