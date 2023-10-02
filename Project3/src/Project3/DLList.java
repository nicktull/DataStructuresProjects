package Project3;
//****************************************************************
// David Ajanaku (Template Provided by Dr. Mei)
//
// Java Templated Doubly Linked List Classs
//****************************************************************

//****************************************************************
//
// DLList Class
//
//****************************************************************
class DLList<T>{
	
	//****************************************************************
	//
	// DLListNode nested class
	//
	//****************************************************************
	private static class DLListNode<T> {
		//****************************************************************
		// DLList node private data members
		//****************************************************************
		private T data;
		private DLListNode<T> prev;
		private DLListNode<T> next;

		//****************************************************************
		// Constructor
		//****************************************************************
		DLListNode(T item, DLListNode<T> p, DLListNode<T> n) {
			//assigns the arguments of the constructor to their respective instance variables.
			data = item;
			prev = p;
			next = n;
		}

		//****************************************************************
		// Get element.
		//****************************************************************
		T getElement() {
			//returns the element of the node.
			return data;
		}

		//****************************************************************
		// Set element.
		//****************************************************************
		void setElement(T item) {
			//takes the argument and assigns it to the "data" variable.
			data = item;
		}

		//****************************************************************
		// Get previous.
		//****************************************************************
		DLListNode<T> getPrev() {
			//returns the object (node) that the "prev" variable refers to.
			return prev;
		}
		
		//****************************************************************
		// Set previous.
		//****************************************************************
		void setPrev(DLListNode<T> node) {
			//takes the argument and assigns it to the "prev" variable
			prev = node;
		}

		//****************************************************************
		// Get Next.
		//****************************************************************
		DLListNode<T> getNext() {
			//returns the object (node) that the "next" variable refers to.
			return next;
		}

		//****************************************************************
		// Set Next.
		//****************************************************************
		void setNext(DLListNode<T> node) {
			//takes the argument and assigns it to the "next" variable
			next = node;
		}
	}

	//****************************************************************
	// DLList private data members
	//****************************************************************
	private DLListNode<T> header;
	private DLListNode<T> trailer;
	//****************************************************************
	// !!!IMPORTANT!!!
	// Node current acts as an iterator.
	// We define that a node with non-null data field is a real node.
	// Node current should always point to a real node if there is
	// at least one real node in the list.
	// Otherwise, node current is null.
	//****************************************************************
	private DLListNode<T> current;
	private int size = 0;

	//****************************************************************
	// Default constructor
	//****************************************************************
	DLList() {
		//constructs a new empty list
		header = new DLListNode<>(null,null,null);
		trailer = new DLListNode<>(null, header, null);
		header.setNext(trailer);
	}

	//****************************************************************
	// Construct a list by shallow copying an existing list.
	// Set node current of the new list to the first node if the list 
	// is not empty.
	// Difference between shallow copy and deep copy:
	// www.geeksforgeeks.org/copy-python-deep-copy-shallow-copy
	//****************************************************************
	DLList(DLList<T> other) {
		//first we create our empty list that will serve as the shallow copy for "other"
		this();
		
		/* We're going to create this shallow copy by taking the next Node of the header of the old list
		 * and assigning it to the "next" variable for the header of our new List. We also take the previous node
		 * of the trailer of the old list and assign it to the "prev" variable of the trailer node of our new list.*/
		header.setNext(other.header.getNext());
		trailer.setPrev(other.trailer.getPrev());
		
		//if the size is greater than 0, than that means that there is a "first" node for us to assign to our "current" variable.
		if(other.size()>0) {
			current = other.header.getNext();
		}
		
		//takes the value assigned to the "size" variable of our old list and assigns it to the "size" variable of our new list
		size = other.size();
	}

	//****************************************************************
	// Delete all the nodes in the list.
	//****************************************************************
	void clear() {
		//makes the header and trailer point to each other so that the other nodes are no longer in the list
		header.setNext(trailer);
		trailer.setPrev(header);
		
		/* sets current equal to null if it was pointing to a node before, which completely disassociates our list from all of its
		   nodes except the header and trailer*/
		current = null;
		
		//sets the size of our list to zero since we are deleting all our nodes from it
		size = 0;
	}

