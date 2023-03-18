package com.example.VeterinriaXYZ.controller.errors;

import com.example.VeterinriaXYZ.util.ResponseMessage;
import com.example.VeterinriaXYZ.util.ResponseMessage;

public class ApplicationCustomException extends Exception {
	
	private static final long serialVersionUID = 2962065458808354006L;
	private final int code;
	private final String message;
	
	public ApplicationCustomException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	
	public ResponseMessage<Object> getResponse(){
		return new ResponseMessage<>(this.code, this.message, null);
	}
	

}
