/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CashRegister class
 * 
 * @author emilyring
 * 
 */
public class CashRegisterTest {
	
	private CashRegister drawer;
	private CurrencyCollection collection; 

	/**
	 * setup CurrencyCollection for testing
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		drawer = new CashRegister();
		collection = new CurrencyCollection(0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#getCurrentBalance
	 * ()}.
	 * 
	 * tests current balance of register 
	 */
	@Test
	public void testGetCurrentBalance() {
		//Test current balance of register 
		assertEquals(36410, drawer.getCurrentBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processPurchase
	 * (int, edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 * 
	 */
	@Test
	public void testProcessPurchase() {
		
		//Attempt to process payment with no money from customer
		try{
			drawer.processPurchase(1357, collection);
		} catch (IllegalArgumentException e){
			//check to make sure amount has not changed
			assertEquals(36410, drawer.getCurrentBalance());			
		}
		
		//Add money to customer account
		collection.modifyDenomination(CurrencyCollection.FIVE_VALUE, 4);
		assertEquals((CurrencyCollection.FIVE_VALUE * 4), collection.getBalance());
		
		//Pay for item
		drawer.processPurchase(1357, collection);
		
		//amount left in cash register
		assertEquals(37767, drawer.getCurrentBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processRefund
	 * (int)}.
	 */
	@Test
	public void testProcessRefund() {
		
		//remove 5 dollars from cash register
		drawer.processRefund(500);
		
		//new cash register amount
		assertEquals(35910, drawer.getCurrentBalance());
	}

}
