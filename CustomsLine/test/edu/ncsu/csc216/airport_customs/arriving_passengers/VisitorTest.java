/**
 * 
 */
package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * Tests Variables in Resident Object
 * @author emilyring
 *
 */
public class VisitorTest {
	
	private Log log;
	private CustomsDesk desk1;
	private CustomsDesk desk2;
	private CustomsDesk desk3;
	private CustomsDesk [] deskLog;

	/**
	 * setup for testing
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log = new Log();
		desk1 = new CustomsDesk(log);
		desk2 = new CustomsDesk(log);
		desk3 = new CustomsDesk(log);
		
		deskLog = new CustomsDesk[3];
		deskLog[0] = desk1;
		deskLog[1] = desk2;
		deskLog[2] = desk3;
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor#getInLine(edu.ncsu.csc216.airport_customs.queues.CustomsDesk[])}.
	 */
	@Test
	public void testGetInLine() {
		Visitor visitor = new Visitor(0, 180);
		assertEquals(-1, visitor.getLineIndex());
		assertFalse(visitor.isWaitingInCustomsLine());
		
		visitor.getInLine(deskLog);
		
		assertEquals(2, visitor.getLineIndex());
		assertTrue(visitor.isWaitingInCustomsLine());
		assertTrue(deskLog[2].size() == 1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Visitor visitor1 = new Visitor(0, 180);
		assertEquals(new Color(255, 153, 153), visitor1.getColor());
		
		Visitor visitor2 = new Visitor(0, 600);
		assertEquals(Color.RED, visitor2.getColor());
		
		Visitor visitor3 = new Visitor(0, 391);
		assertEquals(Color.RED, visitor3.getColor());
	}

}
