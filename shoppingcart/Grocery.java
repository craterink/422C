/**
 * Driver for EE 422C Assignment 3
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;


/**
 * Class representing a grocery purchase item that can be added to the shopping cart
 * Can be perishable or nonperishable
 * Perishable groceries require premium shipping
 * do not incur sales tax
 * @author Aria Pahlavan, Cooper Raterink
 *
 */
public class Grocery extends PurchaseItem {

	/**
	 * Is this item perishable?
	 * If true, premium shipping is required.
	 */
	protected boolean isPerishable;

	/**
	 * Default constructor - calls the parent default constructor, as well as initializing local variable.
	 */
	public Grocery(boolean isPerishable) {
		super();
		//assuming that a default grocery item is not perishable
		this.isPerishable = false;
	}

	/**
	 * Initializes this grocery purchase item with its required member variables.
	 *
	 * @param itemName         Item name.
	 * @param itemPrice        Item price.
	 * @param itemQuantity     Item quantity.
	 * @param itemWeight       Item weight.
	 * @param isItemPerishable Is this item perishable?
	 */
	public Grocery(String itemName, double itemPrice, int itemQuantity, int itemWeight, boolean isItemPerishable) {
		super(itemName, itemPrice, itemQuantity, itemWeight);
		isPerishable = isItemPerishable;
	}


	@Override
	/**
	 * Used to calculate shipping cost for groceries.
	 */
	public void calculateShipCost() {
		//Calculates regular shipping cost
		shippingCost = (SHIPPING_RATE * (weight)) * quantity;

		//If premium shipping requested, add %20
		if (isPremium || isPerishable)
			shippingCost += PREMIUM_RATE * shippingCost;
	}

	@Override
	/**
	 * Groceries incur no taxes.
	 */
	public void calculateTax() {
		salesTax = 0.00;
	}


}