package com.sinse.shopadmin.product.view;

// 나만의 Exception 만들기
public class NumberConvertFailException extends RuntimeException{

	public NumberConvertFailException(String msg) {
		super(msg);
	}
	public NumberConvertFailException(Throwable e) {
		super(e);
	}
	public NumberConvertFailException(String msg , Throwable e) {
		super(msg, e);
	}
}
