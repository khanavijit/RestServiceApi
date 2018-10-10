package dk.tdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.tdc.entity.Order;
import dk.tdc.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	
	public List<Order> getAllDetails(){
		
		
 		List<Order> orders= new ArrayList<Order>();
 		 		
 		orderRepository.findAll().forEach(orders::add);
		
		return orders;
	}
	
	public Order getOrderByOrderNr(String orderNr){
		
		return orderRepository.findByOrderNr(orderNr);
	}
	
	public String addOrder(Order order){
		orderRepository.save(order);
		return order.getOrderNr();
	}
	
	
	
	

	

}
