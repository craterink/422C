/**
 * Driver for EE 422C Assignment 3
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

/**
 * Super class representing any item that can go in the shopping cart.
 *
 * @author Aria Pahlavan - Cooper Raterink
 *
 */
public class PurchaseItem {
	/**
	 * Item details.
	 */
	protected String name;
	protected double price;
	protected double salesTax;
	protected double shippingCost;
	protected int quantity;
	protected double weight;
	protected boolean isPremium;
	protected final double TAX_RATE = 0.1, PREMIUM_RATE = 0.2, SHIPPING_RATE = 20;
	
	
	
	
	/**
	 * default constructor - Initializes an item with default details
	 */
	public PurchaseItem() {
		this.name = "Unknown";
		this.price = 0.00;
		this.quantity = 00;
		this.weight = 0.0;
	}
	
	/**
	 * Custom constructor - Initializes an item with the specified details
	 * @param itemName Item name.
	 * @param itemPrice Item price.
	 * @param itemQuantity Item quantity.
	 * @param itemWeight Item weight.
	 */
	public PurchaseItem(String itemName, double itemPrice, int itemQuantity, int itemWeight) {
		this.name = itemName;
		this.price = itemPrice;
		this.quantity = itemQuantity;
		this.weight = itemWeight;
	}

	/**
	 * Updates the quantity of an item
	 * @param newItem new item.
	 */
	public void updateItem(PurchaseItem newItem) {
		this.quantity = newItem.quantity;
	}


	/**
	 * Calculates this item's price using default calculations.
	 * @return Final price of item.
	 */
	public double calculatePrice () 
	{   
	    //First calculate the shipping cost and the sales tax
	    calculateShipCost();
	    calculateTax();
	    
	    //calculate the price, then add appropriate shipping cost and sales tax
		return price*(double)quantity + shippingCost + salesTax;
	}
	
	
	/**
	 * Calculates this item's shipping cost.
	 */
	public void calculateShipCost ()
	{
	   //Calculates regular shipping cost
	    shippingCost = (SHIPPING_RATE*(weight))*quantity;
	    
	    //If premium shipping requested, add %20
	    if(isPremium)
	        shippingCost += PREMIUM_RATE*shippingCost;
	}
	
	/**
	 * Calculates this item's sales tax.
	 */
	public void calculateTax ()
	{
		salesTax = price*TAX_RATE;
	}
	
	/**
	 * Prints to the console this purchase item's member variables.
	 */
	public void printItemAttributes () 
	{
		System.out.println(name    + "\t" 
		                 + quantity + "\t"
                         + weight  + "\t"
				   + "$" + price   + "\n");
	}

}
