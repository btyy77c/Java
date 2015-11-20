package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.*;

/**
 * Testing the Repair Center and Droid classes
 * 
 * @author Emilt Ring 
 *
 */
public class RepairCenterTest {
	private RepairCenter repairCenter;

	/**
	 * Setup information for testing 
	 * @throws Exception errors
	 */
	@Before
	public void setUp() throws Exception {
		TechDroid.startDroidNumberingAt01();
	}
	
	/**
	 * tests adding a droid to the repair center list
	 */
	@Test
	public void testAddTechDroid(){
		repairCenter = new RepairCenter();
		assertEquals(5, repairCenter.totalNumberOfDroids());

		for (int i = 0; i < 35; i++){
			repairCenter.addTechDroid();
		}
		
		assertEquals(30, repairCenter.totalNumberOfDroids());
	}
	
	/**
	 * Tests the ability to locate a droid in the list 
	 */
	@Test
	public void testGetDroidAt(){
		repairCenter = new RepairCenter();
		assertEquals(5, repairCenter.totalNumberOfDroids());
		
		assertEquals("05V", repairCenter.getDroidAt(0).getDroidID());
		
		for (int i = 0; i < 35; i++){
			repairCenter.addTechDroid();
		}
		
		assertEquals('C', repairCenter.getDroidAt(29).getDroidID().charAt(2));
	}
	
	/**
	 * Tests the accuracy of the number of avaiable droids produced 
	 * @throws BadDeviceInformationException if device is missing required information
	 * @throws DroidBusyException if Droid has already been assigned
	 * @throws DroidDeviceMismatchException if droid and device do not match 
	 */
	@Test
	public void testNumberOfAvailableDroids() throws BadDeviceInformationException, DroidBusyException, 
	DroidDeviceMismatchException{
		repairCenter = new RepairCenter();
		assertEquals(5, repairCenter.totalNumberOfDroids());
		assertEquals(5, repairCenter.numberOfAvailableDroids());
		
		Device dev1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev1.toString());
		
		repairCenter.getDroidAt(4).assign(dev1);
		assertEquals(4, repairCenter.numberOfAvailableDroids());
		
		repairCenter.getDroidAt(3).assign(dev1);
		assertEquals(3, repairCenter.numberOfAvailableDroids());
	}
	
	/**
	 * Test the printed list of droids which appear in the program 
	 * @throws BadDeviceInformationException if device is missing required information
	 * @throws DroidBusyException if Droid has already been assigned
	 * @throws DroidDeviceMismatchException if droid and device do not match 
	 */
	@Test
	public void testPrintDroids() throws DroidBusyException, DroidDeviceMismatchException, 
	BadDeviceInformationException{
		repairCenter = new RepairCenter();
		assertEquals("05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: UNASSIGNED", 
				repairCenter.printDroids());
		
		
		Device dev1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev1.toString());
		
		repairCenter.getDroidAt(4).assign(dev1);
		assertEquals("05V: UNASSIGNED\n03V: UNASSIGNED\n02E: UNASSIGNED\n01C: UNASSIGNED\n04C: NC123456 Doe, Jane", 
				repairCenter.printDroids());
	}
	
	/**
	 * Test removing device information from a droid in the repair center 
	 * @throws BadDeviceInformationException if device is missing required information
	 * @throws DroidBusyException if Droid has already been assigned
	 * @throws DroidDeviceMismatchException if droid and device do not match 
	 */
	@Test
	public void testRelease() throws DroidBusyException, DroidDeviceMismatchException, 
	BadDeviceInformationException{
		repairCenter = new RepairCenter();
		assertEquals(5, repairCenter.totalNumberOfDroids());
		assertEquals(5, repairCenter.numberOfAvailableDroids());
		
		Device dev1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev1.toString());
		
		repairCenter.getDroidAt(4).assign(dev1);
		assertEquals(4, repairCenter.numberOfAvailableDroids());
		
		repairCenter.release(4);
		assertEquals(5, repairCenter.numberOfAvailableDroids());
	}

}
