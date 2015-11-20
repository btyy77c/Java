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
 * Tests Variables in Resident Object and Variables in Passenger Object
 * @author emilyring
 *
 */
public class ResidentTest {
	
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
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Resident#getInLine(edu.ncsu.csc216.airport_customs.queues.CustomsDesk[])}.
	 */
	@Test
	public void testGetInLine() {
		Resident resident1 = new Resident(0, 120);
		assertEquals(-1, resident1.getLineIndex());
		assertFalse(resident1.isWaitingInCustomsLine());
		
		resident1.getInLine(deskLog);
		
		assertEquals(1, resident1.getLineIndex());
		assertTrue(resident1.isWaitingInCustomsLine());
		assertEquals(1, deskLog[1].size());
		
		Resident resident2 = new Resident(0, 120);
		assertEquals(-1, resident2.getLineIndex());
		assertFalse(resident2.isWaitingInCustomsLine());
		
		resident2.getInLine(deskLog);
		System.out.println(resident2.getLineIndex());
		assertEquals(0, resident2.getLineIndex());
		
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Resident#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Resident resident1 = new Resident(0, 120);
		assertEquals(new Color(153, 153, 255), resident1.getColor());
		
		Resident resident2 = new Resident(0, 300);
		assertEquals(Color.BLUE, resident2.getColor());
		
		Resident resident3 = new Resident(0, 211);
		assertEquals(Color.BLUE, resident3.getColor());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		Resident resident1 = new Resident(0, 120);
		assertEquals(0, resident1.getArrivalTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		Resident resident1 = new Resident(0, 120);
		resident1.setWaitTime(5);
		assertEquals(5, resident1.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		Resident resident1 = new Resident(0, 120);
		resident1.setWaitTime(5);
		assertEquals(5, resident1.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		Resident resident1 = new Resident(0, 120);
		assertEquals(120, resident1.getProcessTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#getLineIndex()}.
	 */
	@Test
	public void testGetLineIndex() {
		
		Resident resident1 = new Resident(0, 120);
		assertEquals(-1, resident1.getLineIndex());
		
		resident1.getInLine(deskLog);
		assertEquals(1, resident1.getLineIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#isWaitingInCustomsLine()}.
	 */
	@Test
	public void testIsWaitingInCustomsLine() {
		Resident resident1 = new Resident(0, 120);
		assertFalse(resident1.isWaitingInCustomsLine());
		
		resident1.getInLine(deskLog);
		
		assertTrue(resident1.isWaitingInCustomsLine());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#removeFromWaitingLine()}.
	 */
	@Test
	public void testRemoveFromWaitingLine() {
		Resident resident1 = new Resident(0, 120);
		assertFalse(resident1.isWaitingInCustomsLine());
		
		resident1.getInLine(deskLog);
		
		assertTrue(resident1.isWaitingInCustomsLine());
		
		resident1.removeFromWaitingLine();
		assertFalse(resident1.isWaitingInCustomsLine());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#setLineIndex(int)}.
	 */
	@Test
	public void testSetLineIndex() {
		Resident resident1 = new Resident(0, 120);
		resident1.setLineIndex(4);
		assertEquals(4, resident1.getLineIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger#setWaitingProcessing(java.lang.Boolean)}.
	 */
	@Test
	public void testSetWaitingProcessing() {
		Resident resident1 = new Resident(0, 120);
		assertFalse(resident1.isWaitingInCustomsLine());
		
		resident1.setWaitingProcessing(true);
		assertTrue(resident1.isWaitingInCustomsLine());
		
	}

}
