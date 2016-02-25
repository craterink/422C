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
 * Class representing an electronics purchase item that could go in the shopping cart.
 * Fragile or Non-fragile: fragile require premium shipping.
 * (Delivery) States of TX, NM, VA, AZ, AK have no sales tax for electronics
 * @author Aria Pahlavan, Cooper Raterink
 *
 */
public class Electronics extends PurchaseItem 
{
	/**
	 * Is this piece of electronics fragile?
    * If true, requires premium shipping
	 */
	protected boolean isFragile;
	
	/**
	 * State abbreviation representing which state this item is being delivered to.
	 * Sales-tax free states: TX, NM, VA, AZ, AK
	 */
	protected String stateName;
	
	/**
	 * Regex matching sales-tax-free states: TX, NM, VA, AZ, AK
	 */
	private static final String REGEX_SALESFREE = "TX|NM|VA|AZ|AK";


	/**
	 * Default constructor - Initializes Electronics item object with proper details.
	 */
	public Electronics() {
		super();
	}

	/**
	 * Custom constructor - Initializes Electronics purchase item object with its required member variables.
	 * @param itemName Item name.
	 * @param itemPrice Item price.
	 * @param itemQuantity Item quantity.
	 * @param itemWeight Item weight.
	 * @param isItemFragile Is the item fragile?
	 * @param itemStateName State the item is being delivered to (for sales tax calculation)
	 */
	public Electronics(String itemName, BigDecimal itemPrice, long itemQuantity, long itemWeight, boolean isItemFragile, String itemStateName) {
		super(itemName, itemPrice, itemQuantity, itemWeight, isItemFragile);
		isFragile = isItemFragile;
		stateName = itemStateName;
	}


	@Override
	/**
	 * Used to calculate an electronics item's shipping cost.
	 */
	public void calculateShipCost() {
		//Calculates regular shipping cost
		shippingCost = (SHIPPING_RATE.multiply(BigDecimal.valueOf(weight))).multiply(BigDecimal.valueOf(quantity));

		//If fragile items or premium shipping requested, add %20
		if(isPremium || isFragile)
			shippingCost = shippingCost.add(PREMIUM_RATE.multiply(shippingCost));
	}

	@Override
	/**
	 * Used to calculate an electronics item's sales tax.
	 */
	public void calculateTax() {
		if(!isTaxExempt())
			salesTax = (price.multiply(BigDecimal.valueOf(quantity)).add(shippingCost)).multiply(TAX_RATE);
		else
			salesTax = BigDecimal.valueOf(0.00);
	}

	/**
	 * checks if the item is from a tax exempt state.
	 * @return boolean If tax exempt true, otherwise false.
	 */
	public boolean isTaxExempt() {
		return stateName.toUpperCase().matches(REGEX_SALESFREE);
	}

}
