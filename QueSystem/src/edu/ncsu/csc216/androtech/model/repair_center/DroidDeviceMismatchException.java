/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * Creates new exception error when device and droid do not match 
 * 
 * @author Emily Ring 
 *
 */
public class DroidDeviceMismatchException extends Exception {
	
	/**
	 * I don't know what this is but Eclipse insists I need it
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates special method when error happens
	 * @param message special message created by outside classes 
	 */
	public DroidDeviceMismatchException(String message){
		super(message);
	}
	
	/**
	 * produces standard message when error happens 
	 */
	public DroidDeviceMismatchException(){
		super ("Error: Droid and Device do not match");
	}

}
