package model;


/**
 * Throws an exception when information is missing from a newly
 * created object
 */
public class BadInfoExecption extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * General error message
	 */
	public BadInfoExecption(){
		super("Error: missing information");
	}
	
	/**
	 * Specific error message
	 * @param message - error message that will be sent to user
	 */
	public BadInfoExecption(String message){
		super(message);
	}

}
