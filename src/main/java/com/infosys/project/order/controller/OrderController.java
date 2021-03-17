package com.infosys.project.order.controller;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import com.infosys.project.order.dto.OrderProductsOrderedDTO;
import com.infosys.project.order.dto.OrderDTO;
import com.infosys.project.order.dto.ProductsOrderedDTO;
import com.infosys.project.order.service.OrderService;



@RestController
@CrossOrigin
public class OrderController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Environment environment;
	@Autowired
	OrderService orderService;

	
	@Value("${cart.deleteuri}")
	String carturl;
	@Value("${cart.rewarduri}")
	String cartrewardUrl;
	@Value("${product.uri}")
	String producturl;

	
	
	
	//To get all orders
	@GetMapping(value = "/orders",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getAllOrderDetails() {
		logger.info("Orderdetails");

		return orderService.getOrderDetails();
	}
	
	
	
	
	
	// To Get order details of a specific order using buyer id
	@GetMapping(value = "/orders/{buyerid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderProductsOrderedDTO getspecificOrderDetails(@PathVariable int buyerid) {
		logger.info("Orderdetails request for user {}", buyerid);

		return orderService.getSpecificOrderDetails(buyerid);
	}
	
	
	
	
	
	
	
	
	//Remove products from the cart
      @DeleteMapping(value="/cart/delete/{buyerId}/{prodId}")
     public void delete(@PathVariable Integer buyerId,@PathVariable Integer prodId) {
	 String url=carturl+buyerId+"/"+prodId;
	 RestTemplate restTemp=new RestTemplate();
	 restTemp.delete(url);
      }
      
      
      
      //Get Orders of the seller
      @GetMapping("/order/product/{sellerid}")
      public List<ProductsOrderedDTO> getproductsnySellerId(@PathVariable int sellerid){
    	  return orderService.productsordered(sellerid);
      }
      
      
	
      
	  // Reorder the placed order
	  @SuppressWarnings("unchecked")
	  @PostMapping(value="/order/reOrder/{orderId}/{prodId}")
	  public ResponseEntity<String> reOrder(@PathVariable Integer orderId,@PathVariable Integer prodId) throws Exception {
           ResponseEntity<String> response=null;
           try {
		   orderService.reOrder(orderId, prodId);
		   String successMessage = environment.getProperty("REORDER_PLACED_SUCCESSFULLY");
		   response = new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
			 
	       }catch(Exception e) {
		   throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
			 
	       }
	   return response;
		
	   }
	
	
	
	
	
	
	//To change the status of the order
	@PutMapping("order/{orderid}/{status}")
	public String changeOrderStatus(@PathVariable Integer orderid,@PathVariable String status) {
		return orderService.changeOrderStatus(orderid, status);
	}

	
	
	
	//To get order amount of a specific buyer
	@GetMapping(value = "/orders/buyer/{buyerId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public double getOrderAmountByBuyerId(@PathVariable int buyerId) throws Exception {
		double amount = orderService.getOrderAmountUsingBuyerId(buyerId);
		return amount;
		
		
	}
	
	
	
	
	//Change amount based on reward points
	@PutMapping(value = "/orders/amount/{buyerId}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> disableProductsUsingSellerId(@PathVariable Integer buyerId,@RequestBody OrderDTO orderDTO) {
		ResponseEntity<String> response = null;
		orderService.changeAmountAccordingToRewardPoints(buyerId, orderDTO.getAmount());
		String successMessage = "Good job";
		response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
	    return response;
		
	}

	
	
	
	//To get delivery address of a particular buyer
	@GetMapping(value = "/api/Address/{buyerId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean privilegedBuyer(@PathVariable Integer buyerId) {
		boolean flag = orderService.checkDeliveryAddress(buyerId);
		return flag;
	}
	
	
	
	
	//To cancel order
	@DeleteMapping(value="/order/cancel/{orderid}")
	public String  cancelOrder(@PathVariable int orderid){
		return orderService.cancelOrder(orderid);
	}
}
