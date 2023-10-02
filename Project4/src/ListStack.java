import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
/*
 * Need to fix the iterator in this class to satisfy the iterable interface
 */


public class ListStack<E> extends AbstractStack<E> {
	
	
	private ArrayList<E> list; //Generic ArrayList used for storage
	
	public ListStack(int capacity) {
		super(capacity);
		list = new ArrayList<E>(capacity);                   //Creating the ArrayList for the ListStack class
	}
		// ========================================
		// Primary Methods
		// ========================================
		
		/**
		 * Pushes the specified element onto this stack.
		 * @param element the element to be pushed
		 * @throws NullPointerException if {@code element == null}
		 * @throws IllegalStateException if this stack is full
		 */
		public void push(E element) throws NullPointerException, IllegalStateException{
			if (element == null) {
	            throw new NullPointerException("element cannot be null");   //Checks if there is an element that is null
	        }
	        if (isFull()) {
	            throw new IllegalStateException("stack is full");           //Throws an exception if the stack is full using the isFull() method.
	        }
	        list.add(element);
		}
		
		/**
		 * Removes and returns the element at the top of this stack.
		 * @return the element at the top of this stack
		 * @throws IllegalStateException if this stack is empty
		 */
		public E pop() throws IllegalStateException{
			 if (isEmpty()) {
		            throw new IllegalStateException("stack is empty");
		        }
		        return list.remove(list.size() - 1);
		}
		
		/**
		 * Returns the depth of this stack.
		 * @return the depth of this stack
		 */
		public int depth() {
			return list.size();
		}
		
		/**
		 * Empties this stack.
		 */
		public void clear() {                
			list.clear();
		}
		
		/**
		 * Returns a new, empty stack with the same capacity as this stack.
		 * @return a new, empty stack with the same capacity as this stack
		 */
		public BDDStack<E> newInstance(){
			return new ListStack<E>(capacity());      //Creates a new ListStack with the same capacity 
		}

		@Override
		public Iterator<E> iterator() {
			return new Iterable();
		}
		public class Iterable implements Iterator{   //Created own Iterator class to check if there is a next element
			
			private int cursor;

			public Iterable() {
				cursor = depth() -1;
			}
			@Override
			public boolean hasNext() { 
				return cursor >= 0;
			}

			@Override
			public Object next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				return list.get(cursor--);
			}
			
		}
}
