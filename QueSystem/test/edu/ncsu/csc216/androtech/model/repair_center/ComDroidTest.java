package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.*;

/**
 * Tests methods included in the ComDroid class and the
 * TechDroid class
 * 
 * @author Emily Ring
 */
public class ComDroidTest {
	private ComDroid com1; 

	/**
	 * Setup for testing Com droid and Techdroid
	 * @throws Exception for errors
	 */
	@Before
	public void setUp() throws Exception {
		TechDroid.startDroidNumberingAt01();
		com1 = new ComDroid();
	}
	
	/**
	 * Tests the creations of String lists of TechDroids 
	 */
	@Test
	public void testToString(){
		ComDroid com2 = new ComDroid();
		ComDroid com10 = new ComDroid();
		
		//checks assigning numbers less than 10 and more than 10
		assertEquals("01C: UNASSIGNED", com1.toString());
		assertEquals("02C: UNASSIGNED", com2.toString());
		assertEquals("10C: UNASSIGNED", com10.toString());
		
		TechDroid.startDroidNumberingAt01();
	}
	
	/**
	 * Test the assigning of devices to different droids
	 * @throws BadDeviceInformationException if device is missing required information
	 * @throws DroidBusyException if Droid has already been assigned
	 * @throws DroidDeviceMismatchException if droid and device do not match 
	 */
	@Test (expected = DroidBusyException.class)
	public void testAssign() throws BadDeviceInformationException, DroidBusyException, DroidDeviceMismatchException{			
		Device dev1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev1.toString());
		
		assertEquals("01C: UNASSIGNED", com1.toString());
		
		com1.assign(dev1);
		
		assertEquals("01C: NC123456 Doe, Jane", com1.toString());
		
		Device dev2 = new ComDevice("NC123hhh", "Doe, Jane", 2);
		
		com1.assign(dev2);
	}
	
	/**
	 * Test the removal of a device from a droid.  returns droid name to unassigned 
	 * @throws BadDeviceInformationException if device is missing required information
	 * @throws DroidBusyException if Droid has already been assigned
	 * @throws DroidDeviceMismatchException if droid and device do not match 
	 */
	@Test
	public void testRelease() throws BadDeviceInformationException, DroidBusyException, DroidDeviceMismatchException{
		Device dev1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev1.toString());
		
		assertEquals("01C: UNASSIGNED", com1.toString());
		
		com1.assign(dev1);
		assertEquals("01C: NC123456 Doe, Jane", com1.toString());
		
		Device dev2 = com1.release();
		
		assertEquals(dev1, dev2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev2.toString());
		assertEquals("01C: UNASSIGNED", com1.toString());
	}
	
	/**
	 * Testing for whether or not a droid has been assigned to a device
	 * 
	 * @throws BadDeviceInformationException - if information is missing to create device
	 * @throws DroidBusyException - if droid is already assigned to another device 
	 * @throws DroidDeviceMismatchException - if device is not an acceptable type that the droid can service
	 */
	@Test 
	public void testIsAssigned() throws BadDeviceInformationException, DroidBusyException, DroidDeviceMismatchException{
		Device dev1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev1.toString());
		
		assertFalse(com1.isAssigned());
		
		com1.assign(dev1);
		assertTrue(com1.isAssigned());
		
		com1.release();
		assertFalse(com1.isAssigned());
	}
	
	/**
	 * Tests the Droid ID name
	 */
	@Test
	public void testGetDroidID(){
		assertEquals("01C", com1.getDroidID());
	}
	
	/**
	 * Restarts numbign of Tech Droids.  Used for testing 
	 */
	public void testStartDroidNumberingAt01(){
		assertEquals("01C: UNASSIGNED", com1.toString());
		TechDroid.startDroidNumberingAt01();
		TechDroid com2 = new ComDroid();
		assertEquals("01C: UNASSIGNED", com2.toString());
	}
	
	/**
	 * Tests error when a device does not match a droid
	 * @throws BadDeviceInformationException if device is missing required information
	 * @throws DroidBusyException if Droid has already been assigned
	 * @throws DroidDeviceMismatchException if droid and device do not match 
	 */
	@Test (expected = DroidDeviceMismatchException.class)
	public void testMatching() throws BadDeviceInformationException, DroidBusyException, 
	DroidDeviceMismatchException{
		assertEquals("01C: UNASSIGNED", com1.toString());
		
		Device dev2 = new VRDevice("NC123456", "Doe, Jane", 2);
		com1.assign(dev2);
	}

}
