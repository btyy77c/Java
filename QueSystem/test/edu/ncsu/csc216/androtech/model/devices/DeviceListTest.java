package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
/**
 * Testing for Devices and Device List
 * @author Emily Ring 
 *
 */
public class DeviceListTest {
	private DeviceList list;
	private DeviceList list1;

	/**
	 * Setupf for testing devices and the device list 
	 * @throws Exception error
	 */
	@Before
	public void setUp() throws Exception {
		list = new DeviceList();
		Scanner l = new Scanner("V 0 2041122 Buster Planetary\n"
				+ "C 2 20415678 Hailey Comet");
		
		list1 = new DeviceList(l);
	}

	/**
	 * Testing for  adding and sorting Devices
	 * @throws BadDeviceInformationException if information is missing to create Device
	 */
	@Test
	public void testAdd() throws BadDeviceInformationException {
		ComDevice com1 = new ComDevice("NC123456", "Doe, Jane", 2);
		assertEquals("C Gold      NC123456 Doe, Jane", com1.toString());
		list.add(com1);
		
		ComDevice com2 = new ComDevice("123", "Emily", 0);
		assertEquals("C None      123 Emily", com2.toString());
		list.add(com2);
		
		ComDevice com3 = new ComDevice("123", "Bob", 1);
		assertEquals("C Silver    123 Bob", com3.toString());
		list.add(com3);
		
		assertEquals("C Gold      NC123456 Doe, Jane\n" 
				+ "C Silver    123 Bob\n"
				+ "C None      123 Emily", list.filteredList(""));
	}
	
	/**
	 * Testing for removing and adding devices to a list 
	 * @throws BadDeviceInformationException if information is missing to create Device
	 */
	@Test
	public void testRemove() throws BadDeviceInformationException{
		ComDevice com1 = new ComDevice("NC123456", "Doe, Jane", 2);
		list.add(com1);

		ComDevice com2 = new ComDevice("123", "Emily", 3);
		list.add(com2);
		
		ComDevice com3 = new ComDevice("123", "Rob", 1);
		list.add(com3);
		
		VRDevice vr1 = new VRDevice("NC123456", "Doe, Jeff", 2);
		list.add(vr1);
		
		VRDevice vr2 = new VRDevice("456", "Todd", 0);
		list.add(vr2);
		
		VRDevice vr3 = new VRDevice("8098", "Hank", 1);
		list.add(vr3);
		
		assertEquals("C Platinum  123 Emily\n"
				+ "C Gold      NC123456 Doe, Jane\n"
				+ "V Gold      NC123456 Doe, Jeff\n"
				+ "C Silver    123 Rob\n"
				+ "V Silver    8098 Hank\n"
				+ "V None      456 Todd", list.filteredList(""));
		
		list.remove("", 0);
		
		assertEquals("C Gold      NC123456 Doe, Jane\n"
				+ "V Gold      NC123456 Doe, Jeff\n"
				+ "C Silver    123 Rob\n"
				+ "V Silver    8098 Hank\n"
				+ "V None      456 Todd", list.filteredList(""));
		
		list.remove("", 4);
		
		assertEquals("C Gold      NC123456 Doe, Jane\n"
				+ "V Gold      NC123456 Doe, Jeff\n"
				+ "C Silver    123 Rob\n"
				+ "V Silver    8098 Hank", list.filteredList(""));
		
		list.remove("", 2);
		
		assertEquals("C Gold      NC123456 Doe, Jane\n"
				+ "V Gold      NC123456 Doe, Jeff\n"
				+ "V Silver    8098 Hank", list.filteredList(""));
		
		list.remove("do", 0);
		
		assertEquals("V Gold      NC123456 Doe, Jeff\n"
				+ "V Silver    8098 Hank", list.filteredList(""));

	}
	
	/**
	 * tests the creation scanner of creating a list 
	 */
	@Test
	public void testConstructor(){
		assertEquals("", list.filteredList(""));
		
		assertEquals("C Gold      20415678 Hailey Comet\n"
				+ "V None      2041122 Buster Planetary", list1.filteredList(""));
	}

}
