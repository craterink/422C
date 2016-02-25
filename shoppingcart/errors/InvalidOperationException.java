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
 * @author Aria Pahlavan - Cooper Raterink
 */
public class InvalidOperationException extends Exception {
    
	/**
	 * Contains details about this specific operation exception
	 */
	private String exceptionDetail;

	/**
	 * Initializes this operation exception object, setting the string containing details
	 * @param exDetail String with details about the operation exception that occurred.
	 */
    public InvalidOperationException(String exDetail) {
        this.exceptionDetail = exDetail;
    }

    /**
     * Prints a message about this specific operation exception
     */
    public void printException(){
        System.out.println("\nThe following operation contains an error: " + exceptionDetail);
    }
}
