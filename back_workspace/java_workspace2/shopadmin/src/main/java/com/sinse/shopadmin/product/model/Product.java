package com.sinse.shopadmin.product.model;

import java.util.List;

//멤버변수가 DB컬럼명과 일치하도록 한다
public class Product {
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private String introduce;
	private String detail;
	
	private List<Color> colorList; //보유 색상
}
