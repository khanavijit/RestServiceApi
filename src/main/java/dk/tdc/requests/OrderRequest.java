package dk.tdc.requests;

import java.util.List;

public class OrderRequest {

	

	private String cprNr;
	
	public String getCprNr() {
		return cprNr;
	}
	public void setCprNr(String cprNr) {
		this.cprNr = cprNr;
	}
	
	private List<CartItem> cartItems;

	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
