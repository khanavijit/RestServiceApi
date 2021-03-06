package dk.tdc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import dk.tdc.customSequence.MySequence;

@Entity
@Table(name="Mobile_Orders")
public class Order {

	
	
	
  
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	@GenericGenerator(
		        name = "order_seq", 
		        strategy = "dk.tdc.customSequence.MySequence", 
		        parameters = {
		            @Parameter(name = MySequence.INCREMENT_PARAM, value = "1"),
		            @Parameter(name = MySequence.VALUE_PREFIX_PARAMETER, value = "CB_"),
		            @Parameter(name = MySequence.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String orderNr;
	
	@ManyToOne
	private Person person;
	
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	private List <OrderItem> orderItems;
	
	private String orderStatus;
	
	public String getOrderNr() {
		return orderNr;
	}

	public void setOrderNr(String orderNr) {
		this.orderNr = orderNr;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public void addOrderItems(OrderItem orderItem) {
		
		if(this.orderItems==null) {
			this.orderItems = new ArrayList <OrderItem>();
		}
		this.orderItems.add(orderItem);
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order() {
		
	}
	
	
	


	


	
	
	
	
	
	
}
