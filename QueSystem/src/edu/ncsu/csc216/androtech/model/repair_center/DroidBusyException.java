/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * Creates an exception message when a a device is assigned to a device that is already
 * services a different device
 * 
 * @author Emily Ring 
 *
 */
public class DroidBusyException extends Exception {
	
	/**
	 * I don't know what this is but Eclipse wants me to include it
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates general message for exception
	 */
	public DroidBusyException(){
		super ("Error: Droid is busy and cannot service Device");
	}
	
	/**
	 * Creates specialized message for exceptions 
	 * @param message - special message
	 */
	public DroidBusyException(String message){
		super(message);
	}

}
