package com.helder.cursoSpring.controller.exception;

import java.io.Serializable;

public class FielldMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	public FielldMessage() {
		
	}

	public FielldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