	//****************************************************************
	// Return the number of nodes in the list.
	//****************************************************************
	int size() {
		//returns the integer value assigned to our "size" variable
		return size;
	}

	//****************************************************************
	// Return whether the list is empty.
	//****************************************************************
	boolean isEmpty() {
		//if condition is true, then "true" is returned.
		//if condition is false, then "false" is returned.
		return size==0;
	}

	//****************************************************************
	// Return whether node current points to the first node.
	//****************************************************************
	boolean atFirst() {
		/* If this returns true, then that means that our "current" variable is pointing to the first node
		 * of our list. If this returns false, then that either means that "current" is NOT pointing to the 
		 * first node in our list, or there isn't a first node in the list for "current" to even point to, so
		 * it means that "current" is null and "header.getNext()" should return our trailer node.*/
		
		/* this works because "current" will never point to our header or trailer nodes. "current" is either null or
		   it points to a real node.*/
		return current == header.getNext();
	}

	//****************************************************************
	// Return whether node current points to the last node.
	//****************************************************************
	boolean atLast() {
		/* This method functions in a similar fashion as "atFirst()". If this returns true, then that means 
		 * that our "current" variable is pointing to the last node of our list. If this returns false, then 
		 * that either means that "current" is NOT pointing to the last node in our list, or there isn't a 
		 * last node in the list for "current" to even point to, so it means that "current" is null and 
		 * "trailer.getPrev()" should return our header node.*/
		
		/* this works because "current" will never point to our header or trailer nodes. "current" is either null or
		   it points to a real node.*/
		return current == trailer.getPrev();
	}

	//****************************************************************
	// Set node current to the first node, and return true.
	// Or return false if the list is empty.
	//****************************************************************
	boolean first() {
		//if this if-statement executes then that means that our list is empty, so that means that we can just return "false".
		if(isEmpty()) return false;
		
		/* if the execution of this method didn't end with the if-statement above, then that means our list is NOT empty. Now
		   we just have "current" refer to the first node in our list and then we return "true".*/
			current = header.getNext();
			return true;
	}

	//****************************************************************
	// Set node current to the last node, and return true.
	// Or return false if the list is empty.
	//****************************************************************
	boolean last() {
		//this method functions in a similar fashion as "first()".
		
		//if this if-statement executes then that means that our list is empty, so that means that we can just return "false".
		if(isEmpty()) return false;
		
		/* if the execution of this method didn't end with the if-statement above, then that means our list is NOT empty. Now
		   we just have "current" refer to the last node in our list and then we return "true".*/
			current = trailer.getPrev();
			return true;
	}
	
	//****************************************************************
	// Set node current to its next node, and return true.
	// Or return false if no such node.
	//****************************************************************
	boolean next() {
		//first we check to see our list is empty, and if it is then we just return false.
		if(isEmpty()) return false;
		
		/* next, we check to see if the next node is the trailer node, because if it is then 
		 * we return "false" because "current" is only allowed to refer to real nodes.*/
		if(current.getNext().getElement() == null) return false;
		
		//if the top 2 if-statements don't execute, then we are in the clear to set "current" to its next node and return "true".
		current = current.getNext();
		return true;
	}

	//****************************************************************
	// Set node current to its previous node, and return true.
	// Or return false if no such node.
	//****************************************************************
	boolean previous() {
		//this method works in a similar fashion as "next()".
		
		//first we check to see our list is empty, and if it is then we just return false.
		if(isEmpty()) return false;
		
		/* next, we check to see if the previous node is the header node, because if it is then 
		 * we return "false" because "current" is only allowed to refer to real nodes.*/
		if(current.getPrev().getElement() == null) return false;
		
		//if the top 2 if-statements don't execute, then we are in the clear to set "current" to its previous node and return "true".
		current = current.getPrev();
		return true;
	}

