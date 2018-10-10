package dk.tdc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dk.tdc.entity.Order;
import dk.tdc.entity.OrderItem;
import dk.tdc.entity.Person;
import dk.tdc.requests.CartItem;
import dk.tdc.requests.OrderRequest;
import dk.tdc.service.OrderService;
import dk.tdc.service.PersonService;
import dk.tdc.service.ProductService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PersonService personService;
	
	
	@Autowired
	private ProductService productService;
	
	
	
	
	@RequestMapping("/service/order/all")
	public List<Order> getAllDetails(){
		
		return orderService.getAllDetails();
	}
	
	@RequestMapping("/service/order/{orderNr}")
	public Order getOrderByorderNr(@PathVariable String orderNr){
		
		return orderService.getOrderByOrderNr(orderNr);
	}
		

	
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="/service/order")
	public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest){
		
		
		Order order = new Order();
		
		Person person=personService.getPersonByCpr(orderRequest.getCprNr());
		
		
		for(CartItem ci:orderRequest.getCartItems()) {
			
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(productService.getProductByPackageId(ci.getPackageId()));
			order.addOrderItems(orderItem);
		}	
		
		order.setPerson(person);
		
		String orderNr=orderService.addOrder(order);
		
		return new ResponseEntity<String>("Order Created - " + orderNr, new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
}
