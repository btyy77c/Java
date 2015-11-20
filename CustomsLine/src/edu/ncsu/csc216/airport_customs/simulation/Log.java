/**
 * 
 */
package edu.ncsu.csc216.airport_customs.simulation;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;

/**
 * Creates log of all passenger wait times and process times
 * 
 * @author emilyring
 *
 */
public class Log {
	/* The number of passengers who have logged their information and left the simulation.*/
	private int numCompleted;
	
	/*The sum of all wait times logged by passengers so far.*/
	private int totalWaitTime;
	
	/*The sum of all times that passengers took to do actual processing (excluding wait time). */
	private int totalProcessTime; 
	
	/**
	 * Constructor of the log class
	 */
	public Log(){
		numCompleted = 0;
		totalWaitTime = 0;
		totalProcessTime = 0;
	}
	
	/**
	 * number of passengers who have been added to the log
	 * 
	 * @return number of passengers who have been added to the log
	 */
	public int getNumCompleted(){
		return numCompleted;
	}
	
	/**
	 * Adds Passenger information to the log 
	 * 
	 * @param a Passenger that will be added to the log
	 */
	public void logItem(Passenger a){
		if (a != null && !a.isWaitingInCustomsLine()){
			totalWaitTime = totalWaitTime + a.getWaitTime();
			totalProcessTime = totalProcessTime + a.getProcessTime();
			numCompleted++;
		} 
		
	}
	
	/**
	 * Total wait time of all processed passengers
	 * @return Total wait time of all processed passengers
	 */
	public int getTotalWaitTime(){
		return totalWaitTime;
	}
	
	/**
	 * Procces time for all logged passengers
	 * @return totalProcessTime
	 */
	public int getTotalProcessTime(){
		return totalProcessTime;
	}
	
	/**
	 * average wait time of all passengers 
	 * 
	 * @return average wait time of all passengers
	 */
	public double averageWaitTime(){
		
		if (totalWaitTime == 0 || numCompleted == 0){
			return 0.0;
		} else {
			return (double) totalWaitTime / numCompleted / 60;
		} 
	}
	
	/**
	 * avaergae process time of all passengers
	 * 
	 * @return avaergae process time of all passengers
	 */
	public double averageProcessTime(){
		if (totalProcessTime == 0 || numCompleted == 0){
			return 0.0;
		} else {
			return (double) totalProcessTime / numCompleted / 60;
		}
		
	}

}
