package shoppingcart;

import java.util.ArrayList;

public class ShoppingCart {

	protected ArrayList<PurchaseItem> cart;
	
	public ShoppingCart() {
		cart = new ArrayList<PurchaseItem>();
	}
	
	public ArrayList<PurchaseItem> getCart() {
		return cart;
	}
	
	public void setCart(ArrayList<PurchaseItem> newCart) {
		cart = newCart;
	}
}
