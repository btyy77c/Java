package edu.ncsu.csc216.airport_customs.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing for Simulator Object 
 * @author emilyring
 *
 */
public class SimulatorTest {
	private int numPassengers;
	private int numCustomsDesks;
	

	/**
	 * setup for testing 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		numCustomsDesks = 3;
		numPassengers = 1;
		
	}

	/**
	 * testing for method step
	 */
	@Test
	public void testStep() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertEquals(0, s.getStepsTaken());
		s.step();
		assertEquals(1, s.getStepsTaken());
	}

	/**
	 * testing for method getStepsTaken
	 */
	@Test
	public void testGetStepsTaken() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertEquals(0, s.getStepsTaken());
		s.step();
		assertEquals(1, s.getStepsTaken());
	}

	/**
	 * testing for method numberOfSteps
	 */
	@Test
	public void testTotalNumberOfSteps() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertEquals(2, s.totalNumberOfSteps());
	}

	/**
	 * testing for method moreSteps
	 */
	@Test
	public void testMoreSteps() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertTrue(s.moreSteps());
		s.step();
		assertTrue(s.moreSteps());
		s.step();
		assertFalse(s.moreSteps());
	}

	/**
	 * testing for method getCurrentIndex
	 */
	@Test
	public void testGetCurrentIndex() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertEquals(-1, s.getCurrentIndex());
		
		s.step();
		
		int a = s.getCurrentIndex();
		assertTrue(a < 3 && a >= 0);
		
		s.step();
		a = s.getCurrentIndex();
		assertTrue(a >= 0);
		
	}

	/**
	 * testing for method passengerClearedCustoms
	 */
	@Test
	public void testPassengerClearedCustoms() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertFalse(s.passengerClearedCustoms());
		s.step();
		assertFalse(s.passengerClearedCustoms());
		s.step();
		assertTrue(s.passengerClearedCustoms());
	}

	/**
	 * testing for method averageWaitTime
	 */
	@Test
	public void testAverageWaitTime() {
		Simulator s = new Simulator(numCustomsDesks, numPassengers);
		assertEquals(0, s.averageWaitTime(), .001);
	}

}
