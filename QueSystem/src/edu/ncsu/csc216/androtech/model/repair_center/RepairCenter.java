/**
 * 
 */
package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Class that maintains and manages all TechDroids in the program 
 * 
 * @author Emily Ring
 *
 */
public class RepairCenter {
	/** maximum number of droids the repair center can create */
	public static final int MAX_DROIDS = 30;
	
	/**Defaults to starting with 5 droids when the repair center opens */
	public static final int DEFAULT_SIZE = 5;
	
	/**Number of droids that have been created */
	private int size;
	
	/** List of Droids in the repair center */
	private TechDroid[] list;
	
	/** Number of droids available for VR devices */
	private int vrTotal;
	
	/**
	 * Constructs Repair Center 
	 */
	public RepairCenter(){
		TechDroid.startDroidNumberingAt01();
		list = new TechDroid[MAX_DROIDS];
		size = 0;
		vrTotal = 0;
		
		initDroids(DEFAULT_SIZE);	
	}
	
	/**
	 * Initializes the first few droids for the program
	 * 
	 * @param a - number of initial droids
	 */
	private void initDroids(int a){
		for (int i = 0; i < a; i++){
			this.addTechDroid();
		}
	}
	
	/**
	 * Adds a new Droid to list
	 */
	public void addTechDroid(){
		if (size < MAX_DROIDS){
			//add first 5
			if (size == 0){
				list[3] = new ComDroid(); //1
				size++;
			} else if (size == 1){
				list[2] = new ExpertDroid(); //2
				size++;
				vrTotal++;
			} else if (size == 2){
				list[1] = new VRDroid(); // 3
				size++;
				vrTotal++;
			} else if (size == 3){
				list[4] = new ComDroid(); //4 
				size++;
			} else if (size == 4){
				list[0] = new VRDroid(); //5
				size++;
				vrTotal++;
			} else {
				//if 1/3 can service V devices, create Comdroid
				if (((double) vrTotal / size) > 0.333){
					list[size] = new ComDroid();
					size++;
				} else {
					int y = (int) Math.floor(Math.random() * 4);
					if (y < 2){
						TechDroid a = new ExpertDroid();
						sortE(a);
						size++;
						vrTotal++;
					} else {
						TechDroid a = new VRDroid();
						sortV(a);
						size++;
						vrTotal++;
					}
				}
			}
		} else {
			size = MAX_DROIDS;
		}
	}
	
	private void sortV(TechDroid nu) {
		for (int i = size; i > 0; i--){
			list[i] = list[i - 1];
		}
		
		list[0] = nu;
	}
	
	private void sortE(TechDroid nu) {
		int newSpot = size - 1;
		
		while(list[newSpot].getDroidID().toString().charAt(2) == 'C' || 
				list[newSpot].getDroidID().toString().charAt(2) == 'E'){
			list[newSpot + 1] = list[newSpot];
			newSpot--;
		}
		
		list[newSpot + 1] = nu;
	}

	/**
	 * number of droids that are servicing devices
	 * @return number of droids that are servicing devices
	 */
	public int numberOfAvailableDroids(){
		int available = 0;
		for (int i = 0; i < size; i++){
			if(!list[i].isAssigned()){
				available++;
			}
		}
		return available;
	}
	
	/**
	 * Locates the droid at the request index on the list
	 * @param index - int from requested index
	 * @return the droid at index of droid
	 */
	public TechDroid getDroidAt(int index){
		if (index < size){
			return list[index];
		} else{
			return null;
		}
	}
	
	/**
	 * number of droids in the list
	 * @return number of droids in the list
	 */
	public int totalNumberOfDroids(){
		return size;
	}
	
	/**
	 * Removing device information from a droid
	 * @param index - location of the droid that will be updated
	 * @return Device associated with the Droid 
	 */
	public Device release(int index){
		return list[index].release(); 
	}
	/**
	 * creates a String list of all droids
	 * @return String list of all droids
	 */
	public String printDroids(){
		String l = "";
		
		for (int i = 0; i < size; i++){
			l = l + list[i].toString();
				if (i < (size - 1)){
					l = l + "\n";
				}
		}
		
		return l.trim();
	}
}