	//****************************************************************
	// The index of the first real node is 0.
	// Set node current to the node at the specified index, and return
	// true.
	// Or return false if no such node.
	//****************************************************************
	boolean seek(int loc) {
		/* first, we check to see if the given index is even a valid index value. We also check
		   to see if the given index is outside of the index range of our list. This if-statement
		   also indirectly checks to make sure that the list isn't empty.*/	
		if((loc < 0) || (loc > (size-1))) return false;
		
		/* this for-loop is for iterating through our list. For each iteration, we'll assign the 
		   node we are currently on to the "current" variable, so that means that on our last
		   iteration of the for-loop the node that will be assigned to our "current" variable will
		   be the node whose index matches the value of the "loc" argument.*/	
		for(int i = 0;i <= loc;i++) {
			/* on our first iteration, we'll assign current to the first REAL node in our 
			 * list, so this if-statement will only be executed on our first iteration.*/
			if(i==0) current=header.getNext();
			
			//this if-statement will be what is executed for every iteration after the first iteration.
			if(i>0) current=current.getNext();
		}
		
		/* at this point in the method, the "current" variable should now be pointing to the node at the
		   specified index, so now we can just return "true".*/
		return true;
	}

	//****************************************************************
	// Return the content of node current.
	// Or return null if the list is empty.
	//****************************************************************
	T dataRead() {
		//first we check to see if our list is empty (if so then we return null).
		if(isEmpty()) return null;
		
		//now we just return the content being stored at the "current" node.
		return current.getElement();
	}
	
	//****************************************************************
	// Rewrite the content of node current if the list is not empty.
	//****************************************************************
	void dataWrite(T item) {
		/* first we check to see if the list is empty. If the list is empty we 
		   return in order to end the execution of the method.*/
		if(isEmpty()) return;
		
		//now we just change the content in our "current" node to the one given as an argument.
		current.setElement(item);
	}
	
	//****************************************************************
	// Insert a new node to the front of the list, and set node
	// current to the new node.
	//****************************************************************
	void insertFirst(T item) {
		/* first we check to see if the list is empty, if it is then we perform the 
		   method slightly different from how we would perform it if the list was 
		   NOT empty (if it is empty, we have to work around the fact that "header.getNext()"
		   is the "trailer", so we also have to change the trailer's "prev" variable as well).*/
		if(isEmpty()) {
			DLListNode<T> nodeToBeInsertedAsFirst = new DLListNode<>(item,header,trailer);
			header.setNext(nodeToBeInsertedAsFirst);
			trailer.setPrev(nodeToBeInsertedAsFirst);
			current = nodeToBeInsertedAsFirst;
			
			//increment the size by 1 to represent the new node added to the list
			size++;
			
			//end method execution here
			return;
		}
		
		/* if the if-statement above doesn't execute, then that means that the list is NOT empty and we
		 * perform the method almost the exact same as we did in the if-statement, except we substitute
		 * "header.getNext()" in the place of "trailer".*/
		DLListNode<T> nodeToBeInsertedAsFirst = new DLListNode<>(item,header,header.getNext());
		header.getNext().setPrev(nodeToBeInsertedAsFirst);
		header.setNext(nodeToBeInsertedAsFirst);
		current = nodeToBeInsertedAsFirst;
		
		//increment the size by 1 to represent the new node added to the list
		size++;
	}

	//****************************************************************
	// Insert a new node to the end of the list, and set node current
	// to the new node.
	//****************************************************************
	void insertLast(T item) {
		/*same idea as insertFirst(), except we're inserting at the end, so we'll utilize the trailer node
		more than the header node this time around.*/
		
		/* first we check to see if the list is empty, if it is then we perform the 
		   method slightly different from how we would perform it if the list was 
		   NOT empty (if it is empty, we have to work around the fact that "trailer.getPrev()"
		   is the "header", so we also have to change the header's "next" variable as well).*/
		if(isEmpty()) {
			DLListNode<T> nodeToBeInsertedAsLast = new DLListNode<>(item,header,trailer);
			trailer.setPrev(nodeToBeInsertedAsLast);
			header.setNext(nodeToBeInsertedAsLast);
			current = nodeToBeInsertedAsLast;
			
			//increment the size by 1 to represent the new node added to the list
			size++;
			
			//end method execution here
			return;
		}
		
		/* if the if-statement above doesn't execute, then that means that the list is NOT empty and we
		 * perform the method almost the exact same as we did in the if-statement, except we substitute
		 * "trailer.getPrev()" in the place of "header".*/
		DLListNode<T> nodeToBeInsertedAsLast = new DLListNode<>(item,trailer.getPrev(),trailer);
		trailer.getPrev().setNext(nodeToBeInsertedAsLast);
		trailer.setPrev(nodeToBeInsertedAsLast);
		current = nodeToBeInsertedAsLast;
		
		//increment the size by 1 to represent the new node added to the list
		size++;
	}
    
