package model.veterinarians;

import model.BadInfoExecption;

/**
 * Object for Veterinarians that can only see any animal in the Vet Office
 * @author EmilyRing
 */
public class AllVet extends Vet{
	private String type;

	/**
	 * Class constructor
	 * @param name - Name of Veterinarian 
	 * @param ID - Veterinarian ID number
	 * @throws BadInfoExecption - if name or number are incorrect
	 */
	public AllVet(String name, int ID) throws BadInfoExecption{
		super(name, ID);
		type = "All Animals";
	}
	
	/**
	 * @return String of Vet information
	 */
	public String toString(){
		return "AllVet:   " + super.toString();
	}
	
	/**
	 * @return type of Vet (AllVet)
	 */
	public String getType(){
		return type;
	}
}