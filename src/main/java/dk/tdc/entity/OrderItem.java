package dk.tdc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	

	@Id
	private long orderItemId;
	
	@ManyToOne
	private Product product;
	
	
	private String mobileNr;
	
	private String simNr;
	
	
	
	
	public Product getProduct() {
		return product;
	}


	


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getMobileNr() {
		return mobileNr;
	}


	public void setMobileNr(String mobileNr) {
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
