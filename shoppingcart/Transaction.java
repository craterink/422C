package shoppingcart;

import shoppingcart.errors.InvalidTransactionException;

/**
 * Class that represents a transaction for the shopping cart to handle.
 * Could be an insert, delete, etc.
 * @author Cooper
 *
 */
public class Transaction {

	/**
	 * Regex matching an insert transaction
	 */
	private static final String INSERT_REGEX = 
			"insert (clothing|electronics|groceries) [a-z0-9]+ [0-9]+(.[0-9]{1,2})?"
			+ " [0-9]+ [0-9]+ ?(N?F)?(N?P)? ?";
	
	/**
	 * Regex matching a delete transaction
	 */
	private static final String DELETE_REGEX = "delete [a-z0-9]+ ?";
	
	/**
	 * Regex matching a search transaction
	 */
	private static String SEARCH_REGEX = "search [a-z0-9]+ ?";
	
	/**
	 * Regex matching an update transaction
	 */
	private static String UPDATE_REGEX = "update [a-z0-9]+ [0-9]+ ?";
	
	/**
	 * Regex matching a print transaction
	 */
	private static String PRINT_REGEX = "print ?";
	
	/**
	 * Char representing type of transaction:
	 * 0 if invalid
	 * I for insert
	 * D for delete
	 * S for search
	 * P for print
	 * U for update
	 */
	private char transactionType = 0;
	
	/**
	 * Contains any needed information to process the transaction.
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
		//get rid of any whitespace that isn't a single space
		transactionStr.replaceAll("[\\t ]+", " ");
		if(transactionStr.startsWith(" ")) transactionStr.replaceFirst(" ", "");
		//split using single space as delimiter
		String type = transactionStr.substring(0, transactionStr.indexOf(" "));
		//if empty transaction is invalid
		switch(type.toLowerCase()) {
		case "insert":
			if(!transactionStr.toLowerCase().matches(INSERT_REGEX)) 
				throw new InvalidTransactionException();
			else {
				parseInsert(transactionStr);
			}
			break;
		case "delete":
			if(!transactionStr.toLowerCase().matches(DELETE_REGEX)) 
				throw new InvalidTransactionException();
			else {
				parseDelete(transactionStr);
			}
			break;
		case "search":
			if(!transactionStr.toLowerCase().matches(SEARCH_REGEX)) 
				throw new InvalidTransactionException();
			else {
				parseSearch(transactionStr);
			}
			break;
		case "update":
			if(!transactionStr.toLowerCase().matches(UPDATE_REGEX)) 
				throw new InvalidTransactionException();
			else {
				parseUpdate(transactionStr);
			}
			break;
		case "print":
			if(!transactionStr.toLowerCase().matches(PRINT_REGEX)) 
				throw new InvalidTransactionException();
			else {
				parsePrint(transactionStr);
			}
			break;
		default:
			throw new InvalidTransactionException();
		}
	}

	/**
	 * Parses a print transaction
	 * @param printStr Transaction input string
	 */
	private void parsePrint(String printStr) {
		transactionType = 'P';
	}

	/**
	 * Parses an update transaction
	 * @param updateStr Transaction input string
	 */
	private void parseUpdate(String updateStr) {
		transactionType = 'U';
		transactionItem = new PurchaseItem(
				updateStr.split(" ")[1], Integer.parseInt(updateStr.split(" ")[2]));
	}

	/**
	 * Parses a search transaction
	 * @param searchStr Transaction input string
	 */
	private void parseSearch(String searchStr) {
		transactionType = 'S';
		transactionItem = new PurchaseItem(searchStr.split(" ")[1], 0);
	}

	/**
	 * Parses a delete transaction
	 * @param deleteStr Transaction input string
	 */
	private void parseDelete(String deleteStr) {
		transactionType = 'D';
		transactionItem = new PurchaseItem(deleteStr.split(" ")[1], 0);
	}

	/**
	 * Parses an insert transaction
	 * @param insertStr Transaction input string
	 */
	private void parseInsert(String insertStr) {
		transactionType = 'I';
		String[] splitInsert = insertStr.split(" ");
		switch(splitInsert[1]) {
			case "clothing": //////TODO: UPDATE THESE TO MATCH ARIA'S
				transactionItem = new Clothing(
						splitInsert[2], splitInsert[3], splitInsert[4], splitInsert[5]);
				break;
			case "electronics":
				transactionItem = new Electronics(
						splitInsert[2], splitInsert[3], splitInsert[4], splitInsert[5]);
				break;
			case "groceries":
				transactionItem = new Grocery(
						splitInsert[2], splitInsert[3], splitInsert[4], splitInsert[5]);
				break;
		}
	}
	
}
