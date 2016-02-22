package shoppingcart;

/**
 * Class representing a clothing item that could go in the shopping cart.
 * Special features: Premium shipping not available, otherwise standard purchase-item rules apply.
 * @author Cooper
 *
 */
public class Clothing extends PurchaseItem 
{	
	/**
	 * Initializes a clothing item object with its required member variables.
	 * @param itemName Item name.
	 * @param itemPrice Item price.
	 * @param itemQuantity Item quantity.
	 * @param itemWeight Item weight.
	 */
	public Clothing(String itemName, double itemPrice, int itemQuantity, int itemWeight) {
		super(itemName, itemPrice, itemQuantity, itemWeight);
	}
}
