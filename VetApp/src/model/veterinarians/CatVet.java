package model.veterinarians;

import model.Animal;
import model.BadInfoExecption;

/**
 * Object for Veterinarians that can only see Cats in the Vet Office
 * @author EmilyRing
 */
public class CatVet extends Vet{
	private String type;

	/**
	 * Class constructor
	 * @param name - Name of Veterinarian 
	 * @param ID - Veterinarian  ID number
	 * @throws BadInfoExecption - If name or ID are incorrect
	 */
	public CatVet(String name, int ID) throws BadInfoExecption{
		super(name, ID);
		type = "Cat";
	}
	
	/**
	 * Adds Cat Objects to vet.  Will not assign other Animal Objects
	 * @return true if animal was added to Vet Object
	 */
	public boolean assignAnimal(Animal add){
		if(add.toString().startsWith("Cat") ){
			return super.assignAnimal(add);
		} else {
			return false;
		}
	}
	
	/**
	 * @return Vet Information
	 */
	public String toString(){
		return "CatVet:  " + super.toString();
	}
	
	/**
	 * @return Type of Veterinarian (Cat Vet)
	 */
	public String getType(){
		return type;
	}
}
