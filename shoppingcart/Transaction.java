package shoppingcart;

import shoppingcart.errors.InvalidTransactionException;

public class Transaction {

	private static final String INSERT_REGEX = 
			"insert (clothing|electronics|groceries) [a-z0-9]+ [0-9]+(.[0-9]{1,2})?"
			+ " [0-9]+ [0-9]+ ?(N?F)?(N?P)? ?";
	private static final String DELETE_REGEX = "delete [a-z0-9]+ ?";
	private static String SEARCH_REGEX = "search [a-z0-9]+ ?";
	private static String UPDATE_REGEX = "update [a-z0-9]+ [0-9]+ ?";
	private static String PRINT_REGEX = "print ?";
	
	private char transactionType = 'P';
	
	private PurchaseItem transactionItem;
	
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

	private void parsePrint(String printStr) {
		transactionType = 'P';
	}

	private void parseUpdate(String updateStr) {
		transactionType = 'U';
		transactionItem = new PurchaseItem(
				updateStr.split(" ")[1], Integer.parseInt(updateStr.split(" ")[2]));
	}

	private void parseSearch(String searchStr) {
		transactionType = 'S';
		transactionItem = new PurchaseItem(searchStr.split(" ")[1], 0);
	}

	private void parseDelete(String deleteStr) {
		transactionType = 'D';
		transactionItem = new PurchaseItem(deleteStr.split(" ")[1], 0);
	}

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
