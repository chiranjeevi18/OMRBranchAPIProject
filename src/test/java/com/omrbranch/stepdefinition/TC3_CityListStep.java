package com.omrbranch.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.base.BaseClass_API;
import com.omrbranch.city.CityList;
import com.omrbranch.city.CityList_Input_Pojo;
import com.omrbranch.city.CityList_Output_Pojo;
import com.omrbranch.endpoints.EndPoints;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_CityListStep extends BaseClass_API{
	
	
	Response response;

	@Given("User add header for to get cityList")
	public void userAddHeaderForToGetCityList() {
		List<Header> list = new ArrayList<Header>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Content-Type","application/json");
		list.add(h1);
		list.add(h2);
		Headers headers = new Headers(list);
		addHeaders(headers);
	}

	@When("User add request body state id for get cityList")
	public void userAddRequestBodStateIdForGetCityList() {
		int stateIDNumber = TC1_LoginStep.globalDatas.getStateIDNum();
		String stateID = String.valueOf(stateIDNumber);
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(stateID);
		addBody(cityList_Input_Pojo);
	}

	@When("User send {string} request for cityList endpoint")
	public void userSendRequestForCityListEndpoint(String type) {
		response = addReqType(type, EndPoints.CITYLIST);
		int statusCode = getResponseCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the cityList response message matches {string} and save city id")
	public void userVerifyTheCityListResponseMessageMatchesAndSaveCityId(String expCityName) {
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<CityList> cityListName = cityList_Output_Pojo.getData();
		for (CityList cityList : cityListName) {
			String city = cityList.getName();
			
			if (city.equals(expCityName)) {
				int id = cityList.getId();
				TC1_LoginStep.globalDatas.setCityID(id);
				Assert.assertEquals("Verify city Name", expCityName, city);
			} 	
		}	
	}

}
