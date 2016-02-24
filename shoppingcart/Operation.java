/**
 * Driver for EE 422C Assignment 3
 *
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

import shoppingcart.errors.InvalidOperationException;

import java.math.BigDecimal;

/**
 * Class that represents a operation for the shopping cart to handle.
 * Could be an insert, delete, etc.
 * @author Aria Pahlavan, Cooper Raterink
 *
 */
public class Operation {
    /**
     * Regex matching valid U.S. state abbreviations.
     */
    private static final String STATES_REGEX = "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|"
            + "IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI"
            + "|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY";
    
    /**
     * Regex matching a correct operation input integer.
     */
    private static final String INTEGER_REGEX = "(([0-9]{1,3}((,[0-9]{3})+|[0-9]*)(\\.0*)?)|"
    															+ "([0-9]{0,3}((,[0-9]{3})+|[0-9]*)(\\.0+)))";
    
    /**
     * Regex matching a correct operation price input.
     */
    private static final String PRICE_REGEX = "(([0-9]{1,3}((,[0-9]{3})+|[0-9]*)"
    		+ "(\\.[0-9]*)?)|([0-9]{0,3}((,[0-9]{3})+|[0-9]*)(\\.[0-9]+)))";
    
    /**
     * Regex matching an insert operation
     */
    private static final String INSERT_REGEX = "insert ("
    		+ "(clothing [^ ]+ " + PRICE_REGEX + " " + INTEGER_REGEX + " " + INTEGER_REGEX + ")|"
    		+ "(electronics [^ ]+ " + PRICE_REGEX + " " + INTEGER_REGEX + " " + INTEGER_REGEX + " N?F (" + STATES_REGEX + "))|"
    		+ "(groceries [^ ]+ " + PRICE_REGEX + " " + INTEGER_REGEX + " " + INTEGER_REGEX + " N?P)) ?";

    /**
     * Regex matching a delete operation
     */
    private static final String DELETE_REGEX = "delete [^ ]+ ?";

    /**
     * Regex matching a search operation
     */
    private static final String SEARCH_REGEX = "search [^ ]+ ?";

    /**
     * Regex matching an update operation
     */
    private static final String UPDATE_REGEX = "update [^ ]+ [0-9]+(\\.0+?)? ?";

    /**
     * Regex matching a print operation
     */
    private static final String PRINT_REGEX = "print ?";


    private OperationType operationType = OperationType.NONE;

    /**
     * Gets the type of this operation
     * @return TransType
     */
    public OperationType getOperationType() {
        return operationType;
    }

    /**
     * Sets the type of this operation using the TransType enum
     */
    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    /**
     * Gets the purchase item representing this operation's info.
     * @return PurchaseItem containing operation information
     */
    public PurchaseItem getOperationItem() {
        return operationItem;
    }

    /**
     * Sets new information for the operation using a purchase item.
     * @param operationItem The new operation information purchase item.
     */
    public void setOperationItem(PurchaseItem operationItem) {
        this.operationItem = operationItem;
    }

    /**
     * Contains any needed information in the form of a purchase item object
     *  to process the operation.
     */
    private PurchaseItem operationItem;

