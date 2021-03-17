package com.infosys.project.order.dto;

import com.infosys.project.order.entity.ProductsOrdered;

public class ProductsOrderedDTO {
private int orderId;
private int prodId;
private int sellerId;
private int quantity;
private String status;
private double price;

   public ProductsOrderedDTO() {
	   super();
   }
   public ProductsOrderedDTO(int orderId,int prodId, int sellerId, int quantity, String status, double price) {
	 this.orderId=orderId;
	   this.prodId=prodId;
	   this.sellerId=sellerId;
	   this.quantity=quantity;
	   this.status=status;
	   this.price=price;
	  
   }
   
public int getOrderid() {
	return orderId;
}
public void setOrderid(int orderId) {
	this.orderId = orderId;
}
public int getProdid() {
	return prodId;
}
public void setProdid(int prodId) {
	this.prodId = prodId;
}
public int getSellerid() {
	return sellerId;
}
public void setSellerid(int sellerId) {
	this.sellerId = sellerId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
//coverts Entity to DTO
public static ProductsOrderedDTO valueOf(ProductsOrdered productDetails) {
	ProductsOrderedDTO porderedDTO = new ProductsOrderedDTO();
	porderedDTO.setOrderid(productDetails.getOrderid());
	porderedDTO.setProdid(productDetails.getProdid());
	porderedDTO.setSellerid(productDetails.getSellerid());
	porderedDTO.setQuantity(productDetails.getQuantity());
	porderedDTO.setStatus(productDetails.getStatus());
	porderedDTO.setPrice(productDetails.getPrice());
	return porderedDTO;
}
   //Dto to entity
public  ProductsOrdered createEntity() {
	ProductsOrdered prod=new ProductsOrdered();
	prod.setOrderid(this.getOrderid());
	prod.setProdid(this.getProdid());
	prod.setPrice(this.getPrice());
	prod.setPrice(this.getPrice());
	prod.setQuantity(this.getQuantity());
	prod.setSellerid(this.getSellerid());
	prod.setStatus("ORDER PLACED");
	return prod;
	
}
}
