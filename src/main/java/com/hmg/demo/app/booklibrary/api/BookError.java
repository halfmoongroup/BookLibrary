package com.hmg.demo.app.booklibrary.api;

public class BookError {
	
	Integer code;
	String  message;
	
	public BookError(int status, String message) {
		code = -1;
		message = "";
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
