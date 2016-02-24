/**
 * Driver for EE 422C Assignment 3
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

/**
 * Class representing a clothing item that could go in the shopping cart.
 * Special features: Premium shipping not available, otherwise standard purchase-item rules apply.
 * @author Aria Pahlavan, Cooper Raterink
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
		//premium shipping is not available
		isPremium = false;
	}

	@Override
	/**
	 * Used to calculate shipping cost for clothing items - no premium shipping available
	 */
	public void calculateShipCost() {
		//Calculates regular shipping cost
		shippingCost = (SHIPPING_RATE*(weight))*quantity;
	}
}
