/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Currency.  Tests that the object is constructed correctly
 * and that the state of the object is manipulated properly.
 * 
 * @author Emily Ring
 */
public class CurrencyTest {
	
   /** Currency object with the value of a penny */
   private Currency penny;
   /** object for nickel */
   private Currency nickel;
   /**Creates an object for dime */
   private Currency dime;
   /**Creates an object for quarter */
   private Currency quarter;
   /** Currency object with the value of a dollar */
   private Currency dollar;
   /**Creates an object for five*/
   private Currency five;
   /**Creates an object for ten*/
   private Currency ten;
   /**Creates an object for twenty*/
   private Currency twenty;

   /**
    * Sets up the CurrencyTest by creating two representative Currency objects:
    * one with the value of a penny and the other with the value of a dollar.
    */
   @Before
   public void setUp() throws Exception {
      penny = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
      nickel = new Currency(CurrencyCollection.NICKEL_VALUE, CurrencyCollection.NICKEL_NAME, 10);
      dime = new Currency(CurrencyCollection.DIME_VALUE, CurrencyCollection.DIME_NAME, 10);
      quarter = new Currency(CurrencyCollection.QUARTER_VALUE, CurrencyCollection.QUARTER_NAME, 10);
      dollar = new Currency(CurrencyCollection.ONE_VALUE, CurrencyCollection.ONE_NAME, 10);
      five = new Currency(CurrencyCollection.FIVE_VALUE, CurrencyCollection.FIVE_NAME, 10);
      ten = new Currency(CurrencyCollection.TEN_VALUE, CurrencyCollection.TEN_NAME, 10);
      twenty = new Currency(CurrencyCollection.TWENTY_VALUE, CurrencyCollection.TWENTY_NAME, 10);
   }

   /**
	* Test method for {@link edu.ncsu.csc216.cash_register.Currency#hashCode()}.
	*/
@Test
public void testHashCode() {
   //Create a new penny object that has the same state as our 
	   //field named penny
	   Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
		
	   //Assert that both of these objects have the same has code.
	   //When using assertTrue, the expected value is true.  The actual
	   //value is the result of the argument.
	   assertTrue(penny.hashCode() == penny2.hashCode());
		
	   //Assert that the penny and dollar objects have different
	   //hashcodes.
	   assertTrue(penny.hashCode() != dollar.hashCode());
}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getValue()}.
	 *
	 * The test determines if the value returned from the Currency object
	 * is the same as what the value was initialized to.
	 */
	@Test
	public void testGetValue() {
	   //The PENNY_VALUE constant is our expected value
	   //The actual value is the call to the getValue() method
	   assertEquals(CurrencyCollection.PENNY_VALUE, penny.getValue());
	   
	   //Test value of nickel
	   assertEquals(CurrencyCollection.NICKEL_VALUE, nickel.getValue());
	   
	   //Test value of dime
	   assertEquals(CurrencyCollection.DIME_VALUE, dime.getValue());
	   
	   //Test value of quarter 
	   assertEquals(CurrencyCollection.QUARTER_VALUE, quarter.getValue());

	   //The ONE_VALUE constant is our expected value
	   //The actual value is the call to the getValue method
	   assertEquals(CurrencyCollection.ONE_VALUE, dollar.getValue());
	   
	   //Test value of five dollar
	   assertEquals(CurrencyCollection.FIVE_VALUE, five.getValue());
	   
	   //Test value of ten dollar
	   assertEquals(CurrencyCollection.TEN_VALUE, ten.getValue());
	   
	   //Test value of twenty dollar
	   assertEquals(CurrencyCollection.TWENTY_VALUE, twenty.getValue());
	} 

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getName()}.
	 */
	@Test
	public void testGetName() {
		//Test name of penny
		assertEquals(CurrencyCollection.PENNY_NAME, penny.getName());
		
		//Test name of nickel
		assertEquals(CurrencyCollection.NICKEL_NAME, nickel.getName());
		
		//Test name of dime
		assertEquals(CurrencyCollection.DIME_NAME, dime.getName());
		
		//Test name of quarter
		assertEquals(CurrencyCollection.QUARTER_NAME, quarter.getName());
		
		//Test name of dollar 
		assertEquals(CurrencyCollection.ONE_NAME, dollar.getName());	
		
		//Test name of five
		assertEquals(CurrencyCollection.FIVE_NAME, five.getName());
		
		//Test name of ten
		assertEquals(CurrencyCollection.TEN_NAME, ten.getName());
		
		//Test name of twenty
		assertEquals(CurrencyCollection.TWENTY_NAME, twenty.getName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getCount()}.
	 */
	@Test
	public void testGetCount() {
		//Test count entered in penny
		assertEquals(10, penny.getCount());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#modifyCount(int)}. 
	 * 
	 * The test uses the getCount() method to understand how the 
	 * count is changed when using the modifyCount() method
	 */
	@Test
	public void testModifyCount() {
		//Ensure we're starting with 10 pennies
		//We can't assume that the getCount() method has been tested at this point
		//so this is our sanity check that we can use the getCount() method to evaluate
		//modifyCount()
		//10 is our expected value and penny.getCount() is our actual value
		assertEquals(10, penny.getCount());
			
		//Increase the count of pennies by 3
		penny.modifyCount(3);
			
		//Check that the count changed
		assertEquals(13, penny.getCount());
			
		//Decrease the count of pennies by 5
		penny.modifyCount(-5);
			
		//Check that the count changed
		assertEquals(8, penny.getCount());
		
		try {
	        //Decrease the count of pennies by 9
		    penny.modifyCount(-9);
		    fail(); //We should never reach this point, if we do, the test fails
	    } catch (IllegalArgumentException e) {
	        //Check that the count did NOT change
	        assertEquals(8, penny.getCount());
	    }
	}

	/**
	* Test method for {@link edu.ncsu.csc216.cash_register.Currency#equals(java.lang.Object)}.
	*/
@Test
public void testEqualsObject() {
	   //Create a new penny object that has the same state as our 
	   //field named penny
	   Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
	   
	   //Creates penny with different count
	   Currency penny3 = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 5);
	   
	   //Creates penny with different name
	   Currency penny4 = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.DIME_NAME, 10);
	   
	   //Creates penny with different value
	   Currency penny5 = new Currency(800, CurrencyCollection.PENNY_NAME, 10);
	   
	 //field named penny
	   Currency penny6 = new Currency(CurrencyCollection.PENNY_VALUE, null, 10);
	   
				
	   //Assert that both of these objects are the same using the 
	   //equals method.
	   //When using assertTrue, the expected value is true.  The actual
	   //value is the result of the argument.
	   assertTrue(penny.equals(penny2));
	   
	   // penny is itself
	   assertTrue(penny.equals(penny));
			
	   //Assert that the penny and dollar objects are not the same.
	   //When using assertFalse, the expected value is false.  The actual
	   //value is the result of the argument.
	   assertFalse(penny.equals(dollar));
	   
	   //checks penny with different count
	   assertFalse(penny.equals(penny3));
	   
	   //checks penny with different name
	   assertFalse(penny.equals(penny4));
	   
	   //checks penny with different value
	   assertFalse(penny.equals(penny5));
	   
	   //checks for null object
	   assertFalse(penny == null);
	   
	   //checks null name
	   assertFalse(penny6.equals(penny));
   }

}
