package com.omrbranch.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.base.BaseClass_API;
import com.omrbranch.endpoints.EndPoints;
import com.omrbranch.product.pojo.ProductSearch_Input_Pojo;
import com.omrbranch.product.pojo.ProductSearch_Output_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_ProductSearchStep extends BaseClass_API{
	
	Response response;

	@Given("User add headers to access the search product endpoint")
	public void userAddHeadersToAccessTheSearchProductEndpoint() {
		List<Header> list = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		list.add(h1);
		list.add(h2);
		Headers headers = new Headers(list);
		addHeaders(headers);
	}

	@When("User add request body to search for the product {string}")
	public void userAddRequestBodyToSearchForTheProduct(String product) {
		ProductSearch_Input_Pojo productSearch_Input_Pojo = new ProductSearch_Input_Pojo(product);
		addBody(productSearch_Input_Pojo);
	}

	@When("User send {string} request for the search product response")
	public void userSendRequestForTheSearchProductResponse(String type) {
		response = addReqType(type, EndPoints.SEARCHPRODUCT);
		int responseCode = getResponseCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(responseCode);
	}

	@Then("User verifies that the search product response message matches {string}")
	public void userVerifiesThatTheSearchProductResponseMessageMatches(String expSearchProductResult) {
		ProductSearch_Output_Pojo productSearch_Output_Pojo = response.as(ProductSearch_Output_Pojo.class);
		String message = productSearch_Output_Pojo.getMessage();
		System.out.println(getAsPrettyString(response));
		System.out.println(message);
		Assert.assertEquals("Verify Search product result", expSearchProductResult, message);
	}

}
