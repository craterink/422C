package shoppingcart;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class that represents a shopping cart composed of any amount of PurchaseItems.
 * @author Cooper
 *
 */
public class ShoppingCart {

	/**
	 * Alphabetized-by-name list of purchase items in this cart.
	 */
	protected ArrayList<PurchaseItem> cart;
	
	/**
	 * Initializes a new shopping cart.
	 */
	public ShoppingCart() {
		cart = new ArrayList<PurchaseItem>();
	}
	
	/**
	 * Get the cart.
	 * @return This shopping cart's list of purchase items.
	 */
	public ArrayList<PurchaseItem> getCart() {
		return cart;
	}
	
	/**
	 * Change the cart to a new cart.
	 * @param newCart The new list of purchase items to put in this cart.
	 */
	public void setCart(ArrayList<PurchaseItem> newCart) {
		cart = newCart;
	}

	/**
	 * Performs a transaction on this cart based on a transaction object.
	 * @param trans The Transaction to perform on this cart.
	 */
	public void performTransaction(Transaction trans) {
		switch(trans.getTransactionType()) {
		case 'C': //insert clothing
		case 'E': //insert electronics
		case 'G': //insert groceries
			cart.add(trans.getTransactionItem());
			break;
		case 'U': //update
			
			break;
		case 'D': //delete
			
			break;
		case 'S': //search
			
			break;
		case 'P': //print
			print();
			break;
		}
		
	}

	/**
	 * Print a cart receipt for final checkout.
	 */
	public void print() {
		System.out.println("Shopping Cart Items:\n");
		double total = 0;
		for(PurchaseItem item : cart) {
			item.printItemAttributes();
			total += item.calculatePrice();
		}
		System.out.println("======>>>> Shopping Cart Total Price: $" + 
								(new DecimalFormat("0.00")).format(total));
	}
}
