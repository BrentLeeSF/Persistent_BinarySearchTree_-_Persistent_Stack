package project3;

public class PersistentStack {

	/**
	 * Variables to create stack array and size, initial top of stack, time, and count
	 */
	private Link[] firstStack;
	private Link realTop;
	int size = 8;
	Link current;
	PersistentStack newStack;
	private int stackCount;
	private int currentTime;
	private int currentNodes = 0;
	
	/**
	 * Constructor creates array of links for the stack, the top of the stack, stack count and time
	 */
	public PersistentStack() {
		firstStack = new Link[size];
		realTop = null;
		stackCount = 0;
		currentTime = 0;
	}

	/**
	 * If top is null, creates a new PersistentStack, new link, and adds to stack while increasing time.
	 * if top not null, checks the size of stack (if stack is too small, then we double the size of the
	 * array), then creates new link and adds to stack while connecting that link to the previous stack
	 * @param elem - the String of a letter to add to the stack
	 */
	public void push(String elem) {

		if (realTop == null || firstStack[1] == null) {
			
			newStack = new PersistentStack();
			
			Link newLink = new Link(elem, null);
			Link zeroLink = new Link();
			
			firstStack[0] = zeroLink;
			Link firstPoint = newLink;
			realTop = newLink;
			stackCount++;
			currentTime = currentTime();
			currentTime++;
			setCurrentNodes(getCurrentNodes() + 1);
			firstStack[currentTime] = firstPoint;
			current = newLink;

		} else {
			
			int checkSize = firstStack.length;
			stackCount++;
			currentTime = currentTime();
			currentTime++;
			setCurrentNodes(getCurrentNodes() + 1);
			
			if (checkSize <= currentTime) {
				firstStack = doubleArray(firstStack);
				checkSize = firstStack.length;
			}
			
			Link newLink = new Link(elem, null);
			newLink.setNext(current);
			realTop = newLink;
			Link dNew = newLink;
			firstStack[currentTime] = dNew;
			current = newLink;
		}
	}

	/**
	 * Removes link of stack and decreases size of stack, returns letter
	 * @return String - returns String of letter popped off
	 */
	public String pop() {

		if(realTop == null) {
			return null;
		}
		else {
			setCurrentNodes(getCurrentNodes() - 1);
			String returnString = firstStack[getCurrentNodes()].element();
			Link d1 = realTop.next();
			realTop = realTop.next();
			currentTime = currentTime();
			currentTime++;
			int checkSize = firstStack.length;
			
			if (checkSize <= currentTime) {
				firstStack = doubleArray(firstStack);
				checkSize = firstStack.length;
			}
			firstStack[currentTime] = d1;
			current = d1;

			return returnString;
		}
	}

	/**
	 * Goes through stack array and prints out the elements
	 */
	public void print() {

		for (int i = 0; i < firstStack.length; i++) {
			Link curr = firstStack[i];
			while(curr != null) {
				System.out.print(" Stack["+i+"]: " + curr.element());
				curr = curr.next();
			}
			System.out.println("");
		}
	}

	public int currentTime() {
		return currentTime;

	}

	/**
	 * Returns the size of the stack at the specific time that is passed in
	 * 
	 * @param time - Time is where exactly we are in the stack process (ex: after adding 4, then popping 2, 
	 * we would then have the time be 6 because 6 total things have happened to the stack)
	 * @return - Returns the size of the stack at specific time
	 */
	public int size(int time) {
		int someSize = 0;
		Link head = firstStack[time];
		while(head != null) {
			someSize++;
			head = head.next();
		}
		return someSize;
	}

	public int size() {
		return size(currentTime());
	}

	/**
	 * If time is zero then return empty array. If time is greater than zero then create a link with all elements
	 * in that link at that time. Then return array with letters either in order or reverse order
	 * 
	 * @param time - Time is where exactly we are in the stack process (ex: after adding 4, then popping 2, 
	 * we would then have the time be 6 because 6 total things have happened to the stack)
	 * @param reversed - if reversed is true then return an array of reversed elements.
	 * 	If reversed is false, return array of elements in normal order.
	 * @return - array of elements at specific time of stack in order requested.
	 */
	public String[] getAllElements(int time, boolean reversed) {

		if(time == 0) {
			String[] arr = new String[0];
			return arr;
		}
		
		if(time <= currentTime) {
			int i = 0;
			
			if(firstStack[time] != null && time < firstStack.length) {
				
				Link curr = firstStack[time];
				
				while(curr != null) {
					i++;
					curr = curr.next();
				}
				String[] arr = new String[i];
				Link newCurr = firstStack[time];
				int j = 0;
				
				if(reversed == false) {
					while(newCurr != null) {
						arr[j] = newCurr.element();
						j++;
						newCurr = newCurr.next();
					}
					return arr;
				}
				else {
					int length = arr.length-1;
					while(newCurr != null) {
						arr[length-j] = newCurr.element();
						j++;
						newCurr = newCurr.next();
					}
					return arr;
				}
			}
		}
		return null;
	}

	/**
	 * Creates new array twice size of original and puts all links in new array and returns it
	 * @param stack - Old stack is passed in
	 * @return Returns new array with elements in array twice size of original
	 */
	public Link[] doubleArray(Link[] stack) {

		Link[] newArray = new Link[stack.length * 2];

		for (int i = 0; i < stack.length; i++) {
			newArray[i] = stack[i];
		}
		return newArray;
	}

	public int getCurrentNodes() {
		return currentNodes;
	}

	public void setCurrentNodes(int currentNodes) {
		this.currentNodes = currentNodes;
	}


	/**
	 * Private class of links used on Persistent Stack
	 */
	private class Link {
		
		/* Private Data Members -- Link */
		
		private String element;
		private Link next;

		/* Constructors -- Link */

		Link(String elem, Link nextelem) {
			this.element = elem;
			next = nextelem;
		}

		Link() {
		}
		
		/* Access Methods -- Link */

		Link next() {
			return next;
		}

		String element() {
			return element;
		}

		void setNext(Link nextelem) {
			next = nextelem;
		}

		void setElement(String elem) {
			this.element = elem;
		}
	}
}
