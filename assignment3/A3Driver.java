package assignment3;

import shoppingcart.*;
import ui.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import bank.Bank;
import bank.Transaction;
import bank.errors.IllegalTransactionException;

/**
 * Driver for EE 422C Assignment 3
 * @author Cooper Raterink, Aria Pahlavan
 * EIDs: cdr2678, ******
 * Lab section: Friday 2-3:30pm
 */
public class A3Driver 
	{

	/**
	 * Performs all transactions from the input file and then prints a "receipt"
	 * representing the final shopping cart state.
	 * @param args Transaction file path/name is first argument.
	 */
	  public static void main(String[] args) 
	  {
		  FileReader freader = new FileReader(args[0]);
		  BufferedReader reader = new BufferedReader(freader);
		  ShoppingCart cart = new ShoppingCart();
		  //Read one line at a time, parse, then perform on cart
		  for (String line = reader.readLine(); line != null; line = reader.readLine()) 
		  {
				System.out.println(cart.performTransaction(new Transaction(line)));
		  }
		  cart.print();
	  }

}
