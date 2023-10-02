
abstract class AbstractStack<E> implements BDDStack<E> {
		
	/*
	 * Need to fix the flip method in this program
	 */
	
	int capacity;
	
	public AbstractStack(int capacity) {
//		if(capacity <= 0) {
//			throw new IllegalArgumentException("Capacity must be positive");
//		}
		this.capacity = capacity;
	}
		// ========================================
		// Secondary Methods
		// ========================================
		
		/**
		 * Returns the capacity of this stack.
		 * @return the capacity of this stack
		 */
		public int capacity() {
			return capacity;
		}
		/**
		 * Returns true if this stack is empty.
		 * @return true if this stack is empty
		 */
		public boolean isEmpty() { 
			return depth() == 0;
		}
		/**
		 * Returns true if this stack is full.
		 * @return true if this stack is full
		 */
		public boolean isFull() {
			return depth() == capacity;
		}
		/**
		 * Reverses the elements in this stack.
		 */
		public void flip() {
			BDDStack<E> temp = copy();         //Creating a temp stack that uses the copy method
			clear();                           //Clears the current stack
			for(E element : temp) {            //Adds back the elements in reverse order. 
				push(element);
			}
			                                        
		}
		/**
		 * Returns a new stack that is a shallow copy of this stack. The new stack
		 * has the same capacity as this stack.
		 * @return a new stack that is a shallow copy of this stack
		 */
		public BDDStack<E> copy(){
			
			BDDStack<E> temp = newInstance();   //Create new instance of a stack to temporarily store the elements
			for(E element : this) {             //Using the for each loop to push elements into the result stack.
				temp.push(element);                
			}
			
			BDDStack<E> result = newInstance();  //Result stack is the new copy created from the original stack.
			for(E element : temp) {              //Using the for each loop to push elements into the result stack.
				result.push(element);
			}
			return result;                       //Return the new copy. 
	}
}
