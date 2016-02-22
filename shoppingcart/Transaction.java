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
	 * Regex matching valid U.S. state abbreviations.
	 */
	private static final String STATES_REGEX = "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|"
			+ "IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI"
			+ "|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY";
	
	/**
	 * Regex matching an insert transaction
	 */
	private static final String INSERT_REGEX = "insert ((clothing)|(electronics)|(groceries)) "
			+ "[a-z0-9]+ [0-9]+(\\.[0-9]{1,2})? [0-9]+ [0-9]+(?(2))(?(3) N?F (" + STATES_REGEX
			+ "))(?(4) N?P) ?";
	
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
	 * C for insert clothing
	 * E for insert electronics
	 * G for insert groceries
	 * D for delete
	 * S for search
	 * P for print
	 * U for update
	 */
	private char transactionType = 0;
	
	public char getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(char transactionType) {
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
		//get rid of any whitespace that isn't a single space
		transactionStr.replaceAll("[\\t ]+", " ");
		if(transactionStr.startsWith(" ")) transactionStr.replaceFirst(" ", "");
		//split using single space as delimiter
		String type = transactionStr.substring(0, transactionStr.indexOf(" "));
		//if empty transaction is invalid
		switch(type.toLowerCase()) {
		case "insert":
			if(!transactionStr.toLowerCase().matches(INSERT_REGEX.toLowerCase())) 
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
				updateStr.split(" ")[1], Double.parseDouble(updateStr.split(" ")[2]), 0, 0);
	}

	/**
	 * Parses a search transaction
	 * @param searchStr Transaction input string
	 */
	private void parseSearch(String searchStr) {
		transactionType = 'S';
		transactionItem = new PurchaseItem(searchStr.split(" ")[1], 0, 0, 0);
	}

	/**
	 * Parses a delete transaction
	 * @param deleteStr Transaction input string
	 */
	private void parseDelete(String deleteStr) {
		transactionType = 'D';
		transactionItem = new PurchaseItem(deleteStr.split(" ")[1], 0, 0, 0);
	}

	/**
	 * Parses an insert transaction
	 * @param insertStr Transaction input string
	 */
	private void parseInsert(String insertStr) {
		String[] splitInsert = insertStr.split(" ");
		switch(splitInsert[1]) {
			case "clothing":
				transactionType = 'C';
				transactionItem = new Clothing(
						splitInsert[2], Double.parseDouble(splitInsert[3]), Integer.parseInt(splitInsert[4]),
						Integer.parseInt(splitInsert[5]));
				break;
			case "electronics":
				transactionType = 'E';
				transactionItem = new Electronics(
						splitInsert[2], Double.parseDouble(splitInsert[3]), Integer.parseInt(splitInsert[4]),
						Integer.parseInt(splitInsert[5]), splitInsert[6].matches("F"), splitInsert[7]);
				break;
			case "groceries":
				transactionType = 'G';
				transactionItem = new Grocery(
						splitInsert[2], Double.parseDouble(splitInsert[3]), Integer.parseInt(splitInsert[4]),
						Integer.parseInt(splitInsert[5]), splitInsert[6].matches("P"));
				break;
		}
	}
	
}
