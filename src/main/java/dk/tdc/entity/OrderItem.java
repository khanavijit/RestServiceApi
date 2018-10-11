package dk.tdc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long orderItemId;
	
	@ManyToOne
	private Product product;
	
	@OneToOne
	private MobileNumber mobileNr;
	
	private String simNr;
	
	
	
	
	public Product getProduct() {
		return product;
	}


	


	public void setProduct(Product product) {
		this.product = product;
	}


	public MobileNumber getMobileNr() {
		return mobileNr;
	}


	public void setMobileNr(MobileNumber mobileNr) {
		this.mobileNr = mobileNr;
	}


	public String getSimNr() {
		return simNr;
	}


	public void setSimNr(String simNr) {
		this.simNr = simNr;
	}


	

	public OrderItem() {
		
	}
	
	
	


	@Override
	public String toString() {
		return "OrderItem [product=" + product + ", mobileNr=" + mobileNr + ", simNr=" + simNr + "]";
	}


	
	
	
	
	
	
}
