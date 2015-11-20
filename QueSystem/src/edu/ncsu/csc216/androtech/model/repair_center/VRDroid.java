/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Creates a new VR droid for Repair Center
 * 
 * @author Emily Ring
 *
 */
public class VRDroid extends TechDroid {
	
	/**
	 * Constructor for VR Droids
	 */
	public VRDroid(){
		super("V"); 
	}
	
	/**
	 * Assigns a new device to the VR Droid
	 * @param s1 - the device to be added to the VRDroid
	 * @throws DroidBusyException if the droid is already assigned to another device 
	 * @throws DroidDeviceMismatchException 
	 */
	public void assign(Device s1) throws DroidBusyException, DroidDeviceMismatchException{
		
		if ( s1.toString().charAt(0) != 'V'){
			throw new DroidDeviceMismatchException();
		}
		
		super.assign(s1);
	}

}
