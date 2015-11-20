
package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * Creates Visitor Object for customs processing
 * 
 * @author Emily Ring
 */
public class Visitor extends Passenger {
	/*Visitor color */
	private Color color; 
	/**Min possible processing time for visitor*/
	public static final int MIN_PROCESS_TIME = 180;
	/**Max possible processing time for visitor */
	public static final int MAX_PROCESS_TIME = 600;
	
	/**
	 * Visitor constructor
	 * 
	 * @param arrivalTime - Passenger arrival time
	 * 
	 * @param deskTime - Passenger processing time
	 */
	public Visitor (int arrivalTime, int deskTime){
		super(arrivalTime, deskTime);
		color = setColor(deskTime);
	}
	
	/*
	 * Accepts processing time from constructor and assigns passenger color
	 * 
	 * @param a - processing time from constructor 
	 */
	private Color setColor(int a){
		int halfway = (MIN_PROCESS_TIME + MAX_PROCESS_TIME) / 2;
		
		if (a < halfway){
			return new Color(255, 153, 153);
		} else {
			return Color.RED;
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
			throw new IllegalArgumentException("No desks in list");
		}
		
		int range = (c.length / 2) + 1; // set desk range
		
		for (int i = range; i < c.length; i++){
			if (c[i].size() < size){
				line = i;
				size = c[i].size();
			}
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
