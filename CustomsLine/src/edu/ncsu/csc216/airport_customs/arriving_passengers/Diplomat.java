
package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * Creates Diplomat Object for customs processing 
 * 
 * @author Emily Ring
 */
public class Diplomat extends Passenger {
	/*Passenger color */
	private Color color; 
	/**Min possible processing time for diplomat */
	public static final int MIN_PROCESS_TIME = 30;
	/**Max possible processing time for diplomat */
	public static final int MAX_PROCESS_TIME = 90;
	
	/**
	 * Diplomat constructor
	 * 
	 * @param arrivalTime - Passenger arrival time
	 * 
	 * @param deskTime - Passenger processing time
	 */
	public Diplomat (int arrivalTime, int deskTime){
		super(arrivalTime, deskTime);
		color = setColor(deskTime);
	}
	
	/**
	 * Accepts processing time from constructor and assigns passenger color
	 * 
	 * @param a - processing time from constructor 
	 */
	private Color setColor(int a){
		int halfway = (MIN_PROCESS_TIME + MAX_PROCESS_TIME) / 2;
		
		if (a < halfway){
			return new Color(153, 255, 153);
		} else {
			return Color.GREEN;
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
		
		if (c.length < 3){
			throw new IllegalArgumentException("Not enough desks in list");
		}
		
		line = (int) c.length / 2; //find diplomat desk
		
		setLineIndex(line); //sets line index before added to a line
		setWaitingProcessing(true); // change waiting in line to true
		c[line].addToLine(this);
	}
	
	/**
	 * Give color of Passenger 
	 * @return color of Passenger 
	 */
	public Color getColor(){
		return color;
	}

}
