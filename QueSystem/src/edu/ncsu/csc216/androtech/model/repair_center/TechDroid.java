/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.*;

/**
 * An abstract class which creates droids, available to repaire
 * different devices in the program
 * 
 * @author Emily Ring
 *
 */
public abstract class TechDroid {
	/** whether or not a device is assigned to the droid*/
	private boolean assigned;
	
	/**String representation of the droid name */
	private String droidID;
	
	/**Number used to assign droidID. Increases after each new droid is created */
	private static int nextNumber;
	
	/** Device that the droid will repair */
	private Device dev;
	
	/**Name of the device the droid to repairing. Used for toString */
	private String assignedName;
	
	/** Constructs new droid 
	 * @param suffix  - droid type (E, C or V) depending on droid class. 
	 */
	public TechDroid(String suffix){

		assigned = false;
		assignedName = "UNASSIGNED";
		
		if (nextNumber == 0){
			nextNumber = 1;
		}
		
		if (nextNumber < 10){
			droidID = "0" + nextNumber + suffix;
		} else {
			droidID = nextNumber + suffix;
		}
		
		nextNumber++; 
		
	}
	
	/**Restarts ID numbering.  Used for testing */
	public static void startDroidNumberingAt01(){
		nextNumber = 1; 
	}
	
	/**
	 * the Droid Name
	 * @return the Droid Name
	 */
	public String getDroidID(){
		return droidID.trim();
	}
	
	/** 
	 * whether or not a device is assigned to the droid 
	 * @return whether or not a device is assigned to the droid 
	 */
	public boolean isAssigned(){
		return assigned;
	} 
	
	/**
	 * removes device from Droid 
	 * @return the device that was removed
	 */
	public Device release(){
		assigned = false;
		assignedName = "UNASSIGNED";
		Device n = dev;
		dev = null;
		return n;
	}
	
	/**
	 * assigned a device to a droid
	 * @param s device to be assigned 
	 * @throws DroidBusyException error is droid is already assigned
	 * @throws DroidDeviceMismatchException error if droid and device do not match 
	 */
	public void assign(Device s) throws DroidBusyException, DroidDeviceMismatchException {
		if (assigned){
			throw new DroidBusyException();
		}
		
		dev = s;
		assigned = true;
		assignedName = dev.getSerialNum() + " " + dev.getName();
	}
	
	/**
	 * Creates a string of all information for a TechDroid
	 * @return string of all information for a TechDroid
	 */
	public String toString(){
		return droidID + ": " + assignedName;
	}
}
