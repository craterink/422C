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
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that represents a shopping cart composed of any number of items.
 * Items in the cart must be alphabetized.
 *
 * @author Aria Pahlavan, Cooper Raterink
 */
public class ShoppingCart {

    /**
     * Alphabetized-by-name list of purchase items in this cart.
     */
    protected ArrayList<PurchaseItem> cart;

    /**
     * Initializes a new shopping cart.
     */
    public ShoppingCart() {
        cart = new ArrayList<PurchaseItem>();
    }

    /**
     * Get the cart.
     *
     * @return This shopping cart's list of purchase items.
     */
    public ArrayList<PurchaseItem> getCart() {
        return cart;
    }

    /**
     * Update the cart.
     *
     * @param newCart The new list of purchase items to put in this cart.
     */
    public void setCart(ArrayList<PurchaseItem> newCart) {
        cart = newCart;
    }

    /**
     * Performs a operation on this cart based on a operation object.
     *
     * @param operation The Operation to perform on this cart.
     */
    public void performOperation(Operation operation) {
        PurchaseItem operationItem = operation.getOperationItem();
        switch (operation.getOperationType()) {
            case CLOTH: //insert clothing
            case ELECT: //insert electronics
            case GROCERY: //insert groceries
                cart.add(operationItem);
                Collections.sort(cart);
                System.out.println("\nAdded " + operationItem.name + " to cart.");
                break;
            case UPDATE: //update
                for (PurchaseItem item : cart) {
                    if (operationItem.name.equals(item.name)) {
                        item.updateItem(operationItem);
                        System.out.println("\nUpdated: " + item.name + "\t" + item.quantity);
                        break; //Only update the first occurrence of an item
                    }
                }
                break;
            case DEL: //delete
                long deletionCounter = 0;
                for (int i =0; i < cart.size(); i++) {
                    if (operationItem.name.equals(cart.get(i).name)) {

                  	  deletionCounter += cart.get(i).quantity; 
                  	  cart.remove(cart.get(i--)); //subtract iterator to account for one less item
                    }
                }
                //outputting number of deletions
                System.out.println("\n" + deletionCounter + " " + operationItem.name + "(s) were deleted.");
                break;
            case SEARCH: //search
                long searchResult = 0;
                for (PurchaseItem item : cart) {
                    if (operationItem.name.equals(item.name))
                        searchResult += item.quantity;
                }
                System.out.println("\n" + searchResult + " " + operationItem.name + "(s) were found.");
                break;
            case PRINT: //print
                print();
                break;
            case NONE:
            	 System.out.println("\nOperation is invalid. Cannot perform");
            	 break;
        }

    }

    /**
     * Print a cart receipt for final checkout.
     */
    public void print() {
        System.out.println("\n=================== Aria Ready2Shop: Shopping Cart Items ====================\n");
        BigDecimal total = BigDecimal.valueOf(0.00);
        if(cart.size() == 0) {
      	  System.out.println("No items in the cart.");
        }
        else {
           //Sum total prices of cart items
           for (PurchaseItem item : cart) {
               item.printItemAttributes();
               total = total.add(item.calculatePrice());
           }
        }
        System.out.println("-----------------------------------------------------------------------------\n"
      		  	+ "Shopping Cart Total Price: $" +
                (new DecimalFormat("0.00")).format(total));
        System.out.println("\nThank you for shopping at Aria Ready2Shop!");
        System.out.println("=============================================================================\n");
    }
}
