package com.omrbranch.stepdefinition;

import java.util.ArrayList;

import org.junit.Assert;

import com.omrbranch.base.BaseClass_API;
import com.omrbranch.endpoints.EndPoints;
import com.omrbranch.state.GetStateList_Output_Pojo;
import com.omrbranch.state.StateList;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class TC2_StateListStep extends BaseClass_API {

	Response response;

	@Given("User add headers for to stateList")
	public void userAddHeadersForToStateList() {
		addHeader("accept", "application/json");
	}

	@When("User send {string} request for stateList endpoint")
	public void userSendRequestForStateListEndpoint(String type) {
		response = addReqType(type, EndPoints.STATELIST);
		int responseCode = getResponseCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(responseCode);
	}

	@Then("User should verify the stateList response message mathces {string} and saved state ID")
	public void userShouldVerifyTheStateListResponseMessageMathcesAndSavedStateID(String expStateName) {
		GetStateList_Output_Pojo getStateList_Output_Pojo = response.as(GetStateList_Output_Pojo.class);
		ArrayList<StateList> stateList = getStateList_Output_Pojo.getData();
		for (StateList eachStateList : stateList) {
			String name = eachStateList.getName();
			if(name.equals(expStateName)) {
				int stateId = eachStateList.getId();
				TC1_LoginStep.globalDatas.setStateIDNum(stateId);
				Assert.assertEquals("Verify State Namme", expStateName, name);
				//String stateID = String.valueOf(Id);
				break;
			}	
		}
		

	}

}
