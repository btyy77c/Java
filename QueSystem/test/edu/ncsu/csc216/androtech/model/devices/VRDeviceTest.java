package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing for VR Devices 
 * @author Emily RIng 
 *
 */
public class VRDeviceTest {
	private Device vr1;

	/**
	 * setup for testing devices 
	 * @throws Exception error 
	 */
	@Before
	public void setUp() throws Exception {
		vr1 = new VRDevice("79-27DC", "Carter, June W", 3);
		
		
	}
	
	/**
	 * Testing a device with a missing name 
	 * @throws BadDeviceInformationException if device is missing required informati
	 */
	@Test (expected = BadDeviceInformationException.class)
	public void testToString() throws BadDeviceInformationException{
		assertEquals("V Platinum  79-27DC Carter, June W", vr1.toString());
		
		Device vr3 = new VRDevice("79-27DC", "   ", 3);
		vr3.getName();
	}

}
