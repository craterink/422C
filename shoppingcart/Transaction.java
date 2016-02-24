/**
 * Driver for EE 422C Assignment 3
 *
 * @author Aria Pahlavan, Cooper Raterink
 * EIDs: ap44342, cdr2678
 * Lab section: Friday 2-3:30pm
 * Date: String 2015
 */
package shoppingcart;

import shoppingcart.errors.InvalidTransactionException;

/**
 * Enumerated type representing different transaction types
 * @author Aria Pahlavan, Cooper Raterink
 *
 */
enum TransType {
    NONE,        //invalid
    CLOTH,        //insert clothing
    ELECT,        //insert electronics
    GROCERY,    //insert groceries
    DEL,        //delete item
    SEARCH,        //search item
    PRINT,        //print all
    UPDATE        //update item
}

/**
 * Class that represents a transaction for the shopping cart to handle.
 * Could be an insert, delete, etc.
 * @author Aria Pahlavan, Cooper Raterink
 *
 */
public class Transaction {

    /**
     * Regex matching valid U.S. state abbreviations.
     */
    private static final String STATES_REGEX = "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|"
            + "IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI"
            + "|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY";

    /**
     * Regex matching an insert transaction
     */
    private static final String INSERT_REGEX = "insert ((clothing [a-z0-9]+ [0-9]+(\\.[0-9]{1,2})? "
    					+ "[0-9]+ [0-9]+)|(electronics [a-z0-9]+ [0-9]+(\\.[0-9]{1,2})? [0-9]+ [0-9]+ N?F "
    					+ "(" + STATES_REGEX + "))|(groceries [a-z0-9]+ [0-9]+(\\.[0-9]{1,2})? [0-9]+ [0-9]"
    							+ "+ N?P)) ?";

    /**
     * Regex matching a delete transaction
     */
    private static final String DELETE_REGEX = "delete [a-z0-9]+ ?";

    /**
     * Regex matching a search transaction
     */
    private static final String SEARCH_REGEX = "search [a-z0-9]+ ?";

    /**
     * Regex matching an update transaction
     */
    private static final String UPDATE_REGEX = "update [a-z0-9]+ [0-9]+ ?";

    /**
     * Regex matching a print transaction
     */
    private static final String PRINT_REGEX = "print ?";


    private TransType transactionType = TransType.NONE;

    /**
     * Sets the type of each transaction using the TransType enum
     * @return TransType
     */
    public TransType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransType transactionType) {
        this.transactionType = transactionType;
    }

    public PurchaseItem getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem(PurchaseItem transactionItem) {
        this.transactionItem = transactionItem;
    }

    /**
     * Contains any needed information in the form of a purchase item object
     *  to process the transaction.
     */
    private PurchaseItem transactionItem;

    /**
     * Initializes this transaction object with its appropriate type and item
     * information while checking for validity.
     * @param transactionStr Input string representing a transaction
     * the shopping cart can perform
     * @throws InvalidTransactionException When an invalid exception was entered
     */
    public Transaction(String transactionStr) throws InvalidTransactionException {
        final int BEGIN_INDEX = 0;
        String type;
        try {
            //get rid of any whitespace that isn't a single space
            transactionStr.replaceAll("[\\t ]+", " ");
            if (transactionStr.startsWith(" ")) transactionStr.replaceFirst(" ", "");
            if (!transactionStr.toLowerCase().matches(PRINT_REGEX)) {
                //split using single space as delimiter
                type = transactionStr.substring(BEGIN_INDEX, transactionStr.indexOf(" "));
            } else{
                //if a print transaction, just parse it and then return
                parsePrint(transactionStr);
                return;
            }
            //if empty transaction is invalid
            switch (type.toLowerCase()) {
                case "insert":
                    if (!transactionStr.toLowerCase().matches(INSERT_REGEX.toLowerCase()))
                        throw new InvalidTransactionException(transactionStr);
                    else {
                        parseInsert(transactionStr);
                    }
                    break;
                case "delete":
                    if (!transactionStr.toLowerCase().matches(DELETE_REGEX))
                        throw new InvalidTransactionException(transactionStr);
                    else {
                        parseDelete(transactionStr);
                    }
                    break;
                case "search":
                    if (!transactionStr.toLowerCase().matches(SEARCH_REGEX))
                        throw new InvalidTransactionException(transactionStr);
                    else {
                        parseSearch(transactionStr);
                    }
                    break;
                case "update":
                    if (!transactionStr.toLowerCase().matches(UPDATE_REGEX))
                        throw new InvalidTransactionException(transactionStr);
                    else {
                        parseUpdate(transactionStr);
                    }
                    break;
                default:
                    throw new InvalidTransactionException(transactionStr);
            }
        } catch (InvalidTransactionException i) {
            i.printError();
        }
    }

    /**
     * Parses a print transaction
     * @param printStr Transaction input string
     */
    private void parsePrint(String printStr) {
        transactionType = TransType.PRINT;
    }

    /**
     * Parses an update transaction
     * @param updateStr Transaction input string
     */
    private void parseUpdate(String updateStr) {
        transactionType = TransType.UPDATE;
        transactionItem = new PurchaseItem(
                updateStr.split(" ")[1], Double.parseDouble(updateStr.split(" ")[2]), 0, 0);
    }

    /**
     * Parses a search transaction
     * @param searchStr Transaction input string
     */
    private void parseSearch(String searchStr) {
        transactionType = TransType.SEARCH;
        transactionItem = new PurchaseItem(searchStr.split(" ")[1], 0, 0, 0);
    }

    /**
     * Parses a delete transaction
     * @param deleteStr Transaction input string
     */
    private void parseDelete(String deleteStr) {
        transactionType = TransType.DEL;
        transactionItem = new PurchaseItem(deleteStr.split(" ")[1], 0, 0, 0);
    }

    /**
     * Parses an insert transaction
     * @param insertStr Transaction input string
     */
    private void parseInsert(String insertStr) {
        String[] splitInsert = insertStr.split(" ");
        switch (splitInsert[1]) {
            case "clothing":
                transactionType = TransType.CLOTH;
                transactionItem = new Clothing(
                        splitInsert[2], Double.parseDouble(splitInsert[3]), Integer.parseInt(splitInsert[4]),
                        Integer.parseInt(splitInsert[5]));
                break;
            case "electronics":
                transactionType = TransType.ELECT;
                transactionItem = new Electronics(
                        splitInsert[2], Double.parseDouble(splitInsert[3]), Integer.parseInt(splitInsert[4]),
                        Integer.parseInt(splitInsert[5]), splitInsert[6].matches("F"), splitInsert[7]);
                break;
            case "groceries":
                transactionType = TransType.GROCERY;
                transactionItem = new Grocery(
                        splitInsert[2], Double.parseDouble(splitInsert[3]), Integer.parseInt(splitInsert[4]),
                        Integer.parseInt(splitInsert[5]), splitInsert[6].matches("P"));
                break;
        }
    }

}
