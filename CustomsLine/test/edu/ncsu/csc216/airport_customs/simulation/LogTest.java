package edu.ncsu.csc216.airport_customs.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor;

/**
 * Testing for Log Object
 * @author emilyring
 *
 */
public class LogTest {
	private int numCompleted;
	private Resident a;
	private Visitor b;

	/**
	 * setup for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		numCompleted = 0;
		a = new Resident(27, 200);
		b = new Visitor(156, 180);
	}

	/**
	 * testing for method getNumCompleted
	 */
	@Test
	public void testGetNumCompleted() {
		assertEquals(0, numCompleted);
		
		numCompleted = 9;
		
		assertEquals(9, numCompleted);
	}

	/**
	 * testing for method logItem
	 */
	@Test
	public void testLogItem() {
		Log log = new Log();
		assertEquals(0, log.getNumCompleted());
		a.setWaitingProcessing(false);
		b.setWaitingProcessing(false);
		
		log.logItem(a);
		assertEquals(1, log.getNumCompleted());
		assertEquals(0, log.getTotalWaitTime(), 001);
		assertEquals(200, log.getTotalProcessTime());	
		
	}

	/**
	 * testing for method averageWaitTime
	 */
	@Test
	public void testAverageWaitTime() {
		Log log = new Log();
		assertEquals(0, log.getNumCompleted());
		a.setWaitingProcessing(false);
		b.setWaitingProcessing(false);
		
		log.logItem(a);
		assertEquals(1, log.getNumCompleted());
		assertEquals(0.0, a.getWaitTime(), .001);
		
		b.setWaitTime(71);
		log.logItem(b);
		assertEquals(2, log.getNumCompleted());
		assertEquals(71, b.getWaitTime());
		System.out.println("Average Wait Time b " + log.averageWaitTime());
		assertEquals(.59, log.averageWaitTime(), .01);

	}

	/**
	 * testing for method averageProcessTime
	 */
	@Test
	public void testAverageProcessTime() {
		Log log = new Log();
		assertEquals(0, log.getNumCompleted());
		a.setWaitingProcessing(false);
		b.setWaitingProcessing(false);
		
		log.logItem(a);
		assertEquals(1, log.getNumCompleted());
		assertEquals(3.33, log.averageProcessTime(), .01);
		
		log.logItem(b);
		assertEquals(2, log.getNumCompleted());
		System.out.println("Average process time " + log.averageProcessTime());
		assertEquals(3.16, log.averageProcessTime(), .01);
	}

}
