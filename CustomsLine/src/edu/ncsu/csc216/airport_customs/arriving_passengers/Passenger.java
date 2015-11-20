/**
 * 
 */
package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * Abstract class that creates Passengers which will enter the simulation and be 
 * processed through other classes.
 * 
 * @author Emily Ring
 */
public abstract class Passenger {
	/**sets initial custom desk index to -1 */
	public static final int INITIAL_CUSTOMS_LINE_INDEX = -1;
	
	/**
	 * Time when the passenger enters a customs desk line. Time is measured by the 
	 * number of seconds from the beginning of the simulation. 
	 */
	private int arrivalTime;
	
	/**
	 * Number of seconds the passenger waits in a customs desk line before 
	 * processing. This should be computed when the passenger first enters 
	 * the line. This attribute has a getter and a setter.
	 */
	private int waitTime;
	
	/**
	 * Number of seconds required to actually process this passenger at the customs 
	 * desk (does not include time to wait in line prior to actual processing). 
	 * This attribute is specified by the constructor.
	 */
	private int processTime;
	
	/**
	 * The index of the CustomsDesk that the passenger has selected. Before the 
	 * passenger reaches the line (while still in the arrival hall), lineIndex 
	 * should be -1. This attribute has a public getter, but its setter is 
	 * protected.
	 */
	private int lineIndex;
	
	/*
	 * True if the passenger is currently in a line at a customs desk awaiting 
	 * processing. False if the passenger is still in the ArrivalHall or if 
	 * they have finished all processing and left the line. The corresponding 
	 * getter for this attribute is isWaitingInCustomsLine(). The value of this 
	 * attribute changes upon calls to removeFromWaitingLine() and getInLine().
	 */
	private boolean waitingProcessing;
	
	/**
	 * Constructs a passenger for processing
	 * 
	 * @param arrivalTime - the arrival time when the passenger leaves the arrival 
	 * hall and gets in line to wait for processing at the customs desk,
	 * 
	 * @param deskTime - the time the passenger requires for processing when 
	 * they reach the desk at the front of the line
	 * 
	 */
	public Passenger(int arrivalTime, int deskTime){
		
		if (arrivalTime < 0){
			throw new IllegalArgumentException ("Arrival Time must be greater than 0");
		}
		
		if (deskTime < 0){
			throw new IllegalArgumentException ("Processing Time must be greater than 0");
		}
		
		this.arrivalTime = arrivalTime;
		processTime = deskTime;
		waitingProcessing = false;
		lineIndex = INITIAL_CUSTOMS_LINE_INDEX;
	}

	/**
	 * Retrieves Passenger's arrival time
	 * 
	 * @return arrivalTime
	 */
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	/**
	 * Retrieves Passenger wait time
	 * 
	 * @return waitTime
	 */
	public int getWaitTime (){
		return waitTime;
	}
	
	
	/**
	 * Sets a wait time for a Passenger
	 * 
	 * @param newTime - int which will be used to set waitTime
	 */
	public void setWaitTime(int newTime){
		waitTime = newTime;
		
	}
	
	/**
	 * Retrieves processing time
	 * 
	 * @return processTime
	 */
	public int getProcessTime(){
		return processTime; 
	}
	
	/**
	 * Returns index int where Passenger is located in list
	 * 
	 * @return lineIndex
	 */
	public int getLineIndex(){
		return lineIndex;
	}
	
	/**
	 * Returns true/false if Passenger is waiting in line
	 * 
	 * @return waitingProcessing
	 */
	public boolean isWaitingInCustomsLine(){
		return waitingProcessing;
	}
	
	/**
	 * When Passenger leaves line, changes waitingProcessing to false
	 */
	public void removeFromWaitingLine(){
		waitingProcessing = false;
		
	}
	
	/**
	 * Sets index of passenger when Passenger is placed in a list
	 * 
	 * @param c - int used to corresponding line index
	 */
	protected void setLineIndex(int c){
		lineIndex = c;
	}
	
	/**
	 * The passenger enters the back of the line for their chosen CustomsDesk.
	 * 
	 * @param c - array of customs desk where passenger choosing line
	 */
	public abstract void getInLine(CustomsDesk[] c);
	
	/**
	 * Returns Color of Passenger 
	 * 
	 * @return color
	 */
	public abstract Color getColor();
	
	/**
	 *changes waitingProcessing 
	 *
	 *@param n - true/false for waitingProcessing
	 */
	public void setWaitingProcessing(Boolean n){
		waitingProcessing = n;
	}

}
