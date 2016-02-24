/**
 * Driver for EE 422C Assignment 3
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart.errors;

/**
 * Exception thrown when an entered operation is invalid
 *
 * @author Aria Pahlavan - Cooper Raterink
 *
 */
public class InvalidOperationException extends Exception {
    private String errorDetail;

    public InvalidOperationException(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public void printError(){
        System.out.println("\nThe following operation contains an error: " + errorDetail);
    }
}
