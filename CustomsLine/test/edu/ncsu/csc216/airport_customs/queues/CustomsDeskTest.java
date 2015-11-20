package edu.ncsu.csc216.airport_customs.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * Testing for CustomDesk Object
 * @author emilyring
 *
 */
public class CustomsDeskTest {
	private Log log;
	private Resident a;
	private Visitor b;
	private Visitor c;

	/**
	 * setup for testing 
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		log = new Log();
		a = new Resident(27, 200);
		b = new Visitor(156, 180);
		c = new Visitor(160, 235);
	}

	/**
	 * testing for method size 
	 */
	@Test
	public void testSize() {
		CustomsDesk desk = new CustomsDesk(log);
		assertEquals(0, desk.size());

	}

	/**
	 * testing for method processNext 
	 */
	@Test
	public void testProcessNext() {
		CustomsDesk desk = new CustomsDesk(log);
		assertEquals(0, desk.size());
		
		desk.addToLine(a);
		desk.addToLine(b);
		desk.addToLine(c);
		assertEquals(3, desk.size());
		
		a = (Resident) desk.processNext();
		assertEquals(2, desk.size());
		assertFalse(a.isWaitingInCustomsLine());
		
	}

	/**
	 * testing for method hasNext
	 */
	@Test
	public void testHasNext() {
		CustomsDesk desk = new CustomsDesk(log);
		assertFalse(desk.hasNext());
		
		desk.addToLine(a);
		assertTrue(desk.hasNext());
	}

	/**
	 * testing for method departTimeNext
	 */
	@Test
	public void testDepartTimeNext() {
		CustomsDesk desk = new CustomsDesk(log);
		assertEquals(0, desk.size());
		
		desk.addToLine(a);
		desk.addToLine(b);
		desk.addToLine(c);
		assertEquals(3, desk.size());
		
		assertEquals(227, desk.departTimeNext());
		desk.processNext();
		assertEquals(407, desk.departTimeNext());
		desk.processNext();
		assertEquals(642, desk.departTimeNext());
		desk.processNext();
		assertEquals(Integer.MAX_VALUE, desk.departTimeNext());
		
		
	}

	/**
	 * testing for method assToLine
	 */
	@Test
	public void testAddToLine() {
		CustomsDesk desk = new CustomsDesk(log);
		assertEquals(0, desk.size());
		
		desk.addToLine(a);
		assertEquals(1, desk.size());
		assertEquals(0, a.getWaitTime());
		assertEquals(227, desk.getTimeWhenAvailable());
		
		desk.addToLine(b);
		assertEquals(2, desk.size());
		assertEquals(407, desk.getTimeWhenAvailable());
		assertEquals(71, b.getWaitTime());
		
		desk.addToLine(c);
		assertEquals(3, desk.size());
		assertEquals(642, desk.getTimeWhenAvailable());
		assertEquals(247, c.getWaitTime());
		
		
	}

}
