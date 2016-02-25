/**
 * Driver for EE 422C Assignment 3
 *
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

/**
 * Enumerated type representing different operation types
 * @author Aria Pahlavan, Cooper Raterink
 *
 */
public enum OperationType {
    NONE,        //invalid
    CLOTH,        //insert clothing
    ELECT,        //insert electronics
    GROCERY,    //insert groceries
    DEL,        //delete item
    SEARCH,        //search item
    PRINT,        //print all
    UPDATE        //update item
}
