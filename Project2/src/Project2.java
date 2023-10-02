//****************************************************************
// Nicholas Tullbane
//
// Java Templated Doubly Linked List Class
//****************************************************************
//****************************************************************
//
// DLList Class
//
//****************************************************************
class DLList<T> {

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
		DLListNode(T item, DLListNode<T>p, DLListNode<T>n) {
			data = item;
			prev = p;
			next = n;
		}

		//****************************************************************
		// Get element.
		//****************************************************************
		T getElement() {
			return data;
		}
		
		//****************************************************************
		// Set element.
		//****************************************************************
		void setElement(T item) {
			data = item;
		}

		//****************************************************************
		// Get previous.
		//****************************************************************
		DLListNode<T> getPrev() {
			return prev;
		}
		
		//****************************************************************
		// Set previous.
		//****************************************************************
		void setPrev(DLListNode<T> node) {
			prev = node;
		}

		//****************************************************************
		// Get Next.
		//****************************************************************
		DLListNode<T> getNext() {
			return next;
		}

		//****************************************************************
		// Set Next.
		//****************************************************************
		void setNext(DLListNode<T> node) {
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
		header = new DLListNode<>(null,null,null);                    
		trailer = new DLListNode<>(null,header,null);               //Setting up header and trailer for Doubly Linked List
		header.setNext(trailer);
		current = null;
	}

	//****************************************************************
	// Construct a list by shallow copying an existing list.
	// Set node current of the new list to the first node if the list 
	// is not empty.
	// Difference between shallow copy and deep copy:
	// www.geeksforgeeks.org/copy-python-deep-copy-shallow-copy
	//****************************************************************
	DLList(DLList<T> other) {
		DLList<T> copy = new DLList<T>();                      //creates new list
        DLListNode<T> node = other.header.getNext();           //creates new Node
        while(node != null){                                    
            copy.insertLast(node.getElement());                //while node is not null it copies elements from old list into the new one
            node = node.getNext();
        }
        if(copy.isEmpty() == false){                           //if the list is not empty we will set the first node as current node
            copy.current=copy.header.getNext();
        }

	}

	//****************************************************************
	// Delete all the nodes in the list.
	//****************************************************************
	void clear() {
		while(!isEmpty()) {   // a while loop to loop through all elements within the Doubly Linked List
			deleteFirst();    //uses the delete first method to delete the first element 
		}
	}

	//****************************************************************
	// Return the number of nodes in the list.
	//****************************************************************
	int size() {
		return size;
	}

	//****************************************************************
	// Return whether the list is empty.
	//****************************************************************
	boolean isEmpty() {
        if(size==0) return true;
        else {return false;}
	}

	//****************************************************************
	// Return whether node current points to the first node.
	//****************************************************************
	boolean atFirst() {
		if(isEmpty()) return false;
		else { 
			return(current.getPrev() == header);          //returns true if current is the node after the header
		}
	}

	//****************************************************************
	// Return whether node current points to the last node.
	//****************************************************************
	boolean atLast() {
		if(isEmpty()) return false;
		else {
			return(current.getNext() == trailer);         //returns true if current is the node before the trailer
		}
	}

	//****************************************************************
	// Set node current to the first node, and return true.
	// Or return false if the list is empty.
	//****************************************************************
	boolean first() {
		if(isEmpty()) return false;
		else {
			current = header.getNext();                 //Sets the node current to the node after the header
			return true;
		}
	}

	//****************************************************************
	// Set node current to the last node, and return true.
	// Or return false if the list is empty.
	//****************************************************************
	boolean last() {
		if(isEmpty()) return false;
		else {
			current = trailer.getPrev();                //Sets the node current to the node before the trailer and returns true
			return true;
		}
	}
	
	//****************************************************************
	// Set node current to its next node, and return true.
	// Or return false if no such node.
	//****************************************************************
	boolean next() {
		if(isEmpty() || current.getNext() == null) {
			return false;
		}else {
			current = current.getNext();                //Sets the node current to the next node in the list and returns true
			return true;
		}
	}

	//****************************************************************
	// Set node current to its previous node, and return true.
	// Or return false if no such node.
	//****************************************************************
	boolean previous() {
		if (isEmpty() || current == header.getNext()) return false;
		else {
			current = current.getPrev();                //Sets the node current to the previous node in the list and returns true
			return true;
		}
	}                          

	//****************************************************************
	// The index of the first real node is 0.
	// Set node current to the node at the specified index, and return
	// true.
	// Or return false if no such node.
	//****************************************************************
	boolean seek(int loc) {
		if(loc < 0 || loc >= size()) return false;          //If index given is out of range it will return false
        else if(isEmpty()){
            return false;
        }
		else {
            current = header.getNext();                            
			for (int j = 0; j < loc; j++) {                 //Will move the node current the next node int loc times and then return true
				current = current.getNext();
			}
			return true;
		}
	}

