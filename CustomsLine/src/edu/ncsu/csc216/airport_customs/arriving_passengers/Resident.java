
package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * Creates Resident Object for customs processing
 * 
 * @author Emily Ring
 */
public class Resident extends Passenger {
	/*Resident color */
	private Color color; 
	/**Min possible processing time for resident */
	public static final int MIN_PROCESS_TIME = 120;
	/**Max possible processing time for resident */
	public static final int MAX_PROCESS_TIME = 300;
	
	/**
	 * Resident constructor
	 * 
	 * @param arrivalTime - Passenger arrival time
	 * 
	 * @param deskTime - Passenger processing time
	 */
	public Resident (int arrivalTime, int deskTime){
		super(arrivalTime, deskTime);
		color = setColor(deskTime);
		super.setWaitingProcessing(false);
	}
	
	/*
	 * Accepts processing time from constructor and assigns passenger color
	 * 
	 * @param a - processing time from constructor 
	 */
	private Color setColor(int a){
		int halfway = (MIN_PROCESS_TIME + MAX_PROCESS_TIME) / 2;
		
		if (a < halfway){
			return new Color(153, 153, 255);
		} else {
			return Color.BLUE;
		}
	}
	
	/**
	 * Accepts an array of customs desk
	 * checks each custom desk and assigns passenger to shortest line
	 * 
	 * @param c Array of custom desks 
	 */
	public void getInLine(CustomsDesk[] c){
		int line = -1; 
		int size = Integer.MAX_VALUE;
		
		if (c.length < 3){
			throw new IllegalArgumentException("Not enough desks in list");
		}
		
		int range = (int) c.length / 2; // set desk range
		
		
		for (int i = 0; i <= range - 1; i++){
			if (c[i].size() < size){
				line = i;
				size = c[i].size();
			}
		}
		
		if (c[range].size() == 0){ // get in diplomat line
			line = range;
		}
		
		setLineIndex(line);
		setWaitingProcessing(true);
		c[line].addToLine(this);
	}
	
	/**
	 * Returns Color of Passenger 
	 * 
	 * @return color
	 */
	public Color getColor(){
		return color;
	}

}
