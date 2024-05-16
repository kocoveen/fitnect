package com.ssafy.fitnect.exception;

public class TokenNotFoundException extends Exception {

	public TokenNotFoundException (String msg){
		super(msg);
	}

	public TokenNotFoundException() {
		super();
	}
}
