package assignment3;

import shoppingcart.*;
import shoppingcart.errors.InvalidTransactionException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Driver for EE 422C Assignment 3
 * @author Cooper Raterink, Aria Pahlavan
 * EIDs: cdr2678, ap44342
 * Lab section: Friday 2-3:30pm
 */
public class A3Driver 
	{

	/**
	 * Performs all transactions from the input file and then prints a "receipt"
	 * representing the final shopping cart state.
	 * @param args Transaction file path/name is first argument.
	 * @throws IOException File error
	 * @throws InvalidTransactionException If invalid transaction is entered //TODO
	 */
	  public static void main(String[] args) throws IOException, InvalidTransactionException 
	  {
		  FileReader freader = new FileReader(args[0]);
		  BufferedReader reader = new BufferedReader(freader);
		  ShoppingCart cart = new ShoppingCart();
		  //Read one line at a time, parse, then perform on cart
		  for (String line = reader.readLine(); line != null; line = reader.readLine()) 
		  {
				cart.performTransaction(new Transaction(line));
		  }
		  cart.print();
	  }

}
