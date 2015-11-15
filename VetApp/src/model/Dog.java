package model;

/**
 * Object for Dogs in the Vet Office
 * @author EmilyRing
 */
public class Dog extends Animal {

	/**
	 * Class constructor
	 * @param animalName - Name of dog
	 * @param ownerName - Dog owner's name
	 * @param phone - Phone number of dog owner
	 * @param level - Emergency level of Dog (5 highest - 1 lowest)
	 * @throws BadInfoExecption - if any strings are empty and/or incorrect
	 */
	public Dog (String animalName, String ownerName, String phone, int level) throws BadInfoExecption {
		super(animalName, ownerName, phone, level);
	}
	
	/**
	 * @return String information of Dog
	 */
	public String toString(){
		return String.format("Dog: " + super.toString());
	}
}
