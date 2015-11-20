/**
 * 
 */
package edu.ncsu.csc216.airport_customs.queues;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;

/**
 * Creates an arrival hall for creating new customers
 * using Transit System interface 
 * 
 * @author Emily Ring
 */
public class ArrivalHall implements TransitSystem { 
	private PassengerQueue incomingPassengers;
	private CustomsDesk[] customsArea;
	
	/**
	 * Contructor for arrival hall 
	 * 
	 * @param a number of passengers that will be processed
	 * @param list Array od customs desks used to assign passenger 
	 */
	public ArrivalHall(int a, CustomsDesk[] list){
		customsArea = list;
		incomingPassengers = new PassengerQueue();
		
		if (a < 1){
			System.out.println("No passengers in Arrival Hall. Something went wrong");
		} else if ( a == 1){
			incomingPassengers.add(ArrivingAirplanes.generatePassenger()); // add Passengers to ArrivalHallQue
		} else {
			while (a > 0){
				incomingPassengers.add(ArrivingAirplanes.generatePassenger());
				a--;
			}
		}
	
	}
	
	/**
	 * Checks incomingPassengers to see if there are any other passengers
	 * in the arrival hall 
	 * 
	 * @return true if the ArrivalHall has more passengers
	 */
	public boolean hasNext(){
		return !incomingPassengers.isEmpty();
	}

	/**
	 * Processes the next passenger in the system and removes the passenger from the system.
	 * 
	 * @return the passenger who was just processed
	 */
	public Passenger processNext(){
		Passenger front = incomingPassengers.remove(); // create front Passenger and remove from queues
		front.getInLine(customsArea); // Assign to Customs Line 
		return front;
	}

	/**
	 * Determines the time that the next passenger will leave the ArrivalHall.
	 * If the system is empty, that time is Integer.MAX_VALUE.
	 * @return departure time of the next passenger or Integer.MAX_VALUE if there is
	 * no one left in the ArrivalHall
	 */
	public int departTimeNext(){
		Passenger front = incomingPassengers.front();
		
		if (front == null){
			return Integer.MAX_VALUE;
		} else {
			return front.getArrivalTime();
		}

	}

	/**
	 * Determines the number of passengers in the ArrivalHall
	 * 
	 * @return the number of passengers
	 */
	public int size(){
		return incomingPassengers.size();
	}
}
