package com.omrbranch.stepdefinition;

public class GlobalDatas {
	private int statusCode;
	private int stateIDNum;
	private int cityID;
	private String logToken;
	private String AddressID;

	public String getAddressID() {
		return AddressID;
	}

	public void setAddressID(String addressID) {
		AddressID = addressID;
	}

	public String getLogToken() {
		return logToken;
	}

	public void setLogToken(String logToken) {
		this.logToken = logToken;
	}

	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public int getStateIDNum() {
		return stateIDNum;
	}

	public void setStateIDNum(int stateID) {
		this.stateIDNum = stateID;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
