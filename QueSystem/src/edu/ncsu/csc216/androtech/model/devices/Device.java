/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

/**
 * Abstract Class for all devices in service
 * class extends to ComDevice and VRDevice
 * 
 * @author Emily Ring
 *
 */
public abstract class Device implements Prioritizable {
	/** Device serial number */
	private String serialNum;
	
	/** Name of Device owner */
	private String name;
	
	/** int realted to customer tier level */ 
	private int tierindex;
	
	/** array of optional tier levels */ 
	private static final String [] CUSTOMER_TIER  = {"None", "Silver", 
	                                                 "Gold", "Platinum"};
	
	/** A String representation of the customer tier type */
	private String tierType;
	
	/** 
	 * Constructor that creates a device from of a serial number, owner name, and tier status
	 * @param num - serial number
	 * @param nam - owner name 
	 * @param index - tier index
	 */
	public Device (String num, String nam, int index) { 
		
		serialNum = num.trim();
		name = nam.trim();
		tierindex = index;

		if (tierindex < 1){
			tierType = CUSTOMER_TIER[0];
			tierindex = 0;
		} else if (tierindex == 1){
			tierType = CUSTOMER_TIER[1];
		} else if (tierindex == 2){
			tierType = CUSTOMER_TIER[2];
		} else{
			tierType = CUSTOMER_TIER[3];
			tierindex = 3;
		}
	}
	
	/**
	 * Whether or not a device matches the information given by the user. 
	 * @param filter String input give by user
	 * @return list of devices that meet filter requirements
	 */
	public boolean meetsFilter(String filter){
		boolean check = false;
		
		String nameLower = name.toLowerCase();
		filter = filter.toLowerCase().trim();
		
		if (nameLower.startsWith(filter)){
			check = true;
		} else if (filter.isEmpty()){
			check = true;
		} else if (filter.equals("")){
			check = true;
		} else if (filter.length() < 1){
			check = true;
		}
		
		return check;
	}
	
	/**
	 * creates a string of the Devices Type, tier, serial number, and name
	 * @return String of device information 
	 */
	public String toString(){
		return  (String.format("%-10s", tierType) + serialNum + " " + name).trim();
	}
	
	/**
	 * Gives name of device owner
	 * @return name
	 */
	public String getName(){
		return name.trim();
	}
	
	/**
	 * Give device serial number 
	 * @return device serial number 
	 */
	public String getSerialNum(){
		return serialNum.trim();	
	}
	
	/**
	 * Gives the device tier value 
	 * @return tier value 
	 */
	public int getTier(){
		return tierindex;
	}
	
	/**
	 * whether or not 2 devices have teh same teir
	 * @param a - The device to compare to the current device 
	 * @return whether or not 2 devices have the same tier 
	 */
	public int compareToTier(Prioritizable a){
		if (tierindex == a.getTier()){
			return 0;
		} else if (tierindex < a.getTier()){
			return -2;
		} else {
			return 2;
		}
	}
}
