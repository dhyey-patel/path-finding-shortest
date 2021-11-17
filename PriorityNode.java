/**
 * @author Dhyey Patel
 *
 *  Priority Node is a node that will be used to make a queue using doubly linked lists
 *  Priority Node holds the data item and the priority of the Node
 *  The class mainly has setter and getter methods to use the class to implement a doubly linked queue 
 */

public class PriorityNode <T>{
	private T dataItem;
	private PriorityNode<T> next;
	private PriorityNode<T> previous;
	private double priority;
	
	public PriorityNode() {
		dataItem = null;
		priority = 0;
		next = null;
		previous = null;
	}
	
	public PriorityNode(T data, double prio) {
		dataItem = data;
		priority = prio;
		next = null;
		previous = null;
	}
	
	public double getPriority() {
		return priority;
	}
	
	public T getDataItem() {
		return dataItem;
	}
	
	public PriorityNode<T> getNext(){
		return next;
	}
	
	public PriorityNode<T> getPrevious(){
		return previous;
	}
	
	public void setDataItem (T data) {
		dataItem = data;
	}
	
	public void setNext(PriorityNode<T> node) {
		next = node;
	}
	
	public void setPrevious(PriorityNode<T> node) {
		previous = node;
	}
	
	public void setPriority (double prio) {
		priority = prio;
	}
	
}
