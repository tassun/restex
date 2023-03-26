package com.test;

import org.json.simple.JSONObject;

public class Responser {
	public final Header head = new Header();
	public final JSONObject body = new JSONObject();
	public Responser() { 
		super();
	}
	public Responser(String model,String method) {
		head().setModel(model);
		head().setMethod(method);
	}
	public JSONObject body() {
		return body;
	}
	public void compose(Throwable thrower,String code) {
		head().setErrorcode(code);
		head().setErrorflag("Y");
		head().setErrordesc(thrower.getMessage());
	}
	public Header head() {
		return head;
	}
}
