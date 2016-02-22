package shoppingcart;

/**
 * Class representing an electronics purchase item that could go in the shopping cart.
 * Fragile or Nonfragile: fragile require premium shipping.
 * (Delivery) States of TX, NM, VA, AZ, AK have no sales tax for electronics
 * @author Cooper
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
	 * Initializes Electronics purchase item object with its required member variables.
	 * @param itemName Item name.
	 * @param itemPrice Item price.
	 * @param itemQuantity Item quantity.
	 * @param itemWeight Item weight.
	 * @param isItemFragile Is the item fragile?
	 * @param itemStateName State the item is being delivered to (for sales tax calculation)
	 */
	public Electronics(String itemName, double itemPrice, int itemQuantity, int itemWeight, boolean isItemFragile, String itemStateName) {
		super(itemName, itemPrice, itemQuantity, itemWeight);
		isFragile = isItemFragile;
		stateName = itemStateName;
	}
	
	public double calculatePrice() {
		if(stateName.matches(REGEX_SALESFREE)) {
			return super.calculatePrice();
		} else {
			return super.calculatePrice()/SALES_TAX;
		}
	}
	
	public double calculateShippingCost() {
		if(isFragile) {
			return super.calculateShippingCost()*1.2;
		}
		else {
			return super.calculateShippingCost();
		}
		
	}
}
