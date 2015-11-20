/**
 * 
 */
package edu.ncsu.csc216.airport_customs.queues;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * Creates Custom Desk Objec which can process passengers
 * after they leave the arrival hall 
 * 
 * @author emilyring
 *
 */
public class CustomsDesk implements TransitSystem {
	/*
	 * The time when the line for this customs desk will finally be clear all of passengers 
	 * currently in line.
	 */
	private int timeWhenAvailable;
	
	/* list of passengers waiting for or being processed at this customs desk.*/
	private PassengerQueue line;
	
	private Log log;
	
	/**
	 * constructs a Log and a PassengerQueue for processing
	 * 
	 * @param a - initializes the instance field log.
	 */
	public CustomsDesk(Log a){
		log = a;
		line = new PassengerQueue();
	}
	
	/**
	 * number of passengers still in line
	 * 
	 * @return the number of passengers still in line
	 */
	public int size(){
		return line.size();
	}
	
	/**
	 * Removes the front passenger from the line, logging their information in the process. 
	 * @return the passenger who was just processed
	 */
	public Passenger processNext(){
		Passenger front = line.remove(); // creates passenger front and removes passenger
		front.removeFromWaitingLine(); //remove from CustomDesk
		this.log.logItem(front); // logs passenger information
		return front; // returns passenger info
	}
	
	/**
	 * Checks to see if any passengers are in line at the 
	 * Customs Desk
	 * @return true if the line is not empty.
	 */
	public boolean hasNext(){
		return !line.isEmpty();
	}
	
	/**
	 * Tells when the passenger at the front of the line (currently being processed) will 
	 * finish processing and leave the simulation. 
	 * 
	 * @return departure time of the next passenger or Integer.MAX_VALUE if there is none
	 * line is empty.
	 */
	public int departTimeNext(){
		Passenger front = line.front();
		
		if (front == null){
			return Integer.MAX_VALUE;
		} else{
			return front.getArrivalTime() + front.getProcessTime() + front.getWaitTime();
		}
	}
	
	/**
	 * Adds a passenger to the end of the line, updating the passenger's waitTime as well as the time 
	 * when the line will be clear of all passengers currently in line.
	 * 
	 * @param b - new passenger to be added 
	 */
	public void addToLine(Passenger b){	
		if (b.getArrivalTime() >= timeWhenAvailable){
			timeWhenAvailable = b.getArrivalTime() + b.getProcessTime();
			b.setWaitTime(0);
		} else {
			timeWhenAvailable = timeWhenAvailable + b.getProcessTime();
			b.setWaitTime(timeWhenAvailable - b.getArrivalTime() - b.getProcessTime());
		}
		
		line.add(b);
	}
	
	/**
	 * Time when customs desk will be empty 
	 * @return Time when customs desk will be empty 
	 */
	public int getTimeWhenAvailable(){
		return timeWhenAvailable;
	}

}
