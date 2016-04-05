package com.resham.shopping;

import java.io.Serializable;

public class SideMenu implements Serializable{
	private String name;
	private double price;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public SideMenu(String name, double d) {
		super();
		this.name = name;
		this.price = d;
	}
    public String toString(){
    	return name;
    }

	

}
