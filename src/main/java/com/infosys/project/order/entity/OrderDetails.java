package com.infosys.project.order.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	@Column(nullable = false)
	int buyerId;
	@Column(nullable = false)
	double amount;
	@Column(nullable = false)
	LocalDate date;
	@Column(nullable = false)
	String address;
	@Column(nullable = false)
	String status;
	public OrderDetails() {
		super();
	}
	public OrderDetails(int i, int j, double d, LocalDate localDate, String string, String string2) {
		super();
	}
	

	public int getOrderid() {
		return orderId;
	}

	public void setOrderid(int orderId) {
		this.orderId = orderId;
	}

	public int getBuyerid() {
		return buyerId;
	}

	public void setBuyerid(int buyerId) {
		this.buyerId = buyerId;
	}

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
