package shoppingcart;

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
	public double calculatePrice () 
	{
		double finalPrice = (double)(price*quantity);
		return finalPrice;
	}
	
	/**
	 * Prints to the console this purchase item's member variables.
	 */
	public void printItemAttributes () 
	{
		System.out.println("name: " + name + "\n" 
							  + "price: " + price + "\n"
							  + "quantity: " + quantity + "\n"
							  + "weight: " + weight);
	}

}
