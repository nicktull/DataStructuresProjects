

public class LBTreeV2<E> extends LinkedBinaryTree<E> {
	private int size =0;
	
	public int size() {
		return size;
	}
	
	public void addRoot(Position<E> p) throws IllegalStateException {
        if (size() != 0) throw new IllegalStateException("Tree is not empty");
        root = validate(p);                                               
        size = 1;
    }
	
	public void addLeft(Position<E> p, Position<E> q)
            throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = validate(q);                                    
        parent.setLeft(child);
        size++;
    }
	
	public void addRight(Position<E> p, Position<E> q)
            throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) 											  
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = validate(q);
        parent.setRight(child);
        size++;
    }

	
	
}
