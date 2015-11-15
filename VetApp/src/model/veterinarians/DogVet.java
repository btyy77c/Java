package model.veterinarians;

import model.Animal;
import model.BadInfoExecption;

/**
 * Object for Veterinarians that can only see Dogs in the Vet Office
 * @author EmilyRing
 */
public class DogVet extends Vet{
	private String type;
	
	/**
	 * Class constructor
	 * @param name - Name of Veterinarian 
	 * @param ID - Veterinarian ID number
	 * @throws BadInfoExecption if name or ID are incorrect
	 */
	public DogVet(String name, int ID) throws BadInfoExecption{
		super(name, ID);
		type = "Dog";
	}
	
	/**
	 * Adds new Animal to Vet Object.  Will only accept Dog Objects
	 * @return True if Animal Obecjt was added to Vet
	 */
	public boolean assignAnimal(Animal add){
		if(add.toString().startsWith("Dog") ){
			return super.assignAnimal(add);
		} else {
			return false;
		}
	}
	
	/**
	 * String list of Vet Info
	 */
	public String toString(){
		return "DogVet: " + super.toString();
	}
	
	/**
	 * @return Type of Veterinarian (Dog Vet)
	 */
	public String getType(){
		return type;
	}
}