package assignment3;

import shoppingcart.*;
import shoppingcart.errors.InvalidOperationException;

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
	 * Performs all operations from the input file and then prints a "receipt"
	 * representing the final shopping cart state.
	 * @param args operation file path/name is first argument.
	 * @throws IOException File error
	 * @throws InvalidOperationException If invalid operation is entered //TODO
	 */
	  public static void main(String[] args) throws IOException 
	  {
		  try {
			//Get ready to read the file specified in the args
			  FileReader freader = new FileReader(args[0]);
			  BufferedReader reader = new BufferedReader(freader);
			  //initialize a shopping cart
			  ShoppingCart cart = new ShoppingCart();
			  //Read one line at a time, parse, then perform on cart
			  for (String line = reader.readLine(); line != null; line = reader.readLine()) 
			  {
				  //make sure there are characters in the line first
				  if(line.trim().length() > 0)
				  {
					  Operation op = new Operation(line);
					  if(op.getOperationType() != OperationType.NONE)
						  cart.performOperation(op);
				  }
			  }
			  //print a "receipt"
			  cart.print();
			  reader.close();
		} catch (IOException ioe) { //file error
			System.out.println("Error: File could not be read.");
		}
	  }

}
