package model;

/**
 * Object for Cat in the vet Office
 * @author EmilyRing
 */
public class Cat extends Animal{
	
	/**
	 * Class constructor
	 * @param animalName - Name of cat
	 * @param ownerName - Cat owner's name
	 * @param phone - Phone number of cat owner
	 * @param level - Emergency level of Dog (5 highest - 1 lowest)
	 * @throws BadInfoExecption - if any strings are empty and/or incorrect
	 */
	public Cat(String animalName, String ownerName, String phone, int level) throws BadInfoExecption {
		super(animalName, ownerName, phone, level);
	}
	
	/**
	 * @return String information of Cat
	 */
	public String toString(){
		return String.format("Cat:  " + super.toString());
	}
}
