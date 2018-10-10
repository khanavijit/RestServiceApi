package dk.tdc.repository;

import org.springframework.data.repository.CrudRepository;

import dk.tdc.entity.Order;

public interface OrderRepository extends CrudRepository <Order,Integer>{
	
	
	public Order findByOrderNr(String orderNr);

}
