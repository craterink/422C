/**
 * Driver for EE 422C Assignment 3
 *
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

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
     * Performs a transaction on this cart based on a transaction object.
     *
     * @param trans The Transaction to perform on this cart.
     */
    public void performTransaction(Transaction trans) {
        PurchaseItem transItem = trans.getTransactionItem();
        switch (trans.getTransactionType()) {
            case CLOTH: //insert clothing
            case ELECT: //insert electronics
            case GROCERY: //insert groceries
                cart.add(transItem);
                Collections.sort(cart);
                System.out.println("Added " + transItem.name + " to cart.");
                break;
            case UPDATE: //update
                for (PurchaseItem item : cart) {
                    if (transItem.name == item.name) {
                        item.updateItem(transItem);
                        System.out.println("Updated: " + item.name + "\t" + item.quantity);
                        break; //Only update the first occurrence of an item
                    }
                }
                break;
            case DEL: //delete
                int deletionCounter = 0;
                for (int i =0; i < cart.size(); i++) {
                    if (transItem.name == cart.get(i).name) {
                        cart.remove(cart.get(i));
                        deletionCounter += 1;
                    }
                }
                //outputting number of deletions
                System.out.println(deletionCounter + " " + transItem.name + " were deleted.");
                break;
            case SEARCH: //search
                int searchResult = 0;
                for (PurchaseItem item : cart) {
                    if (transItem.name == item.name)
                        searchResult += 1;
                }
                System.out.println(searchResult + " " + transItem.name + "(s) were found.");
                break;
            case PRINT: //print
                print();
                break;
        }

    }

    /**
     * Print a cart receipt for final checkout.
     */
    public void print() {
        System.out.println("Shopping Cart Items:\n");
        //Sum total prices of cart items
        double total = 0;
        for (PurchaseItem item : cart) {
            item.printItemAttributes();
            total += item.calculatePrice();
        }
        System.out.println("======>>>> Shopping Cart Total Price: $" +
                (new DecimalFormat("0.00")).format(total));
    }
}
