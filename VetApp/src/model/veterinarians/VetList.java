package model.veterinarians;

import java.util.ArrayList;
import java.util.ListIterator;

import model.Animal;

/**
 * Manages lists of Veterinarians on file
 * @author EmilyRing
 */
public class VetList {
	/* ArrayList which will handle all Vet Objects*/
	private ArrayList<Vet> vetList;
	/* Number of Vet Objects in list */
	private int number;
	
	/**
	 * Class constructor 
	 */
	public VetList(){
		vetList = new ArrayList<Vet>();
		number = 0;
	}
	
	/**
	 * @return a String ArrayList list of Vet information.  Used for GUI 
	 */
	public String[] getArrayList(){
		String[] viewList;
		
		if (number < 1){
			viewList = new String[]{"", "", ""};
		} else {
			viewList = new String[number];
			ArrayList<Vet> create = vetList;
			for (int i = 0; i < number; i++){
				viewList[i] = create.get(i).toString();
			}
		}
		return viewList;
	}
	
	/**
	 * Adds a new Vet object to the list
	 * New Vets are added at the end of the list
	 * @param nu - new Vet object to be added
	 */
	public void add(Vet nu){
		if (!doesVetExists(nu)){
			vetList.add(nu);
			number++;
		} 
	}
	
	/**
	 * Removes animal assigned to a specific Vet
	 * @param position - Position of the Vet Object which will be updated
	 * @return The animal that was removed
	 */
	public Animal completeVisit(int position){
		return vetList.get(position).removeAnimal();
	}
	
	/**
	 * 
	 * @param position - Position of selected vet in the list
	 * @return the Vet Object in the selected position
	 */
	public Vet getSelectedVet(int position){
		return vetList.get(position);
	}
	
	/**
	 * Removes a Vet from the selected position
	 * @param rem - position of the Vet that will be removed
	 * @return the Vet that was removed
	 */
	public Vet remove(int rem){
		Vet vet = vetList.get(rem);
		if (vetList != null){
			vetList.remove(rem);
			number--;
		}
		
		return vet;
	}
	
	/**
	 * Adds animal to the first available Vet Object that is able to see the Animal
	 * @param add - Animal the will be added to the Vet Object
	 * @return True if the animal was added to a vet. False if there was not an 
	 * available Vet
	 */
	public boolean addAnimal(Animal add){
		boolean added = false;
		ListIterator<Vet> run = vetList.listIterator();
		
		while(run.hasNext() && add != null){
			if (run.next().assignAnimal(add)){
				add = null;
				added = true;
			} 
		}
		
		return added;
	}
	
	/**
	 * Check Vet ID to see if there is a matching ID already in the list
	 * @param nuVet - New Vet Object which will be compared to other Vets in the list
	 * @return true if the Vet ID is already in the list
	 */
	public boolean doesVetExists(Vet nuVet){
		boolean check = false;
		
		ArrayList<Vet> checkList = vetList;
		for (int i = 0; i < number; i++){
			if (checkList.get(i).getID() == nuVet.getID()){
				check = true;
			}
		}	
		return check;
	}
}
