package com.omrbranch.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass_API {

	RequestSpecification reqSpec;
	Response response;
	
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	public String getProperityFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectPath() + "\\Config\\config.properties"));
		Object obj = properties.get(key);
		String value = (String) obj;
		return value;
	}

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}

	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);
	}

	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}
	
	public void addBody(Object object) {
		reqSpec = reqSpec.body(object);
	}

	public void addBasicAuth(String userName, String passWord) {
		reqSpec = reqSpec.auth().preemptive().basic(userName, passWord);
	}

	public Response addReqType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.get(endpoint);
			break;
		case "POST":
			response = reqSpec.post(endpoint);
			break;
		case "PUT":
			response = reqSpec.put(endpoint);
			break;
		case "PATCH":
			response = reqSpec.patch(endpoint);
			break;
		case "DELETE":
			response = reqSpec.delete(endpoint);
			break;
		default:
			break;
		}
		return response;
	}

	public int getResponseCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String getAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	public String getAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}

}
