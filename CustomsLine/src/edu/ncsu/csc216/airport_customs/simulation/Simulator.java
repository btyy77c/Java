/**
 * 
 */
package edu.ncsu.csc216.airport_customs.simulation;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.queues.ArrivalHall;
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.queues.TransitSystem;

/**
 * Creates a simulator that will run the program simulation 
 * 
 * @author Emily Ring
 *
 */
public class Simulator {
	private static final int MIN_NUM_DESKS = 3;
	private static final int MAX_NUM_DESKS = 17;
	private int numCustomsDesks;
	private int numPassengers;
	private int stepsTaken;
	private TransitSystem[] desk;
	private Log myLog;
	private TransitSystem arrivingPassengers;
	private Passenger currentPassenger;
	private EventCalendar myCalendar; 
	
	/**
	 * COntructor for simulator 
	 * 
	 * @param a number of desks for the simulator
	 * @param b number of passengers which will be processed
	 */
	public Simulator(int a, int b){
		numPassengers = b;
		numCustomsDesks = a;
		
		if (numPassengers < 1){
			throw new IllegalArgumentException ("There must be at least one Passenger");
		}
		
		if (numCustomsDesks < MIN_NUM_DESKS || numCustomsDesks > MAX_NUM_DESKS){
			throw new IllegalArgumentException ("Number of customs desks must be between 3 and 17");
		}
		
		stepsTaken = 0;
		myLog = new Log();
		
		desk = new CustomsDesk[numCustomsDesks];
		for (int i = 0; i < numCustomsDesks; i++){
			desk[i] = new CustomsDesk(myLog);
		}
		
		arrivingPassengers = new ArrivalHall(numPassengers, (CustomsDesk[]) desk);
		
		myCalendar = new EventCalendar(desk, arrivingPassengers);
		
	}
	
	
	/**
	 * Handle the next event from the EventCalendar.
	 */
	public void step(){
		
		if (stepsTaken > totalNumberOfSteps()){
			throw new IllegalStateException("Too many steps");
		}
		
		currentPassenger = null;
		currentPassenger = myCalendar.nextToBeProcessed().processNext();
		stepsTaken++;
	}
	
	/**
	 * Number of steps taken so far.
	 * 
	 * @return stepsTaken
	 */
	public int getStepsTaken(){
		return stepsTaken;
	}
	
	/**
	 * Total number of steps in the simulation.
	 * 
	 * @return numPassengers * 2
	 */
	public int totalNumberOfSteps(){
		return numPassengers * 2;
	}
	
	/**
	 * true if the simulation has not yet finished, false if it has.
	 * 
	 * @return stepsTaken < totalNumberOfSteps()
	 */
	public boolean moreSteps(){
		return stepsTaken < totalNumberOfSteps();
	}
	
	/**
	 * Index of CustomsDesk selected by the currentPassenger, or -1 if currentPassenger is null.
	 * 
	 * @return currentPassenger.getLineIndex(); or -1
	 */
	public int getCurrentIndex(){
		if (currentPassenger == null){
			return -1;
		} else {
			return currentPassenger.getLineIndex();
		}
		
	}
	
	/**
	 * Color of the currentPassenger or null if currentPassenger is null.
	 * 
	 * @return currentPassenger.getColor()
	 */
	public Color getCurrentPassengerColor(){
		if (currentPassenger == null){
			return null;
		} else {
			return currentPassenger.getColor();
		}
		
	}
	
	/**
	 * Return true if the most recently handled passenger completed processing and 
	 * left a customs desk line; false if the most recently handled passenger 
	 * left the arrival hall to enter a customs desk line or if there is no 
	 * current passenger.
	 * 
	 * @return !currentPassenger.isWaitingInCustomsLine()
	 */
	public boolean passengerClearedCustoms(){
		
		if (currentPassenger == null){
			return false;
		} else {
			return !currentPassenger.isWaitingInCustomsLine();
		}
			
	}
	
	/**
	 * Average number of minutes passengers had to wait in a customs desk line 
	 * prior to actual processing.
	 * 
	 * @return myLog.averageWaitTime()
	 */
	public double averageWaitTime(){
		return myLog.averageWaitTime();
	}
	
	/**
	 * Average number of minutes passengers spent in actual processing.
	 * 
	 * @return myLog.averageProcessTime();
	 */
	public double averageProcessTime(){
		return myLog.averageProcessTime();
	}
}
