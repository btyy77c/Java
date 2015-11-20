/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Tests currency collection class
 * @author emilyring
 *
 */
public class CurrencyCollectionTest {
	
	private CurrencyCollection collection; 
	private CurrencyCollection modifier; 

	/**
	 * Sets up variables for testing
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		collection = new CurrencyCollection(10);
		
		//used to add or subtract money and interact with the collection object
		modifier = new CurrencyCollection(0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyAtIdx(int)}
	 * 
	 * Test 
	 */
	@Test
	public void testGetCurrencyAtIdx() {
		
		//create 3 identical pennies
		Currency penny1 = new Currency(CurrencyCollection.PENNY_VALUE, 
				CurrencyCollection.PENNY_NAME, 10);
		Currency penny2 = collection.getCurrencyAtIdx(0);
		Currency penny3 = collection.getCurrencyAtIdx(0);
		
		//checks that penny1 and penny2 are equal
		assertTrue(penny2.equals(penny1));
		
		//Creates error if index int is not valid
		try{
			penny3 = collection.getCurrencyAtIdx(-7);
		} catch (IndexOutOfBoundsException e){
			assertTrue(penny2.equals(penny3));
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#modifyDenomination(int, int)}.
	 * 
	 * Checks changing amounts of money 
	 */
	@Test
	public void testModifyDenomination() {
		//modify amount of five dollar amount
		collection.modifyDenomination(CurrencyCollection.FIVE_VALUE, -1);
		//confirms new balance 
		assertEquals(35910, collection.getBalance());
		
		//Try to modify a value that does not exists
		try{
			collection.modifyDenomination(-100, 8);
		} catch (IllegalArgumentException e){
			assertEquals(35910, collection.getBalance());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#depositCurrencyCollection(edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 */
	@Test
	public void testDepositCurrencyCollection() {
		//check initial balance
		assertEquals(36410, collection.getBalance());
		
		//add money to modifier and check balance
		modifier.modifyDenomination(CurrencyCollection.FIVE_VALUE, 2);
		assertEquals(1000, modifier.getBalance());
		
		//add modifier money to collection and check new collection balance
		collection.depositCurrencyCollection(modifier);
		assertEquals(37410, collection.getBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#refundByAmount(int)}.
	 */
	@Test
	public void testRefundByAmount() {
		//check initial balance
		assertEquals(36410, collection.getBalance());
		
		modifier = collection.refundByAmount(10);
		
		//check amount returned
		assertEquals(10, modifier.getBalance());
		
		//check collection amount has changed
		assertEquals(36400, collection.getBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyCollection()}.
	 */
	@Test
	public void testGetCurrencyCollection() {
		
		Currency[] test = collection.getCurrencyCollection();
		
		Currency penny = new Currency(CurrencyCollection.PENNY_VALUE, 
				CurrencyCollection.PENNY_NAME, 10);
		assertTrue(penny.equals(test[0]));
		
		Currency nickel = new Currency(CurrencyCollection.NICKEL_VALUE, 
				CurrencyCollection.NICKEL_NAME, 10);
		assertTrue(nickel.equals(test[1]));
		
		Currency dime = new Currency(CurrencyCollection.DIME_VALUE, 
				CurrencyCollection.DIME_NAME, 10);
		assertTrue(dime.equals(test[2]));
		
		Currency quarter = new Currency(CurrencyCollection.QUARTER_VALUE, 
				CurrencyCollection.QUARTER_NAME, 10);
		assertTrue(quarter.equals(test[3]));
		
		Currency dollar = new Currency(CurrencyCollection.ONE_VALUE, 
				CurrencyCollection.ONE_NAME, 10);
		assertTrue(dollar.equals(test[4]));
		
		Currency five = new Currency(CurrencyCollection.FIVE_VALUE, 
				CurrencyCollection.FIVE_NAME, 10);
		assertTrue(five.equals(test[5]));
		
		Currency ten = new Currency(CurrencyCollection.TEN_VALUE, 
				CurrencyCollection.TEN_NAME, 10);
		assertTrue(ten.equals(test[6]));
		
		Currency twenty = new Currency(CurrencyCollection.TWENTY_VALUE, 
				CurrencyCollection.TWENTY_NAME, 10);
		assertTrue(twenty.equals(test[7]));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getBalance()}.
	 * Check balance of Curreny array
	 */
	@Test
	public void testGetBalance() {
		assertEquals(36410, collection.getBalance());
	}

}
