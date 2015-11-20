/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

/**
 * Create Com Devices which can service ComDroids
 * 
 * 
 * @author Emily Ring 
 *
 */
public class ComDevice extends Device {
	
	/**
	 * COnstructor for the Communication Devices
	 * @param num - Serial number of device
	 * @param nam - Name of device owner
	 * @param index - tier level for device service 
	 * @throws BadDeviceInformationException if num or num is empty
	 */
	public ComDevice(String num, String nam, int index) 
			throws BadDeviceInformationException{
		super(num, nam, index);
		validateString(num, "Serial Number");
		validateString(nam, "Name");
	}
	
	/**
	 * String list of the Devices type, tier, serial number, and name
	 * @return name and device type
	 */
	public String toString(){
		return "C " + super.toString();
	}
	
	private boolean isAllWhiteSpaces(String a){
		boolean check = false;
		int lenght = a.length();
		int t = 0;
		
		for (int i = 0; i < lenght; i++){
			if (a.charAt(i) == ' '){
				t++;
			}
		}
		
		if (t == lenght){
			check = true;
		}
		
		return check;
	}
	
	private void validateString(String d, String field) throws BadDeviceInformationException {
		if (d.isEmpty() || isAllWhiteSpaces(d)){
			throw new BadDeviceInformationException(
					"Error: " + field + " is empty");
		}
	}

}
