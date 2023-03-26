package com.test;

public class Header {
	private String model = "";
	private String method = "";
	private String errorcode = "0";
	private String errorflag = "N";
	private String errordesc = "";
	public Header() {
		super();
	}
	public String getErrorcode() {
		return errorcode;
	}
	public String getErrordesc() {
		return errordesc;
	}
	public String getErrorflag() {
		return errorflag;
	}
	public String getMethod() {
		return method;
	}
	public String getModel() {
		return model;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public void setErrordesc(String errordesc) {
		this.errordesc = errordesc;
	}
	public void setErrorflag(String errorflag) {
		this.errorflag = errorflag;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setModel(String model) {
		this.model = model;
	}	
}
