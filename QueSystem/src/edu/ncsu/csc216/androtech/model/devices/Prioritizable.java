/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

/**
 * Interface describing behaviors for the Device classes
 * 
 * @author Emily Ring 
 */
public interface Prioritizable {
	
	/**
	 * Gives tier level 
	 * @return tier level 
	 */
	public int getTier();
	
	/**
	 * whether or not 2 items are equal 
	 * @param a device to compare with current device 
	 * @return whether or not 2 items are equal 
	 */
	public int compareToTier(Prioritizable a);

}
