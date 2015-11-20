/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

import java.util.Scanner; 

import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * implements a list of Devices. Devices are ordered first by 
 * priority and then by time of entry into the list.
 * 
 * @author Emily Ring 
 */
public class DeviceList {
	private Node list;
	
	/** Creates an empty list of devices */
	public DeviceList(){
		list = null;
	}
	
	/** 
	 * Creates a list of devices from a Scanner. The Scanner would have been 
	 * initialized by the client as a Scanner on an input text file 
	 * 
	 * @param s - Scanner text file from user
	 */
	public DeviceList(Scanner s) {
		list = null;
		
		while (s.hasNextLine()){
			Scanner line = new Scanner (s.nextLine());
				String type = "";
				int tier = 0;
				String serial = "";
				String name = "";
				
				if (line.hasNext()){
					type = line.next();
				}
				
				if (line.hasNextInt()){
					tier = line.nextInt();
				}
				
				if (line.hasNext()){
					serial = line.next();
				}
				
				if (line.hasNext()){
					name = line.next();
				}
				
				if (line.hasNext()){
					name = name + " " + line.next();
					name = name.trim();
				}
				
				
				if (type.equals("C")){
					Device c;
					try {
						c = new ComDevice(serial, name, tier);
						this.add(c);
					} catch (BadDeviceInformationException e) {
						e.printStackTrace();
					}
					
				} else if (type.equals("V")){
					Device v;
					try {
						v = new VRDevice(serial, name, tier);
						this.add(v);
					} catch (BadDeviceInformationException e) {
						e.printStackTrace();
					}
				}
				
				line.close();
		}
	}

	/** 
	 * Returns a SimpleIterator initialized to point to the 
	 * first element in the list
	 * @return - list of all devices 
	 */
	public SimpleIterator<Device> iterator (){
		return new Cursor();
	}
	
	/**
	 * Adds the given device to the list of those awaiting service
	 * @param c - Device which will be added to the list
	 */
	public void add(Device c) {
		double tier = c.getTier() - .05;
		
		if (list == null) {
			list = new Node(c, list);
		} else if (tier > list.element.getTier()){
			list = new Node(c, list);
		} else {
			list.add(c);
		}
	}
	
	/**
	 * Filtered list of devices
	 * @param filter -String used to create filter
	 * @return filtered list of devices 
	 */
	public String filteredList(String filter){
		String fin = "";
		Node p = list;
		
		while (p != null){
			if (p.element.meetsFilter(filter)){
				fin = fin + p.element.toString() + "\n";
			}
			
			p = p.next;
		}
		
		return fin.trim();
	}
	
	/** 
	 * Removes the vehicle that appears in the filtered list 
	 * in the given position
	 * @param filter - Srting name which will be used to filter the list
	 * @param position - place were Device needs to be removed
	 * @return - Device that will be removed
	 */
	public Device remove(String filter, int position){
		Device rem = null;
		String item = this.removeCheck(filter, position);
		
		if (list != null){
			if (list.element.toString().trim().equals(item)){
				rem = list.element;
				list = list.next;
			} else {
				rem = list.remove(item);
			}
		}
		
		return rem;
	}
	
	/**
	 * removes a device froma positio on a filtered list
	 * @param filter String information used to create the filter
	 * @param position of device that needs to be removed 
	 * @return removed device
	 */
	public String removeCheck(String filter, int position){
		int pt = 0;
		String l = "-1";
		Scanner fil = new Scanner(this.filteredList(filter));
		
		while(fil.hasNextLine()){
			String a = fil.nextLine();
			if (pt == position){
				l = a;
			}
			pt++;
		}
		
		fil.close();
		return l.trim();
	}
	
	/** Recursive list of Devices */
	public class Node {
		private Device element;
		private Node next;
		
		/**
		 * Device information 
		 * @param a current device
		 * @param b next node in the list 
		 */
		public Node(Device a, Node b) {
			element = a;
			next = b;
		}
		
		private Device remove(String item) {
			Device rem = null;
			
			if (next.element != null) {
				if (next.element.toString().trim().equals(item)){
					rem = next.element;
					next = next.next;
				} else {
					next.remove(item);
				}
			} 
			
			return rem;
		}

		private void add(Device c) {
			double tier = c.getTier() - .5;
			
			if (next == null){
				if (tier > element.getTier()){
					Device previous = element;
					element = c;
					next = new Node (previous, null);
				} else {
					next = new Node (c, null);
				}
				
			} else if (next.element.getTier() < tier) {
				next = new Node(c, next);
			} else {
				next.add(c);
			}
		}
	}
	
	/** Search through Device list*/
	public class Cursor implements SimpleIterator<Device> {
		private Node cursor;
		
		private Cursor(){
			cursor = list;
		}
		
		/**
		 * whether or not the list has another node
		 * @return whether or not the list has another node
		 */
		public boolean hasNext(){
			return cursor != null;
		}
		
		/**
		 * moves the cursor to the next device 
		 * @return the next device 
		 */
		public Device next(){
			Device nu = null;
			
			if (cursor != null){
				nu = cursor.element;
				cursor = cursor.next;
			}
			
			return nu;
		}
	}
}
