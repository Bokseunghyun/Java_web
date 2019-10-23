package org.web.memberDTO;

public class ChartDTO {
	
	private String item;
	private String date;
	private String price;
	private String shop;

	public ChartDTO() {
		// TODO Auto-generated constructor stub
	}

	public ChartDTO(String item, String date, String price, String shop) {
		super();
		this.item = item;
		this.date = date;
		this.price = price;
		this.shop = shop;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}
	
}
