package com.infosys.project.order.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.project.order.repository.OrderRepository;
import com.infosys.project.order.repository.ProductsOrderedRepo;
import com.infosys.project.order.dto.CartDTO;
import com.infosys.project.order.dto.OrderProductsOrderedDTO;
import com.infosys.project.order.dto.OrderDTO;
import com.infosys.project.order.dto.ProductsDTO;
import com.infosys.project.order.dto.ProductsOrderedDTO;
import com.infosys.project.order.dto.PlaceOrder;
import com.infosys.project.order.entity.OrderDetails;
import com.infosys.project.order.entity.ProductsOrdered;
import com.infosys.project.order.entity.CompositeClass;

@Service
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductsOrderedRepo productRepo;

	
	public List<OrderDTO> getOrderDetails() {

		List<OrderDetails> orders = orderRepo.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		for (OrderDetails order:orders) {
			OrderDTO orderDTO = OrderDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		}
		logger.info("Order details : {}", orderDTOs);
		return orderDTOs;
	}
	
	
	public OrderProductsOrderedDTO getSpecificOrderDetails(int buyerid) {
		OrderProductsOrderedDTO newDTO=null;
		Optional<OrderDetails> optOrder=orderRepo.findByBuyerid(buyerid);
		if(optOrder.isPresent()) {
			OrderDetails orderDetails=optOrder.get();
			newDTO=OrderProductsOrderedDTO.valueOf(orderDetails);
			List<ProductsOrdered> prodorders=productRepo.findByorderid(newDTO.getOrderid());
		List<ProductsOrderedDTO> prodList=new ArrayList<>();
		for(ProductsOrdered p:prodorders) {
			ProductsOrderedDTO pdto=ProductsOrderedDTO.valueOf(p);
			prodList.add(pdto);
		}
		newDTO.setProductsOrdered(prodList);
		}
		return newDTO;
	}
	public List<ProductsOrderedDTO> productsordered(int sellerid){
		List<ProductsOrdered> prodorders=productRepo.findBysellerid(sellerid);
		List<ProductsOrderedDTO> prodlist=new ArrayList<ProductsOrderedDTO>();
		for(ProductsOrdered p:prodorders) {
			ProductsOrderedDTO pdto=ProductsOrderedDTO.valueOf(p);
			prodlist.add(pdto);
		}
		return prodlist;
	}
	
	public OrderDTO getSpecificOrder(int orderid) {
		logger.info("Order details : {}", orderid);
		OrderDTO orderDTO=null;
		Optional<OrderDetails> optOrder=orderRepo.findById(orderid);
		if(optOrder.isPresent()) {
			OrderDetails orderDetails=optOrder.get();
	        orderDTO=OrderDTO.valueOf(orderDetails);
		}
		return orderDTO;
	}

  


    public void reOrder(Integer orderId,Integer prodId) throws Exception{
	     LocalDate date=LocalDate.now();
	     Optional<OrderDetails> order=orderRepo.findById(orderId);
	     CompositeClass ck=new CompositeClass();
	     ck.setOrderid(orderId);
	     ck.setProdid(prodId);
	     Optional<ProductsOrdered> products= productRepo.findById(ck);
	     OrderDTO orderDto=null;
	     OrderDetails orderEntity=null;
	     ProductsOrdered prodentity=null;
	     ProductsOrderedDTO pdto=null;
	     OrderDTO reorderDTO;
	     if(order.isPresent()) {
	    	   OrderDetails entity=order.get();
	    	   System.out.println(entity.getOrderid());
	    	   orderDto=OrderDTO.valueOf(entity);
	    	   System.out.println(orderDto.getOrderid());
	    	   if(orderId.equals(orderDto.getOrderid())) {
	    		   orderEntity=new OrderDetails();
	    		   orderEntity.setBuyerid(orderDto.getBuyerid());
                   orderEntity.setDate(date);
                   orderEntity.setAddress(orderDto.getAddress());
                   orderEntity.setAmount(orderDto.getAmount());
                   orderEntity.setStatus("ORDER PLACED");
                   orderRepo.save(orderEntity);
                   reorderDTO=OrderDTO.valueOf(orderEntity);
	    	   }
	    	   else {
	    		   throw new Exception("NO_BUYER_FOUND");
	    	   }
	    	   if(products.isPresent()) {
	    		   System.out.println(reorderDTO.getOrderid());
	    	   ProductsOrdered pentity=products.get();
	    	   pdto=ProductsOrderedDTO.valueOf(pentity);
	    	   if(prodId.equals(pdto.getProdid())) {
	    		   prodentity=new ProductsOrdered();
	    		   prodentity.setOrderid(reorderDTO.getOrderid());
	    		   prodentity.setProdid(prodId);
	    		   prodentity.setQuantity(pdto.getQuantity());
	    		   prodentity.setPrice(pdto.getPrice());
	    		   prodentity.setSellerid(pdto.getSellerid());
	    		   prodentity.setStatus("ORDER PLACED");
	    		   productRepo.save(prodentity);
	    	   }
	    	   else
	    		   throw new Exception("NO_PRODUCT_FOUND");
	    	   }
	    	   
	     }
	     else {
	    	 throw new Exception("No_ORDER");
	     }     
}
    

   
    
public String changeOrderStatus(Integer orderid,String status){
	String orderstatus=null;
	 List<OrderDetails> entities=orderRepo.findAll();
	 for(OrderDetails entity:entities) {
		 if(orderid.equals(entity.getOrderid())) {
			 entity.setStatus(status);
			 orderRepo.save(entity);
			 orderstatus="Status of your order:"+status;
			 return orderstatus;
		 }
	 }
	 orderstatus ="Sorry!!!No such order is present to change the status..Please Confirm Your Order";
	 return orderstatus;
}
public double getOrderAmountUsingBuyerId(int buyerId) throws Exception  {
	Optional<OrderDetails> optOrder = orderRepo.findByBuyerid(buyerId);
	double amount = -1.0;
	if(optOrder.isPresent()) {
		OrderDetails order = optOrder.get();
		amount = order.getAmount();
	}
	return amount;
	
}
public void changeAmountAccordingToRewardPoints(int buyerId,double amount) {
	Optional<OrderDetails> optOrder = orderRepo.findByBuyerid(buyerId);
	if(optOrder.isPresent()) {
		OrderDetails order = optOrder.get();
	    order.setAmount(amount);
	    orderRepo.save(order);
	}

}
public boolean checkDeliveryAddress(int buyerId) {
	Optional<OrderDetails> optOrder = orderRepo.findByBuyerid(buyerId);
	if(optOrder.isPresent()) {
		OrderDetails order = optOrder.get();
	    if(order.getAddress().length()<100) {
	    	return true;
	    }
	    
	}
	return false;
}
public String cancelOrder(int orderid) {
	  Optional<OrderDetails> order=orderRepo.findById(orderid);
	  if(order.isPresent()) {
		  OrderDetails order1=order.get();
	  orderRepo.delete(order1);
	  return "DELETED Successfully!!!!!";
	  }

return "Sorry!!Your order cannot be deleted";
}
}
