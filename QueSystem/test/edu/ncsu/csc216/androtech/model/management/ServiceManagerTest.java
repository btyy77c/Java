package edu.ncsu.csc216.androtech.model.management;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.*;
import edu.ncsu.csc216.androtech.model.repair_center.*;

/**
 * Testing for Service Manager class 
 * @author Emily Ring 
 *
 */
public class ServiceManagerTest {
	private ServiceManager device;
	private ServiceManager device2;

	/**
	 * Setup information to test the Service Manager 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		TechDroid.startDroidNumberingAt01();
		device = new ServiceManager();
		
		TechDroid.startDroidNumberingAt01();
		Scanner l = new Scanner("V 0 2041122 Buster Planetary\n"
				+ "C 2 20415678 Hailey Comet");
		device2 = new ServiceManager(l);
	}
	
	/**
	 * Test adding droids to the Repair Center list 
	 */
	@Test
	public void testAddNewDroid(){
		assertEquals("05V: UNASSIGNED\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: UNASSIGNED\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED", device.printDroids());
		
		device.addNewDroid();
		
		assertEquals("05V: UNASSIGNED\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: UNASSIGNED\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED\n"
				+ "06C: UNASSIGNED", device.printDroids());
		
		Scanner v = null;
		ServiceManager s3 = new ServiceManager(v);
		assertEquals (s3.printWaitList(""), device.printWaitList(""));
	}
	/**
	 * Test assigning devices in the devicelist to droids in the repair center 
	 * @throws BadDeviceInformationException if device is missing required information
	 */
	@Test
	public void testAssignDroids() throws BadDeviceInformationException{
		assertEquals("05V: UNASSIGNED\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: UNASSIGNED\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED", device2.printDroids());
		
		assertEquals("C Gold      20415678 Hailey Comet\n"
				+ "V None      2041122 Buster Planetary", device2.printWaitList(""));
		
		device2.assignDroids();
		
		assertEquals("05V: 2041122 Buster Planetary\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: 20415678 Hailey Comet\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED", device2.printDroids());
		
		assertEquals("", device2.printWaitList(""));
	}
	
	/**
	 * Testing removing devices and droids from the Service manager
	 * @throws BadDeviceInformationException - if needed information is missing to create a device
	 */
	@Test
	public void testReleaseAndRemove() throws BadDeviceInformationException{
		assertEquals("05V: UNASSIGNED\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: UNASSIGNED\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED", device2.printDroids());
		
		assertEquals("C Gold      20415678 Hailey Comet\n"
				+ "V None      2041122 Buster Planetary", device2.printWaitList(""));
		
		device2.assignDroids();
		
		assertEquals("05V: 2041122 Buster Planetary\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: 20415678 Hailey Comet\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED", device2.printDroids());
		
		assertEquals("", device2.printWaitList(""));
		
		device2.releaseFromService(0);
		
		
		assertEquals("05V: UNASSIGNED\n"
				+ "03V: UNASSIGNED\n"
				+ "02E: 20415678 Hailey Comet\n"
				+ "01C: UNASSIGNED\n"
				+ "04C: UNASSIGNED", device2.printDroids());
		
		Device nu = new ComDevice("897087", "Buster Planetary", 8);
		
		device2.putOnWaitingList(nu);
		
		assertEquals("C Platinum  897087 Buster Planetary", device2.printWaitList(""));
		
		device2.putOnWaitingList("V", "97697697", "Todd", 9);
		
		device2.putOnWaitingList("C", "97665465463697", "Bill", 0);
		
		assertEquals("C Platinum  897087 Buster Planetary\n"
				+ "V Platinum  97697697 Todd\n"
				+ "C None      97665465463697 Bill", device2.printWaitList(""));
	}

}
