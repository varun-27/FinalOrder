package com.infosys.project.order.dto;


import java.time.LocalDate;
import java.util.List;

import com.infosys.project.order.entity.OrderDetails;

public class OrderProductsOrderedDTO {
	private int orderId;
	private int buyerId;
	private double amount;
	private LocalDate date;
	private String address;
	private String status;
	List<ProductsOrderedDTO> productsOrdered;
	
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
	
	public List<ProductsOrderedDTO> getProductsOrdered() {
		return productsOrdered;
	}
	public void setProductsOrdered(List<ProductsOrderedDTO> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
	
	
	//convert entity to dto
	public static OrderProductsOrderedDTO valueOf(OrderDetails orderDetails) {
		OrderProductsOrderedDTO orderDTO=new OrderProductsOrderedDTO();
		orderDTO.setOrderid(orderDetails.getOrderid());
		orderDTO.setBuyerid(orderDetails.getBuyerid());
		orderDTO.setAmount(orderDetails.getAmount());
		orderDTO.setDate(orderDetails.getDate());
		orderDTO.setAddress(orderDetails.getAddress());
		orderDTO.setStatus( orderDetails.getStatus());
		return orderDTO;
	}
}
