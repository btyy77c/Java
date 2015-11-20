/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

/**
 * Throws exeptions when a device is missing Serial number or 
 * Name
 * 
 * @author Emily Ring
 *
 */
public class BadDeviceInformationException extends Exception {
	
	/**
	 * I don't know what this is but Eclipse insists it should
	 * be in the program
	 */
	private static final long serialVersionUID = 3133449161525314546L;

	/**
	 * Construction for this class, which includes a standard message
	 */
	public BadDeviceInformationException(){
		super ("Error: Device is missing required information and cannot be created");
	}
	
	/**
	 * Constructor for this class which includes a personal message
	 * @param message - message created by outside class error
	 */
	public BadDeviceInformationException(String message){
		super(message);
	}

}
