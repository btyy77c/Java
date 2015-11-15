package model.veterinarians;

import model.Animal;
import model.BadInfoExecption;

/**
 * Veterinarian Object
 * @author EmilyRing
 */
public abstract class Vet {
	/**Name of Veterinarian */
	private String name;
	private String type;
	
	
	private Animal animal;
	private int ID;
	
	/**Class constructor 
	 * @throws BadInfoExecption */
	public Vet(String nam, int I) throws BadInfoExecption{
		name = checkEmpty(nam, "Dr Name");
		ID = I;
		type = "none";
	}
	
	/**
	 * Checks if a string is empty
	 * @param check - String that will be check for 
	 */
	private String checkEmpty(String check, String field) throws BadInfoExecption {
		if ((check == null) || "".equals(check.trim())){
			throw new BadInfoExecption("Error: " + field + " is empty");
		}
		
		return check;
	}
	
	/**
	 * Adds Animal to the first available Vet in the list
	 * @param add - Animal to be added to a Vet Object
	 * @return - True is the animal was assigned to a Vet. False if there was no
	 * available Vet
	 */
	public boolean assignAnimal(Animal add){
		if(animal == null){
			animal = add;
			animal.assignVet(this);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @returns name of the Veterinarian
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Removes assigned animal from the Vet Object
	 * @return the animal that was removed from the Vet Object
	 */
	public Animal removeAnimal(){
		Animal previous = animal;
		animal = null;
		return previous;
	}
	
	/**
	 * @return The Animal that is assigned to the Vet
	 */
	public Animal getAnimal(){
		return animal;
	}
	
	/**
	 * @return Veterinarian's ID number
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * @return - Vet info and info of the Animal assigned to the Vet
	 */
	public String toString(){
		String info = "Dr. " + name;
		
		if(animal != null){
			info = info + "    -" + animal.toString();
		}
		
		return info;
	}
	
	/**
	 * @return Type of Veterinarian (Dog Vet, Cat Vet, or All Vet)
	 */
	public String getType(){
		return type;
	}
}
