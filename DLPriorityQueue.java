/**
 * @author Dhyey Patel
 *
 *  The method DLPriorityQueue is made to have a queue of doubly linked nodes that also have a priority
 *  The methods in this class are implemented from the Priority Queue Abstract Data Type
 *  This class is made for any object 
 */

public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	private int count;
	private PriorityNode<T> front, rear;
	
	// The constructor with no parameters
	// Sets the default variables to 0, or null
	public DLPriorityQueue() {
		count = 0;
		front = null;
		rear = null;
	}
	
	// This method is used to add the data item to the queue
	// This method will take in the data item as well as it's priority 
	// The priority is used to implement the Priority Node
	public void enqueue (T dataItem, double priority) {
		PriorityNode<T> temp = new PriorityNode<T>(dataItem,priority);
		// If there are no items in the Queue, then set front and rear to that Node
		if (count == 0) {
			front = temp;
			rear = temp;
		}
		// If there is at least one item in the Queue, then add it to the end 
		else {
			rear.setNext(temp);
			temp.setPrevious(rear);
			rear = temp;		
		}
		count++;
	}
	
	// This method is used to remove the data item that is at the front of the queue 
	// This method throws EmptyPriorityQueueException if the queue is empty
	public T dequeue() throws EmptyPriorityQueueException{
		// If the queue is empty then throw the EmptyPriorityQueueException
		if (count == 0) {
			throw new EmptyPriorityQueueException("The Queue is empty");
		}
		
		// As long as the queue is not empty remove and return the first data item 
		else {
			T tempItem = front.getDataItem();
			front.getNext().setPrevious(null);
			front = front.getNext();
			count --;
			return tempItem;
		}	
	}
	
	// This method is used to get the priority of a given data item
	//This method will take in the data item for which the priority is required 
	// This method throws the InvalidDataItemException if the data item is not in the queue
	public double getPriority(T dataItem) throws InvalidDataItemException{
		PriorityNode<T> check = front;
		// Do a linear search and if the data item is found return the priority of the data item
		for (int i=0; i<count;i++) {
			if ((check.getDataItem()).equals(dataItem)) {
				return (check.getPriority());
			}
			check = check.getNext();
		}
		// If the data item is not found throw the exception
		throw new InvalidDataItemException("The data item provided is invalid");
	}
	
	// This method is used to change the priority of a given data item 
	// This method will take in the data item for which the priority needs to be changed, and the new priority 
	// This method throws the InvalidDataItemException if the data item is not in the queue
	public void changePriority (T dataItem, double newPriority) throws InvalidDataItemException{
		boolean done = true;
		PriorityNode<T> check = front;
		// Do a linear search and change the priority if the data item is found
		for (int i=0; i<count;i++) {
			if ((check.getDataItem()).equals(dataItem)) {
				check.setPriority(newPriority);
				done = false;
			}
			check = check.getNext();
		}
		// If the data item is not found throw and exception
		if (done) {
			throw new InvalidDataItemException ("The data item provided is invalid");
		}
	}
	
	// This method is used to get the data item associated with the smallest priority
	// This method throws the EmptyPriorityQueueException if the queue is empty
	public T getSmallest() throws EmptyPriorityQueueException{
		PriorityNode<T> smallest = front;
		// If the queue is empty throw the exception
		if (count == 0) {
			throw new EmptyPriorityQueueException ("The Queue is empty");
		}
		//Do a linear search and set smallest to the data item with the smallest priority
		else {
			PriorityNode<T> check = front;
			for (int i=1; i<count;i++) {
				if (check.getPriority() < smallest.getPriority()) {
					smallest = check;
				}
				check = check.getNext();
			}
			
			boolean checker = true;
			int counter = 1;
			check = front;
			
			// Go through the queue and delete the data item with the smallest priority
			// Take into account the different positions of the data item (ex. front, middle, rear)
			while (checker) {
				if (smallest.getDataItem().equals(check.getDataItem())) {
					if (counter == 1 && count == 1) {
						front = null;
						rear = null;
					}
					else if (counter == 1) {
						front.getNext().setPrevious(null);
						front = front.getNext();
					}
					else if (counter == count) {
						check.getPrevious().setNext(null);
						rear = check;
					}
					else {
						check.getPrevious().setNext(check.getNext());
						check.getNext().setPrevious(check.getPrevious());
					}
					count --;
					checker = false;
				}
				check = check.getNext();
				counter++;
			}
			// Once the data item is removed return it 
			return smallest.getDataItem();
		}
	}

	// Method returns a boolean value on if the queue is empty
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}
		return false;
	}
	
	// This method return the number of data items in the queue
	public int numItems() {
		return count;
	}
	
	
	// This method returns a string representation of the queue by getting the string representation of the each data item
	public String toString() {
		String returnString="";
		PriorityNode<T> check = front;
		for (int i=0; i<count;i++) {
			returnString = returnString + check.getDataItem().toString() + "\n";
			check = check.getNext();
		}
		return returnString;
	}
	
}
