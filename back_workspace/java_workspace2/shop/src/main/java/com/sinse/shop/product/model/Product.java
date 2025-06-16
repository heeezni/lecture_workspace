package com.sinse.shop.product.model;

import java.util.List;

//멤버변수가 DB컬럼명과 일치하도록 한다
public class Product {
	
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	
	private List<Color> colorList; //상품이 보유한 색상 (하나의 상품은 여러개의 색상을 가지고 있다)
	private List<Size> sizeList; //상품이 보유한 사이즈 (하나의 상품은 여러개의 사이즈를 가지고 있다)
	private List<String> filenameList; 	// 상품에 등록된 이미지명 (하나의 상품은 여러개의 파일명을 가지고 있다)
	// 1:N관계인 테이블은 조인대상 X (개수에서 혼란)
	
	private String introduce;
	private String detail;
	private SubCategory subcategory; // ERD상에서는 자식이 부모의 pk를 숫자로 보유하지만
												  // 자바코드에서는 부모를 객체로 보유해야한다
	
	

	public List<Color> getColorList() {
		return colorList;
	}
	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}
	public List<Size> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}
	public List<String> getFilenameList() {
		return filenameList;
	}
	public void setFilenameList(List<String> filenameList) {
		this.filenameList = filenameList;
	}
	
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public SubCategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

}
