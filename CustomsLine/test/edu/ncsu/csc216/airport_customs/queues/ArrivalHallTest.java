package edu.ncsu.csc216.airport_customs.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * Testing for ArrivalHall Object 
 * @author emilyring
 *
 */
public class ArrivalHallTest {
	
	private Log log;
	private CustomsDesk desk1;
	private CustomsDesk desk2;
	private CustomsDesk desk3;
	private CustomsDesk [] deskLog;
	private int numPassengers;

	/**
	 * Setuo for testing
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
		
		numPassengers = 1;
	}

	/**
	 * testing for method hasNext
	 */
	@Test
	public void testHasNext() {
		ArrivalHall hall = new ArrivalHall(numPassengers, deskLog);
		assertTrue(hall.hasNext());
		
		hall.processNext();
		assertFalse(hall.hasNext());
	}

	/**
	 * testing for method processNext
	 */
	@Test
	public void testProcessNext() {
		numPassengers = 3;
		
		//desks are empty
		assertEquals(0, deskLog[0].size());
		assertEquals(0, deskLog[1].size());
		assertEquals(0, deskLog[2].size());
		
		// Create arrival hall and check it has 3 passengers
		ArrivalHall hall = new ArrivalHall(numPassengers, deskLog);		
		assertEquals(3, hall.size());
		
		
		//check to make sure 1 passenger was removed from arrival hall
		Passenger a = hall.processNext();
		assertEquals(2, hall.size());
		
		//check passenger is waiting in line
		assertTrue(a.isWaitingInCustomsLine());
		
		//get line index and check passenger has been moved to that custom desk
		assertTrue(a.getLineIndex() >= 0);
		int b = a.getLineIndex();
		assertEquals(1, deskLog[b].size());
	}

	/**
	 * testing for method departTie=meNext
	 */
	@Test
	public void testDepartTimeNext() {
		ArrivalHall hall = new ArrivalHall(numPassengers, deskLog);
		assertEquals(hall.departTimeNext(), hall.processNext().getArrivalTime());
	}


	
	/**
	 * testing for method size 
	 */
	@Test
	public void testSize() {
		ArrivalHall hall = new ArrivalHall(numPassengers, deskLog);
		assertEquals(1, hall.size());
		
		numPassengers = 3;
		ArrivalHall hall2 = new ArrivalHall(numPassengers, deskLog);
		assertEquals(3, hall2.size());
	}

}
