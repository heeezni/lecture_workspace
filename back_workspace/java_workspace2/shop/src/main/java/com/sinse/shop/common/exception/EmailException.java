package com.sinse.shop.common.exception;

public class EmailException extends RuntimeException{
	public EmailException(String msg) {
		super(msg);
	}
	public EmailException(String msg, Throwable e) {
		super(msg, e);
	}
	public EmailException(Throwable e) {
		super(e);
	}
	
}
