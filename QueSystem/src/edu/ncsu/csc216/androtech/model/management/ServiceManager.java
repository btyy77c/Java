/**
 * 
 */
package edu.ncsu.csc216.androtech.model.management;

import edu.ncsu.csc216.androtech.model.devices.*; 
import edu.ncsu.csc216.androtech.model.repair_center.*;
import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

import java.io.*;
import java.util.Scanner; 

/**
 * Main classes that interacts with user information. 
 * Also controls the overall functions of the program
 * @author Emily Ring 
 *
 */
public class ServiceManager implements Manager {
	private RepairCenter repairCenter;
	private DeviceList deviceList;
	
	/**
	 * Creates a service manager with no devices awaiting service
	 */
	public ServiceManager(){
		repairCenter = new RepairCenter();
		deviceList = new DeviceList();
	}
	
	/**
	 * Initializes the list of devices awaiting service with data from 
	 * the Scanner
	 * @param s - text file from user to add devices
	 */
	public ServiceManager(Scanner s)  {
		if (s == null){
			deviceList = new DeviceList();
			repairCenter = new RepairCenter();
		} else {
			repairCenter = new RepairCenter();
			deviceList = new DeviceList(s);
		}
	}
	
	/**
	 * Assigns devices on the waiting list droids that do not have devices 
	 * assigned to them
	 */
	public void assignDroids() {
		SimpleIterator<Device> check = deviceList.iterator();
		
		
		while(check.hasNext()){
			Device a = check.next();
			int droid = 0;
			
			while (droid < repairCenter.totalNumberOfDroids() && a != null){
				if(!repairCenter.getDroidAt(droid).isAssigned()){
					if (a.toString().charAt(0) == 
							repairCenter.getDroidAt(droid).getDroidID().charAt(2)
							|| repairCenter.getDroidAt(droid).getDroidID().charAt(2) == 'E'){
						try {
							repairCenter.getDroidAt(droid).assign(a);
							deviceList.remove(a.getName(), 0);
							droid = repairCenter.totalNumberOfDroids() + 2;
						} catch (DroidBusyException
								| DroidDeviceMismatchException e) {
							e.printStackTrace();
						}
					}
				}
				
				
				droid++;
			}
			
		}
	}

	/**
	 * Removes the vehicle that appears in the filtered list in the given position
	 * @param filter - String name which will be used to filter the list
	 * @param position - int location of the device that will be removed
	 * @return Prioritizable Device that was removed
	 */
	public Prioritizable remove(String filter, int position){
		return deviceList.remove(filter, position);
	}
	
	/**
	 * Release a device from a droid.
	 * @param a - index where device will be released
	 * @return - Device that will be released
	 */
	public Prioritizable releaseFromService(int a){
		return repairCenter.release(a);
	}
	
	/**
	 * Add a new TechDroid to the repair center
	 */
	public void addNewDroid(){
		repairCenter.addTechDroid();
	}
	
	/**
	 * String representation of all devices that meet the filter. Each 
	 * substring corresponding to a vehicle is terminated by a newline
	 * @param filter - String name which will be used to filter the list
	 * @return - A String list of all Devies that meet the filter requirements
	 */
	public String printWaitList(String filter){
		return deviceList.filteredList(filter);
	}
	
	/**
	 * String representation of all TechDroids
	 * @return String list of all droid
	 */
	public String printDroids(){
		return repairCenter.printDroids();
	}

	/**
	 * Tests creation of new device and adding it to the DeviceList 
	 * 
	 * @param k - Device type
	 * @param b - device serial number
	 * @param c - device owner name
	 * @param x - tier level for device 
	 */
	public void putOnWaitingList(String k, String b, String c, int x) {
		if (k.startsWith("C") || k.startsWith("c")){
			try {
				Device a = new ComDevice(b, c, x);
				deviceList.add(a);
			} catch (BadDeviceInformationException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Device a = new VRDevice(b, c, x);
				deviceList.add(a);
			} catch (BadDeviceInformationException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds a device to the DeviceList
	 * @param v new device to be added 
	 */
	public void putOnWaitingList(Prioritizable v) {
		deviceList.add((Device) v);
		
	}
}


