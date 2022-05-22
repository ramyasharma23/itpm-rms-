package com.restaurant.management.dto;

import java.sql.Date;

public class InventoryDto {
	private Long id;
	private String supplierName;
	private Date supplierDisappliedDate;
	private int quantity;
	private double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Date getSupplierDisappliedDate() {
		return supplierDisappliedDate;
	}
	public void setSupplierDisappliedDate(Date supplierDisappliedDate) {
		this.supplierDisappliedDate = supplierDisappliedDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
