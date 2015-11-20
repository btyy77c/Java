package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.*;

/**
 * Tests methods included in the VRDroid class
 * @author Emily Ring
 */
public class VRDroidTest { 
	private VRDroid vr1;

	/**
	 * set up for testing 
	 * @throws Exception error
	 */
	@Before
	public void setUp() throws Exception {
		TechDroid.startDroidNumberingAt01();
		vr1 = new VRDroid();
	}
	
	/**
	 * Testing the assignments of TechDroid devices 
	 * @throws BadDeviceInformationException - exception for missing information 
	 * @throws DroidBusyException - exception when a droid is already assigned to another device
	 * @throws DroidDeviceMismatchException - error when a droid and device do not match 
	 */
	@Test (expected = DroidDeviceMismatchException.class)
	public void testAssign() throws BadDeviceInformationException, DroidBusyException, 
	DroidDeviceMismatchException{	
		
		assertEquals("01V: UNASSIGNED", vr1.toString());
		
		
		Device dev1 = new VRDevice("79-27DC", "Carter, June W", 3);
		

		
		assertEquals("V Platinum  79-27DC Carter, June W", dev1.toString());
		
		
		
		vr1.assign(dev1); 
		assertEquals("01V: 79-27DC Carter, June W", vr1.toString());
		
		vr1.release();
		assertEquals("01V: UNASSIGNED", vr1.toString());
		
		Device dev2 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", dev2.toString());
		
		vr1.assign(dev2);
	}

}