    /**
     * Initializes this operation object with its appropriate type and item
     * information while checking for validity.
     * @param operationStr Input string representing a operation
     * the shopping cart can perform
     * @throws InvalidOperationException When an invalid exception was entered
     */
    public Operation(String operationStr) throws InvalidOperationException {
        final int BEGIN_INDEX = 0;
        String type;
        try {
            //get rid of any whitespace that isn't a single space
            operationStr.replaceAll("[\\t ]+", " ");
            //get rid of starting space
            if (operationStr.startsWith(" ")) operationStr.replaceFirst(" ", "");
            //properly assign operation type string to type variable, ignoring weird 
            if(operationStr.indexOf(" ") < BEGIN_INDEX) {
            	type = operationStr;
            } 
            else {
            	type = operationStr.substring(BEGIN_INDEX, operationStr.indexOf(" "));
            }
            switch (type.toLowerCase()) {
                case "insert":
                    if (!operationStr.toLowerCase().matches(INSERT_REGEX.toLowerCase()))
                        throw new InvalidOperationException(operationStr);
                    else {
                        parseInsert(operationStr);
                    }
                    break;
                case "delete":
                    if (!operationStr.toLowerCase().matches(DELETE_REGEX))
                        throw new InvalidOperationException(operationStr);
                    else {
                        parseDelete(operationStr);
                    }
                    break;
                case "search":
                    if (!operationStr.toLowerCase().matches(SEARCH_REGEX))
                        throw new InvalidOperationException(operationStr);
                    else {
                        parseSearch(operationStr);
                    }
                    break;
                case "print":
						  if (!operationStr.toLowerCase().matches(PRINT_REGEX))
						      throw new InvalidOperationException(operationStr);
						  else {
						      parsePrint(operationStr);
						  } 
						  break;
                case "update":
                    if (!operationStr.toLowerCase().matches(UPDATE_REGEX))
                        throw new InvalidOperationException(operationStr);
                    else {
                        parseUpdate(operationStr);
                    }
                    break;
                default:
                    throw new InvalidOperationException(operationStr);
            }
        } catch (InvalidOperationException ite) {
      	   //the operation must be invalid because there was an error while parsing
            ite.printError();
            operationType = OperationType.NONE;
        }
    }

    /**
     * Parses a print operation
     * @param printStr operation input string
     */
    private void parsePrint(String printStr) {
        operationType = OperationType.PRINT;
    }

    /**
     * Parses an update operation
     * @param updateStr operation input string
     */
    private void parseUpdate(String updateStr) {
        operationType = OperationType.UPDATE;
        operationItem = new PurchaseItem(
                updateStr.split(" ")[1], BigDecimal.valueOf(0), (long)Double.parseDouble(updateStr.split(" ")[2]), 0);
    }

    /**
     * Parses a search operation
     * @param searchStr operation input string
     */
    private void parseSearch(String searchStr) {
        operationType = OperationType.SEARCH;
        operationItem = new PurchaseItem(searchStr.split(" ")[1], BigDecimal.valueOf(0), 0, 0);
    }

    /**
     * Parses a delete operation
     * @param deleteStr operation input string
     */
    private void parseDelete(String deleteStr) {
        operationType = OperationType.DEL;
        operationItem = new PurchaseItem(deleteStr.split(" ")[1], BigDecimal.valueOf(0), 0, 0);
    }

    /**
     * Parses an insert operation
     * @param insertStr operation input string
     */
    private void parseInsert(String insertStr) {
        String[] splitInsert = insertStr.split(" ");
        switch (splitInsert[1]) {
            case "clothing":
                operationType = OperationType.CLOTH;
                operationItem = new Clothing(
                        splitInsert[2], BigDecimal.valueOf(Double.parseDouble(splitInsert[3].replaceAll(",", ""))), (long)Double.parseDouble(splitInsert[4].replaceAll(",", "")),
                        (long)Double.parseDouble(splitInsert[5].replaceAll(",", "")));
                break;
            case "electronics":
                operationType = OperationType.ELECT;
                operationItem = new Electronics(
                        splitInsert[2], BigDecimal.valueOf(Double.parseDouble(splitInsert[3].replaceAll(",", ""))), (long)Double.parseDouble(splitInsert[4].replaceAll(",", "")),
                        (long)Double.parseDouble(splitInsert[5].replaceAll(",", "")), splitInsert[6].matches("F"), splitInsert[7]);
                break;
            case "groceries":
                operationType = OperationType.GROCERY;
                operationItem = new Grocery(
                        splitInsert[2], BigDecimal.valueOf(Double.parseDouble(splitInsert[3].replaceAll(",", ""))), (long)Double.parseDouble(splitInsert[4].replaceAll(",", "")),
                        (long)Double.parseDouble(splitInsert[5].replaceAll(",", "")), splitInsert[6].matches("P"));
                break;
        }
    }

}