	//****************************************************************
	// Insert a new node before where node current points to if the
	// list is not empty; or insert a new node if the list is empty.
	// Set node current to the new node.
	//****************************************************************
	void insertAtCurrent(T item) {
		//if the list is empty, we just add a new node, have "current" refer to it, and increment "size".
		//we also change "header.getNext()" and trailer.getPrev()"" to our new node.
		if(isEmpty()) {
			DLListNode<T> nodeToBeInserted = new DLListNode<>(item,header,trailer);
			header.setNext(nodeToBeInserted);
			trailer.setPrev(nodeToBeInserted);
			current = nodeToBeInserted;
			size++;
			return;
		}
		//create new node and insert it between "current" and the node before it
		DLListNode<T> nodeToBeInserted = new DLListNode<>(item,current.getPrev(),current);
		current.getPrev().setNext(nodeToBeInserted);
		current.setPrev(nodeToBeInserted);
		
		//have "current" refer to our new node
		current = nodeToBeInserted;
		
		//increment size of list to represent new node
		size++;
	}

	//****************************************************************
	// Delete the first node in the list if the list is not empty.
	// Set node current to the new first node; or set node current to
	// null if the list becomes empty.
	//****************************************************************
	void deleteFirst() {
		//if list is empty, we don't do anything
		if(isEmpty()) return;
		
		//we will now delete the first node
		DLListNode<T> placeHolderNode = header.getNext();
		header.setNext(placeHolderNode.getNext());
		placeHolderNode.getNext().setPrev(header);
		placeHolderNode.setNext(null);
		placeHolderNode.setPrev(null);
		placeHolderNode = null;
		size--;
		
		//if the list is empty AFTER deleting the first node, we set "current" to null and end execution
		if(isEmpty()) {
			current = null;
			return;
		}
		
		//now we can just set "current" equal to the NEW first node
		current = header.getNext();
	}

	//****************************************************************
	// Delete the last node in the list if the list is not empty.
	// Set node current to the new last node; or set node current to
	// null if the list becomes empty.
	//****************************************************************
	void deleteLast() {
		//if list is empty, we don't do anything
		if(isEmpty()) return;
		
		//we will now delete the last node in the list
		DLListNode<T> placeHolderNode = trailer.getPrev();
		trailer.setPrev(placeHolderNode.getPrev());
		placeHolderNode.getPrev().setNext(trailer);
		placeHolderNode.setNext(null);
		placeHolderNode.setPrev(null);
		placeHolderNode = null;
		size--;
		
		//if the list is empty AFTER deleting the last node, we set "current" to null and end execution
		if(isEmpty()) {
			current = null;
			return;
		}
		
		//now we can just set "current" equal to the NEW last node
		current = trailer.getPrev();
	}

	//****************************************************************
	// Delete the node where node current points to if the list is not
	// empty.
	// Set node current to its next node; or set node current to its
	// previous node if node current was at the end of the list; or
	// set node current to null if the list becomes empty.
	//****************************************************************
	void deleteAtCurrent() {
		//if the list is initially empty, end execution of method
		if(isEmpty()) return;
		
		//if the list becomes empty after deleting the node at current
		if(size == 1) {
			header.setNext(trailer);
			trailer.setPrev(header);
			current.setNext(null);
			current.setPrev(null);
			current = null;
			size--;
			return;
		}
		
		//if node current is the last node in the list
		if(current.getNext().getElement() == null) {
			deleteLast();
			return;
		}
		
		/* if none of the if-statements above got executed, then that means we'll delete
		   current node and assign the node AFTER it as the new current node.*/
		DLListNode<T> placeHolderForOldCurrent = current;
		current.getPrev().setNext(current.getNext());
		current.getNext().setPrev(current.getPrev());
		current = current.getNext();
		placeHolderForOldCurrent.setNext(null);
		placeHolderForOldCurrent.setPrev(null);
		placeHolderForOldCurrent = null;
		size--;
		
	}
}