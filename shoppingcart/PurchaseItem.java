/**
 * Driver for EE 422C Assignment 3
 *
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Super class representing any item that can go in the shopping cart.
 * Can be sorted by its name.
 * @author Aria Pahlavan - Cooper Raterink
 */
public class PurchaseItem implements Comparable<PurchaseItem>{
    /**
     * Item details.
     */
    protected String name;
    protected BigDecimal price;
    protected BigDecimal salesTax;
    protected BigDecimal shippingCost;
    protected long quantity;
    protected long weight;
    protected boolean isPremium;
    protected final BigDecimal TAX_RATE  = BigDecimal.valueOf(0.1), PREMIUM_RATE = BigDecimal.valueOf(0.2), SHIPPING_RATE = BigDecimal.valueOf(20);


    /**
     * default constructor - Initializes an item with default details
     */
    public PurchaseItem() {
        this.name = "Unknown";
        this.price = new BigDecimal(0.00);
        this.quantity = 00;
        this.weight = 0;
    }

    /**
     * Custom constructor - Initializes an item with the specified details
     *
     * @param itemName     Item name.
     * @param itemPrice    Item price.
     * @param itemQuantity Item quantity.
     * @param itemWeight   Item weight.
     */
    public PurchaseItem(String itemName, BigDecimal itemPrice, long itemQuantity, long itemWeight, boolean isPremium) {
        this.name = itemName;
        this.price = itemPrice;
        this.quantity = itemQuantity;
        this.weight = itemWeight;
        this.isPremium = isPremium;
    }

    /**
     * Updates the quantity of an item
     *
     * @param newItem new item.
     */
    public void updateItem(PurchaseItem newItem) {
        this.quantity = newItem.quantity;
    }


    /**
     * Calculates this item's price using default calculations.
     *
     * @return Final price of item.
     */
    public BigDecimal calculatePrice() {
        //First calculate the shipping cost and the sales tax
        calculateShipCost();
        calculateTax();

        //calculate the price, then add appropriate shipping cost and sales tax
        BigDecimal finalPrice = (price.multiply(BigDecimal.valueOf(quantity))).add(shippingCost).add(salesTax);
        return finalPrice;
    }


    /**
     * Calculates this item's shipping cost.
     */
    public void calculateShipCost() {
        //Calculates regular shipping cost
        shippingCost = (SHIPPING_RATE.multiply(BigDecimal.valueOf(weight))).multiply(BigDecimal.valueOf(quantity));

        //If premium shipping requested, add %20
        if (isPremium)
            shippingCost.add(shippingCost.multiply(PREMIUM_RATE));
    }

    /**
     * Calculates this item's sales tax.
     */
    public void calculateTax() {
        salesTax = (price.multiply(BigDecimal.valueOf(quantity)).add(shippingCost)).multiply(TAX_RATE);
    }

    /**
     * Prints to the console this purchase item's member variables.
     */
    public void printItemAttributes() {
        System.out.println(name + "     "
      		    + "$" + (new DecimalFormat("0.00")).format(price) + "    "
                + "qty: " + quantity + "     "
                + "wt: " + weight + "     "
                + "ship: " + (isPremium ? "Premium" : "Standard") + "    "
                + "Item Total: $" + (new DecimalFormat("0.00")).format(calculatePrice()) + "\n");
    }

	@Override
	/**
	 * Compare PurchaseItems by comparing their names.
	 */
	public int compareTo(PurchaseItem otherPurchaseItem) {
		return this.name.compareTo(otherPurchaseItem.name);
	}
    
    

}
