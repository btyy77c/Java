package model;

import model.veterinarians.Vet;

/**
 * Abstract Class for all animals in service
 * class extends to Cat and Dog classes
 * 
 * @author EmilyRing
 *
 */
public abstract class Animal {
	/* Animal name */
	private String animalName;
	/*Owner name */
	private String ownerName;
	/*Phone number of animal owner*/
	private String phone;
	/*Emergency level of animal.  0 lowest and 5 highest*/
	private int level;
	/*Vet that worked on the animal*/
	private Vet vet;
	
	/**
	 * Constructor for Animal class
	 * @param animalNam _ Name of pet
	 * @param phon - Phone number of pet owner
	 * @param leve - Emergency level of animal
	 * @throws BadInfo 
	 */
	public Animal(String animalNam, String ownerNam, String phon, 
			int leve) throws BadInfoExecption {	
		animalName = checkEmpty(animalNam, "Animal name").trim();
		ownerName = checkEmpty(ownerNam, "Owner name").trim();
		
		if (phon.length() != 10){
			throw new BadInfoExecption("Error: phone number must be 10 digits");
		} else {
			phone = checkEmpty(phon, "phone number").trim();
		}
	
		if (leve < 0){
			level = 0;
		} else if (leve > 5){
			level = 5;
		} else {
			level = leve;
		}
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
	 * List of Animal information
	 */
	public String toString(){
		return "[" + level + "]" + animalName;
	}
	
	/**
	 * @return Animal name
	 */
	public String getName(){
		return animalName;
	}
	
	/**
	 * @return Owner name
	 */
	public String getOwnerName(){
		return ownerName;
	}
	
	/**
	 * @return Phone number for animal's phone number
	 */
	public String getPhone(){
		return "(" + phone.substring(0, 3) + ")" +
	            phone.substring(3, 6) + "-" + phone.substring(6);
	}
	
	/**
	 * @return Emergency level of animal
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * Add Vet Object information when animal is seen by a Vet
	 * @param add - Vet Object that will be assigned to the animal
	 */
	public void assignVet(Vet add){
		vet = add;
	}
	
	/**
	 * @return Vet that met with the animal
	 */
	public Vet getVet(){
		return vet;
	}

}
