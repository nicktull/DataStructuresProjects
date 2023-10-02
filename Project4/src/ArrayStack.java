import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Comments are similar to the ListStack class.
 */
public class ArrayStack<E> extends AbstractStack<E>{
		
	private E[] elements; // Generic array used for storage
	private int top;      // index of the top element in stack
	private int size;     // size of the Array Stack
	
	
	@SuppressWarnings("unchecked")       //Warning recommended by IDE to and informs the compiler to ignore the warning when the type Object[] is casted to E[].
										 //We are getting a warning here because Java does not allow generic arrays to be created directly due to type erasure.
	public ArrayStack(int capacity) {
		super(capacity);
		elements = (E[]) new Object[capacity];
		top = -1;
		size = 0; 
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
	            throw new NullPointerException("Cannot push null onto the stack");
	        }
	        if (isFull()) {
	            throw new IllegalStateException("Stack is full");
	        }
	        top++;
	        elements[top] = element;
	        size++;
	    }
		
		/**
		 * Removes and returns the element at the top of this stack.
		 * @return the element at the top of this stack
		 * @throws IllegalStateException if this stack is empty
		 */
		public E pop() throws IllegalStateException{
			if (isEmpty()) {
	            throw new IllegalStateException("Stack is empty");
	        }
	        E element = elements[top];
	        elements[top] = null;
	        top--;
	        size--;
	        return element;
		}
		
		/**
		 * Returns the depth of this stack.
		 * @return the depth of this stack
		 */
		public int depth() {
			return size;
		}
		
		/**
		 * Empties this stack.
		 */
		public void clear() {
			for (int i = 0; i <= top; i++) {
	            elements[i] = null;
	        }
	        top = -1;
	        size = 0;
		}
		
		/**
		 * Returns a new, empty stack with the same capacity as this stack.
		 * @return a new, empty stack with the same capacity as this stack
		 */
		public BDDStack<E> newInstance(){
			return new ArrayStack<>(capacity());
		}

		public class Iterable implements Iterator{
			
			private int cursor;
			
			public Iterable() {
				cursor = size - 1;
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
				return elements[cursor--];
			}
		}
		
		@Override
		public Iterator<E> iterator() {
			return new Iterable();
		}
}
