package shoppingcart;


/**
 * Class representing a grocery purchase item that can be added to the shopping cart
 * Can be perishable or nonperishable
 * do not incur sales tax
 * @author Cooper
 *
 */
public class Grocery extends PurchaseItem {

	/**
	 * Is this item perishable?
	 * If true, premium shipping is required.
	 */
	protected boolean isPerishable;
	
	/**
	 * Initializes this grocery purchase item with its required member variables.
	 * @param itemName Item name.
	 * @param itemPrice Item price.
	 * @param itemQuantity Item quantity.
	 * @param itemWeight Item weight.
	 * @param isItemPerishable Is this item perishable?
	 */
	public Grocery(String itemName, double itemPrice, int itemQuantity, int itemWeight, boolean isItemPerishable) {
		super(itemName, itemPrice, itemQuantity, itemWeight);
		isPerishable = isItemPerishable;
	}
	
	/**
	 * Used to calculate shipping cost for groceries.
	 * @return Shipping cost for this grocery item.
	 */
	public double calculateShippingCost() {
		if(isPerishable) {
			return super.calculateShippingCost()*1.2;
		}
		else {
			return super.calculateShippingCost();
		}
	}
	
	/**
	 * Used to calculate the price of a grocery item.
	 * 
	 */
	public double calculatePrice() {
		return super.calculatePrice()/SALES_TAX;
	}
}
