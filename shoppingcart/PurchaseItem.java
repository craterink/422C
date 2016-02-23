package shoppingcart;

import java.text.DecimalFormat;

/**
 * Base class representing any item that can go in the shopping cart.
 * @author Cooper
 *
 */
public class PurchaseItem 
{
	/**
	 * Item name.
	 */
	protected String name;
	
	/**
	 * Item price.
	 */
	protected double price;
	
	/**
	 * Item quantity.
	 */
	protected int quantity;
	
	/**
	 * Item weight.
	 */
	protected int weight;
	
	/**
	 * Default sales tax levied on purchased items.
	 */
	protected static final double SALES_TAX = 1.1;
	
	/**
	 * Multiplier used to calculate a purchased item's shipping cost.
	 * Multiplied by total weight to get base shipping cost.
	 */
	protected static final double SHIPPING_RATE = 20;
	
	/**
	 * Overhead cost multiplier for premium shipping of a purchase item.
	 */
	protected static final double PREMIUM_SHIPPING_RATE = 1.2;
	
	/**
	 * Initializes a PurchaseItem object with its required member variables. 
	 * @param itemName Item name.
	 * @param itemPrice Item price.
	 * @param itemQuantity Item quantity.
	 * @param itemWeight Item weight.
	 */
	public PurchaseItem(String itemName, double itemPrice, int itemQuantity, int itemWeight) {
		name = itemName;
		price = itemPrice;
		quantity = itemQuantity;
		weight = itemWeight;
	}


	/**
	 * Calculates this item's price using default calculations.
	 * @return Final price of item.
	 */
	public double calculatePrice() {
		return (price*quantity + calculateShippingCost())*SALES_TAX;
	}
	
	/**
	 * Prints to the console this purchase item's member variables.
	 */
	public void printItemAttributes () 
	{
		System.out.println("name: " + name + "\n" 
							  + "price: $" + (new DecimalFormat("0.00")).format(price) + "\n"
							  + "quantity: " + quantity + " units\n"
							  + "weight: " + weight
							  + "=> Final Price: $" + (new DecimalFormat("0.00")).format(calculatePrice()) 
							  + "\n");
	}
	
	/**
	 * Calculates the shipping cost of this item.
	 * @return Shipping cost for this item.
	 */
	public double calculateShippingCost() {
		return SHIPPING_RATE*weight*quantity;
	}

}