	//****************************************************************
	// Return the content of node current.
	// Or return null if the list is empty.
	//****************************************************************
	T dataRead() {
		if(isEmpty())return null;
		else {
			return current.getElement();                  //Returns the content of current node or will return null if list is empty
		}
	}
	
	//****************************************************************
	// Rewrite the content of node current if the list is not empty.
	//****************************************************************
	void dataWrite(T item) {
		if(isEmpty()) {
			current = null;                             
		}else {
			current.setElement(item);                     //Rewrites the current node with a new element called item
		}
	}
	
	//****************************************************************
	// Insert a new node to the front of the list, and set node
	// current to the new node.
	//****************************************************************
	void insertFirst(T item) {
		DLListNode<T> newest = new DLListNode<>(item,header,header.getNext());
		if(isEmpty()) {            
			header.setNext(newest);              //If the list is empty, we will add the new node with header and trailer pointing towards it
			trailer.setPrev(newest);
		}else {
			header.getNext().setPrev(newest);     //If the list has nodes, we will add the new node after the header and rearrange the arrows to point to the new node.
			header.setNext(newest);
		}
		current = newest;
		size++;
	}

	//****************************************************************
	// Insert a new node to the end of the list, and set node current
	// to the new node.
	//****************************************************************
	void insertLast(T item) {
		DLListNode<T> newest = new DLListNode<>(item,trailer.getPrev(),trailer);
		if(isEmpty()) {            				//Checking to see if the list is now empty
			header.setNext(newest);             
			trailer.setPrev(newest);            //Header and trailer are point to the same node.
		}else {
			trailer.getPrev().setNext(newest);     //Pointing the arrows to the new node called newest.
			trailer.setPrev(newest);
		}
		current = newest;
		size++;
	}
    
	//****************************************************************
	// Insert a new node before where node current points to if the
	// list is not empty; or insert a new node if the list is empty.
	// Set node current to the new node.
	//****************************************************************
	void insertAtCurrent(T item) {
		DLListNode<T> newest = new DLListNode<>(item,current.getPrev(),current);
		if(isEmpty()) {						//Checking to see if the list is empty
			header.setNext(newest);
			trailer.setPrev(newest);
		}else {
			current.getPrev().setNext(newest);
			current.setPrev(newest);
		}
		current = newest;					//Setting the new current node
		size++;                             //Increased the size of the list
	}

	//****************************************************************
	// Delete the first node in the list if the list is not empty.
	// Set node current to the new first node; or set node current to
	// null if the list becomes empty.
	//****************************************************************
	void deleteFirst() {
		if(isEmpty()) {
			current = null;
		}else {
            DLListNode<T> successor = header.getNext().getNext();
			header.setNext(successor);   //Setting the arrows from the header to the new first node successor
			successor.setPrev(header);  //Setting the arrows from the current to the new first node successor
            current = successor;    //Setting the new current node
            size--;                 //Decreasing the size of the list
		} 
		if(isEmpty()) {           //Checking to see if the list is now empty after decreasing the size
			current = null;
		}
	}

	//****************************************************************
	// Delete the last node in the list if the list is not empty.
	// Set node current to the new last node; or set node current to
	// null if the list becomes empty.
	//****************************************************************
	void deleteLast() {
        if(isEmpty()) {
			current = null;
		}
		else {
            DLListNode<T> predecessor = trailer.getPrev().getPrev();  //this is null for some reason
			trailer.setPrev(predecessor);
			predecessor.setNext(trailer);
            current = predecessor;     //Setting the new current node
            size--;                    //Decreasing the size of the list 
		}
		if(isEmpty()) {           //Checking to see if the list is now empty after decreasing the size
			current = null;
		}
	}

	//****************************************************************
	// Delete the node where node current points to if the list is not
	// empty.
	// Set node current to its next node; or set node current to its
	// previous node if node current was at the end of the list; or
	// set node current to null if the list becomes empty.
	//****************************************************************
	void deleteAtCurrent() {
		DLListNode<T> predecessor = current.getPrev();
		DLListNode<T> successor = current.getNext();
		if(isEmpty()) {
			current = null;
		}else if(successor == null) {    //End of the list
			deleteLast();                //If the current is at the last node. Will perform method deleteLast()
		}else if(predecessor == null) {    //Beginning of the list
			deleteFirst();                 //If the current is the first node. Will perform method deleteFirst()
		}else {                                  //Middle of the list
			predecessor.setNext(successor);
			successor.setPrev(predecessor);
			current = successor;
            size--;
		}
		if(isEmpty()) {                     //If list is now empty after decreasing, it will set current to null
			current = null;
		}
	}
}