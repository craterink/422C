package assignment3;

import shoppingcart.*;
import ui.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import bank.Bank;
import bank.Transaction;
import bank.errors.IllegalTransactionException;

public class A3Driver 
	{

	  public static void main(String[] args) 
	  {
		  FileReader freader = new FileReader(args[0]);//args[0]);
		  BufferedReader reader = new BufferedReader(freader);
		  ShoppingCart cart = new ShoppingCart();
		  for (String line = reader.readLine(); line != null; line = reader.readLine()) 
		  {
				System.out.println(cart.performTransaction(new Transaction(line)));
		  }
		  cart.print();
	  }

}
