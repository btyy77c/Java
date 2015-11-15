package manager;

import model.*;
import model.veterinarians.Vet;
import model.veterinarians.VetList;

/**
 * Manages the interaction between animals, Veterinarians and the GUI
 * @author EmilyRing
 */
public class Manager {
	/* List of animals in the system */
	private AnimalList animalList; 
	/* List of Vets in the system */
	private VetList vetList;
	/* List of animals that finished their visit to the Office */
	private AnimalList completedAnimalList;

	/**
	 * Class constructor
	 */
	public Manager(){
		animalList = new AnimalList();
		vetList = new VetList();
		completedAnimalList = new AnimalList();
	}
	
	/**
	 * Adds new animal to list of animals in the system
	 * @param nu - New anminal that will be added
	 */
	public void addAnimal(Animal nu){
		if (nu != null){
			animalList.add(nu);
		}
	}
	
	/**
	 * Removes animal from a specific position in the animal list
	 * @param position - position of the animal that will be removed
	 */
	public void removeAnimal(int position){
		animalList.remove(position);
	}
	
	/**
	 * @return String array of animals in the system
	 */
	public String[] getAnimalList(){
		return animalList.toArrayString();
	}
	
	/**
	 * Adds a new Vet to the list of Veterinarians in the system
	 * @param nu - new Veterinarian to be added
	 */
	public void addVet(Vet nu){
		vetList.add(nu);
	}
	
	/**
	 * Removes Veterinarian from the list.  If Vet has an animal then the
	 * Animal is also added to the listed of completed animal visits
	 * @param rem - position of the Veterinarian that will be removed
	 */
	public void removeVet(int rem){
		Vet vet = vetList.remove(rem);
		Animal animal = vet.getAnimal();
		if (animal != null){
			completedAnimalList.add(animal);
		}
	}
	
	/**
	 * Takes the animal at the top of the list and looks for a Veterinarian to assign
	 * the animal to.  If a Vet is available, then the animal is removed from the 
	 * waiting list
	 */
	public void assignAnimal(int position){
		Animal add = animalList.getSelected(position);
		
		if (add != null && vetList !=null){
			boolean assignVet = vetList.addAnimal(add);
			if (assignVet == true){
				animalList.remove(position);
			} else {
				assignAnimal(position + 1);
			}
		}
		
	}
	
	/**
	 * @return a String array of all the Veterinarian in the system
	 */
	public String[] getVetList(){
		return vetList.getArrayList();
	}
	
	/**
	 * @param position - position of the selected animal
	 * @return - return Animal from the selected position
	 */
	public Animal getSelectedAnimal(int position){
		return animalList.getSelected(position);
	}
	
	/**
	 * @param position - position of the selected Vet in the list
	 * @return - Vet Object at the select position 
	 */
	public Vet getSelectedVet(int position){
		return vetList.getSelectedVet(position);
	}
	
	/**
	 * Takes a Vet Object and removes an animal that is working with
	 * @param poisition of the Vet that needs to complete their visit
	 */
	public void completeVisit(int poisition){
		Animal add = vetList.completeVisit(poisition);
		completedAnimalList.add(add);
	}
	
	/**
	 * @param nu - Animal to be added to the list
	 * @return true is Animal is already in the list
	 */
	public boolean IsAnimalInList(Animal nu){
		return animalList.doesAnimalExists(nu);
	}
	
	/**
	 * @param nu - New vet to be added to a list
	 * @return True if the Vet ID macthes a Vet already in the list
	 */
	public boolean isVetInList(Vet nu){
		return vetList.doesVetExists(nu);
	}

}
