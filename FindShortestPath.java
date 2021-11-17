/**
 * @author Dhyey Patel
 *
 * The class FindShortestPath is made to find the shortest path between the UWO store and the Customer's house
 * This class is made with the given algorithm from the assignment 
 * This class also only has a static main method that is used
 * 
 */


import java.io.FileNotFoundException;
import java.io.IOException;



public class FindShortestPath {

	public static void main(String[] args) {
		// Create a try-catch block to catch all the possible exceptions thrown 
		try {
			
			// Initialize all the needed variables
			Map cityMap = new Map(args[0]);
			MapCell current = cityMap.getUWOstore();
			MapCell neighbour;
			boolean check, customerCheck = true;
			double distance, priority;
			DLPriorityQueue<MapCell> queue= new DLPriorityQueue<MapCell>();
			
			// Put the first item into the queue, and mark it enqueued
			queue.enqueue(current, 0);
			current.markEnqueued();
			
			// Run this loop as long as the queue is not empty 
			while (!queue.isEmpty()) {
				// Get the cell with the smallest priority
				current = queue.getSmallest();
				if(current!=null) {
					current.markDequeued();
					check = true;
					// If the cell happens to be the tower cell then restart the while loop
					if (current.isTower()) {
						check = false;
					}
					// If the current cell is the customer cell then find the distance from the start to that cell
					// Also exit the while loop
					if(current.isCustomer()) {
						System.out.println("It took "+(current.getDistanceToStart()+1)+" cells to reach the destination.");
						customerCheck= false;
						break;
					}
					// Find all the neighbors of the current cell and if any of them are tower cells, restart the while loop
					for (int i=0; i<6; i++) {
						neighbour = current.getNeighbour(i);
						if (neighbour != null) {
							if (neighbour.isTower()) {
								check = false;
							}
						}
						
					}
					
					
					if (check) {
						// Go through each neighbor 
						for (int i=0; i<6; i++) {
							neighbour = current.getNeighbour(i);
							if(neighbour != null) {
								// If the cell is non flying and not in the queue
								if(!neighbour.isNoFlying() && !neighbour.isMarkedDequeued()) {
									// If the distance from nighbour to start is greater than distance than set current as neighbour's predecesor 
									distance = 1+ current.getDistanceToStart();
									if (neighbour.getDistanceToStart()>distance) {
										neighbour.setDistanceToStart((int) distance);
										neighbour.setPredecessor(current);
									}
									// Calculate the priority of neighbour and add change it in the queue if it is smaller
									priority = (neighbour.getDistanceToStart() + neighbour.euclideanDistToDest(cityMap));
									if (neighbour.isMarkedEnqueued() && (priority < queue.getPriority(neighbour))){
										queue.changePriority(neighbour, priority);
									}
									//If neighbour is not enqueued than enqueue it with teh calculated priority 
									if(!neighbour.isMarkedEnqueued()) {
										queue.enqueue(neighbour, priority);
										neighbour.markEnqueued();
									}
								}
							}
						}
					}
				}				
			}
			// Print this if the destination cannot be reached 
			if(customerCheck) {
				System.out.println("The Destination cannot be reached");
			}
		} 
		// Catch all the exceptions that coulf have been thrown
		catch (InvalidMapException e) {
			System.out.println(e);
		} 
		catch (FileNotFoundException e) {
			System.out.println(e);
		} 
		catch (InvalidDataItemException e) {
			System.out.println(e);
		}
		catch (EmptyPriorityQueueException  e) {
			System.out.println(e);
		}
		catch (IOException e) {
			System.out.println(e);
		}
		

	}

}
