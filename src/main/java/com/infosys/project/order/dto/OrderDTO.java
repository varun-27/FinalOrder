package com.infosys.project.order.dto;


import java.time.LocalDate;


import com.infosys.project.order.entity.OrderDetails;


public class OrderDTO {

	private int orderId;
	private int buyerId;
	private Double amount;
	private LocalDate date;
	private String address;
	private String status;

	public OrderDTO() {
		super();
	}

	public OrderDTO(int orderId, int buyerId, double amount,LocalDate date, String address,String status) {
		this();
		this.orderId=orderId;
		this.buyerId=buyerId;
		this.date=date;
		this.address=address;
		this.status=status;
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


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
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
	// Converts Entity into DTO
	public static OrderDTO valueOf(OrderDetails orderDetails) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderid(orderDetails.getOrderid());
		orderDTO.setBuyerid(orderDetails.getBuyerid());
		orderDTO.setAmount(orderDetails.getAmount());
		orderDTO.setDate(orderDetails.getDate());
		orderDTO.setAddress(orderDetails.getAddress());
		orderDTO.setStatus( orderDetails.getStatus());
		 
		return orderDTO;
	}
	
	//converts DTO to entity
	public OrderDetails createEntity() {
		OrderDetails order = new OrderDetails();
		order.setOrderid(this.orderId);
		order.setBuyerid(this.buyerId);
		order.setAmount(this.amount);
		order.setDate(this.date);
        order.setAddress(this.address);
        order.setStatus(this.status);
		return order;
	}


}
