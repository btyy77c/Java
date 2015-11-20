/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

/**
 * Class that represents VR Devices.  Cannot be serviced by ComDroids
 * 
 * @author Emily Ring 
 *
 */
public class VRDevice extends Device {
	
	/**
	 * Creates a new VR Device 
	 * @param num serial number
	 * @param nam - owner name 
	 * @param index - tier level 
	 * @throws BadDeviceInformationException is device is missing information
	 */
	public VRDevice(String num, String nam, int index) 
			throws BadDeviceInformationException{
		super(num, nam, index);
		validateString(num);
		validateString(nam);
	}
	
	/**
	 * String information of device, including type
	 * @return String information of device, including type
	 */
	public String toString(){
		return "V " + super.toString();
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
	
	private void validateString(String d) throws BadDeviceInformationException {
		if (d.isEmpty() || isAllWhiteSpaces(d)){
			throw new BadDeviceInformationException();
		}
	}

}
