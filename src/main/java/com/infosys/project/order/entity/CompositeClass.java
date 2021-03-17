package com.infosys.project.order.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class CompositeClass implements Serializable {
	@Column(name="orderid")
	private int orderid;
	@Column(name="prodid",nullable=false)
	private int prodid;
	

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	
	public int getOrderid() {
		return orderid;
	}
	

}
