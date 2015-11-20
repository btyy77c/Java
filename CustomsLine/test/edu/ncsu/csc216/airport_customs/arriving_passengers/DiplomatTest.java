package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * Testing class for Diplomat Object 
 * @author emilyring
 *
 */
public class DiplomatTest {
	
	private Log log;
	private CustomsDesk desk1;
	private CustomsDesk desk2;
	private CustomsDesk desk3;
	private CustomsDesk [] deskLog;
	
	/**
	 * setup for testing 
	 * @throws Exception
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
	 * testing for method getInLine
	 */
	@Test
	public void testGetInLine() {
		Diplomat diplomat = new Diplomat(0, 30);
		assertEquals(-1, diplomat.getLineIndex());
		assertFalse(diplomat.isWaitingInCustomsLine());
		
		diplomat.getInLine(deskLog);
		
		assertEquals(1, diplomat.getLineIndex());
		assertTrue(diplomat.isWaitingInCustomsLine());
		assertTrue(deskLog[1].size() == 1);
	}

	/**
	 * testing for method getColor
	 */
	@Test
	public void testGetColor() {
		Diplomat diplomat1 = new Diplomat(0, 30);
		assertEquals(new Color(153, 255, 153), diplomat1.getColor());
		
		Diplomat diplomat2 = new Diplomat(0, 90);
		assertEquals(Color.GREEN, diplomat2.getColor());
		
		Diplomat diplomat3 = new Diplomat(0, 61);
		assertEquals(Color.GREEN, diplomat3.getColor());
	}

}
