/**
 * Driver for EE 422C Assignment 3
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

import java.math.BigDecimal;

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
	public Clothing(String itemName, BigDecimal itemPrice, long itemQuantity, long itemWeight) {
		super(itemName, itemPrice, itemQuantity, itemWeight, false);
		//premium shipping is not available (reason for false in above call)
	}

	@Override
	/**
	 * Used to calculate shipping cost for clothing items - no premium shipping available
	 */
	public void calculateShipCost() {
		//Calculates regular shipping cost
		shippingCost = (SHIPPING_RATE.multiply(BigDecimal.valueOf(weight))).multiply(BigDecimal.valueOf(quantity));
	}
}
