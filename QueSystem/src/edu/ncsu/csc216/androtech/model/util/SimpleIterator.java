/**
 * 
 */
package edu.ncsu.csc216.androtech.model.util;

/**
 * Interface that creates a list of Devices for the DeviceList class
 * @author Emily Ring
 *
 * @param <Device> - List of Devices included in the DeviceList 
 */
public interface SimpleIterator<Device> {
	
	/**
	 * Whether or not there is another deivce in the list
	 * @return Whether or not there is another deivce in the list
	 */
	public boolean hasNext();
	
	/**
	 * Moves the list to the next device
	 * @return the next device on the list
	 */
	public Device next();
}
