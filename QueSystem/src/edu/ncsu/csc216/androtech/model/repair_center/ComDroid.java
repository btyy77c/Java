/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Creates a new Com droid for Repair Center
 * 
 * @author Emily Ring
 *
 */
public class ComDroid extends TechDroid {
	
	/**
	 * Constructor for ComDroids
	 */
	public ComDroid(){
		super("C");
	}
	
	/**
	 * Assigns a new device to the Com Droid
	 * @param s1 - the device to be added to the ComDroid
	 * @throws DroidBusyException if the droid is already assigned to another device 
	 * @throws DroidDeviceMismatchException 
	 */
	public void assign(Device s1) throws DroidBusyException, DroidDeviceMismatchException{
		
		if (s1.toString().charAt(0) != 'C'){
			throw new DroidDeviceMismatchException("Error: Not a Com Device");
		}
		
		super.assign(s1);
	}

}
