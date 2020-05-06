import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Comparator;

//For interface Queue, see: http://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
//Note that all interface methods for Queue should be implemented here, those for
//the Collection interface should be in SimpleTree.

//IMPORTANT: This class _extends_ SimpleKTree, don't re-implement anything here, use inheritance!

//NOTE: You may assume null values are not allowed in the Queue.
/**
 * The Priority tree class that inherits simpleKTree and implements to form a generic priority heap.
 * @param <E> Can handle any type of object.
 */
public class PriorityTree<E> extends SimpleKTree<E> implements Queue<E> {
	//you code here
	//private or protected fields only
	
	/**
	 * The Comparator object initialization.
	 */
	private Comparator<? super E> comp;
	
	/**
	 * Constructor which takes a comparator to be used later when adding/swapping values in the queue and the k value of the k-ary tree.
	 * @param comp the type of comparison used.
	 * @param k the k-value of the heap.
	 */
	public PriorityTree(Comparator<? super E> comp, int k)  {
		
		//if you are unfamiliar with the Comparator interface, you can find it here:
		//see: http://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
		//it's just a way of comparing objects using something other than their
		//"natural ordering"

		super(k);
		this.comp = comp;

	}
	
	/**
	 * Takes an array representation of a tree to create the queue and forms the heap from the bottom up.
	 * @param comp the type of comparison used.
	 * @param arrayTree the array tree passed for the heapify.
	 * @param k the k-value of the heap.
	 */
	public PriorityTree(Comparator<? super E> comp, E[] arrayTree, int k) {
		//OPTIONAL METHOD (you do not get extra points for doing it, just an imaginary pat on the
		//back). Takes an array representation of a tree and performs "heapify" to create the queue
		//use the method which forms the heap from the bottom up (not the method that repeatedly
		//adds elements). This may (or may not) be covered in all sections of the class, so
		//it is not required, but encouraged for those interested.
		
		//DO NOT DELETE METHODS YOU DON'T COMPLETE.
		
		super(k);
		this.comp = comp;
		// heapify arrayTree and then add
		heapify(comp, arrayTree, k);
		for (int i = 0; i < arrayTree.length; i++){
			nodeList.add(arrayTree[i]);
		}
	}
	
	//you code here
	//private or protected methods only (except those required by the Queue interface)
	/**
	 * Takes an array representation of a tree to create the queue and forms the heap from the bottom up.
	 * @param comp the type of comparison used.
	 * @param arrayTree the array tree passed for the heapify.
	 * @param k the k-value of the heap.
	 */
	private void heapify(Comparator<? super E> comp, E[] arrayTree, int k) {
		int i = arrayTree.length - 1;
		while (i >= 0) {
			percDown(comp, arrayTree, k, i);
			i--;
		}
	}

	/**
	 * PercDown - Percolates node as far down as needed - ie. Swaps parent with min Value child recursively. Used for heapify.
	 * @param <E> Any object type.
	 * @param comp the type of comparison used.
	 * @param arrayTree the array tree passed for the heapify.
	 * @param k the k-value of the heap.
	 * @param parentIndex the parent index that needs to be percolated down.
	 */
	private static <E> void percDown(Comparator<? super E> comp, E[] arrayTree, int k, int parentIndex)
	{
		//System.out.println("percDown: start, parentIndex : " + parentIndex + ", arrayTree = " + toString(arrayTree));
		int firstChildIndex = parentIndex * k + 1;
		
		E parentValue = arrayTree[parentIndex];

		int swapIndex = -1;
		E minValue = parentValue;
		// go through all siblings, find the smallest
		for (int childIndex = firstChildIndex; childIndex < firstChildIndex + k && childIndex < arrayTree.length; childIndex++) {
			E childValue = arrayTree[childIndex];
			int c = comp.compare(minValue, childValue);
			if (c > 0) {
				// child node value is smaller
				swapIndex = childIndex;
				minValue = childValue;
			}
		}

		if (swapIndex >= 0) {	// found smaller value
			arrayTree[parentIndex] = minValue;
			arrayTree[swapIndex] = parentValue;
			//System.out.println("percDown: after swap: " + toString(arrayTree));
			percDown(comp, arrayTree, k, swapIndex);
		}
	}

	//********************************************************************************
	// Testing code... edit this as much as you want!
	//********************************************************************************
	/**
	 * Main method for testing code.
	 * @param arg no args.
	 */
	public static void main(String[] arg) {
		//maybe add some yays?
		
		
		//When you think you're ready for testing, uncomment this block. It's a
		//secondary check to make sure your code compiles with the test cases used
		//for grading.

		/**
		 * Random Object Class for testing.
		 */
		class Banana { int size; }
		
		int x, x1, x2;
		String s;
		boolean b;
		Banana a;
		PriorityTree<Banana> tree1;
		PriorityTree<String> tree2;
		PriorityTree<Integer> tree3;
		
		//comparator for bananas in size order
		Comparator<Banana> comp = new Comparator<Banana>() {
			public int compare(Banana o1, Banana o2) { return ((Integer)(o1.size)).compareTo(o2.size); }
		};
		
		//comparator for strings in reverse alphabetical order
		Comparator<String> revComp = new Comparator<String>() {
			public int compare(String o1, String o2) { return o2.compareTo(o1); }
		};
		
		//comparator for strings in reverse alphabetical order
		Comparator<String> strComp = new Comparator<String>() {
			public int compare(String o1, String o2) { return o1.compareTo(o2); }
		};

		
		Comparator<Integer> intComp = new Comparator<Integer>() {
			//public int compare(Integer o1, Integer o2) { return o1.compareTo(o2); }
			public int compare(Integer o1, Integer o2) { 
				//System.out.println("intComp: o1 = "+ o1 + "; o2 = "+ o2);
				return o1.compareTo(o2); 
			}			
		};
		Comparator<Integer> revIntComp = new Comparator<Integer>() {
			//public int compare(Integer o1, Integer o2) { return o1.compareTo(o2); }
			public int compare(Integer o1, Integer o2) { 
				//System.out.println("intComp: o1 = "+ o1 + "; o2 = "+ o2);
				return o2.compareTo(o1); 
			}			
		};
		
		tree1 = new PriorityTree<>(comp, 2);
		tree2 = new PriorityTree<>(revComp, new String[] {"a", "b", "c"}, 7);		
		tree3 = new PriorityTree<>(intComp, 2);

		String k2walk = tree1.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.isEmpty() == true && tree3.size() == 0 && k2walk.equals("") ) {
			System.out.println("Yay 1a");
		}

		b = tree3.add(1);

		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 1 && k2walk.equals("1") ) {
			System.out.println("Yay 1b");
		}

		b = tree3.add(3);

		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 2 && k2walk.equals("1 3") ) {
			System.out.println("Yay 1c");
		}

		b = tree3.offer(3);

		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 3 && k2walk.equals("1 3 3") ) {
			System.out.println("Yay 1d");
		}

		x1 = tree3.element();
		x2 = tree3.peek();
		System.out.println("x1: " + x1 + ", x2: "+ x2);
		if ( x1 == 1 && x2 == 1) {
			System.out.println("Yay 2a");
		}

		x1 = tree3.remove();
		x2 = tree3.poll();
		System.out.println("x1: " + x1 + ", x2: "+ x2);
		if ( x1 == 1 && x2 == 3) {
			System.out.println("Yay 3a");
		}

		// testing priorty or ordering part of the Queue
		tree3.clear();
		// 5, 6, 3, 2, 7, 1
		System.out.println(tree3);
		tree3.add(5); System.out.println(tree3);
		tree3.add(6); System.out.println(tree3);
		tree3.add(3); System.out.println(tree3);
		tree3.add(2); System.out.println(tree3);
		tree3.add(7); System.out.println(tree3);
		tree3.add(1);
		
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 6 && k2walk.equals("1 3 2 6 7 5") ) {
			System.out.println("Yay 3b");
		}

		System.out.println("Test Remove for Tree: " + tree3 + " , maxK = " + tree3.maxK);
        x1 = tree3.remove();
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 5 && k2walk.equals("2 3 5 6 7") ) {
			System.out.println("Yay 4a");
		}

		x1 = tree3.remove();
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 4 && k2walk.equals("3 6 5 7") ) {
			System.out.println("Yay 4b");
		}

		x1 = tree3.remove();
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 3 && k2walk.equals("5 6 7") ) {
			System.out.println("Yay 4c");
		}

		x1 = tree3.remove(); 
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 2 && k2walk.equals("6 7") ) {
			System.out.println("Yay 4d");
		}

		x1 = tree3.remove();
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 1 && k2walk.equals("7") ) {
			System.out.println("Yay 4e");
		}

        x1 = tree3.remove();
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(): " + k2walk);
		if (tree3.size() == 0 && k2walk.equals("") ) {
			System.out.println("Yay 4f");
		}
		
		// K =  3: testing priorty or ordering part of the Queue
		tree3 = new PriorityTree<>(intComp, 2);
		// 5, 6, 3, 2, 7, 1
		System.out.println(tree3);
		tree3.add(5); System.out.println(tree3);
		tree3.add(6); System.out.println(tree3);
		tree3.add(3); System.out.println(tree3);
		tree3.add(2); System.out.println(tree3);
		tree3.add(7); System.out.println(tree3);
		tree3.add(1);
		System.out.println(tree3);
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(5a): " + k2walk);
		if (tree3.size() == 6 && k2walk.equals("1 3 2 6 7 5")) {
			System.out.println("Yay 5a - by adding all elements");
		}

		tree3 = new PriorityTree<>(intComp, new Integer[] {5, 6, 3, 2, 7, 1}, 2);
		k2walk = tree3.toString();
		System.out.println("k2walk.toString(5b): " + k2walk);
		if (tree3.size() == 6 && k2walk.equals("1 2 3 6 7 5")) {
			System.out.println("Yay 5b - second PriorityTree constructor");
		}

		// all other test
		b = tree1.offer(new Banana());
		a = tree1.element();
		a = tree1.peek();
		a = tree1.poll();
		try {
			a = tree1.remove();
			System.out.println("Nay 6");
		} catch (NoSuchElementException e) {
			System.out.println("Yay 6");
		}

		// Given tests

		x = 0;
		s = "";
		b = false;
		tree1 = new PriorityTree<>(comp, 2);
		tree2 = new PriorityTree<>(revComp, new String[] {"a", "b", "c"}, 7);
		
		b = tree1.add(new Banana());	System.out.println("tree1.add(new Banana()) = " + b);
		b = tree1.offer(new Banana());	System.out.println("tree1.offer(new Banana()) = " + b);
		a = tree1.element();			System.out.println("tree1.element() = " + a);
		a = tree1.peek();				System.out.println("tree1.peek() = " + a);
		a = tree1.poll();				System.out.println("tree1.poll() = " + a);
		a = tree1.remove();				System.out.println("tree1.remove() = " + a);
		
		x = tree1.height();				System.out.println("tree1.height()" + x);
		// tree1.clear();
		s = tree2.toString(); 			System.out.println("tree1.toString() = " + s);
		s = tree2.toStringPreOrder();	System.out.println("tree1.toStringPreOrder() = " + s);
		s = tree2.toStringPostOrder();	System.out.println("tree1.toStringPostOrder() = " + s);
		s = tree2.toStringWithLevels();	System.out.println("tree1.toStringWithLevels() = " + s);
		x = tree2.size();				System.out.println("tree1.size() = " + x);
		b = tree2.isEmpty();			System.out.println("tree1.isEmpty() = " + b);
	}


	
	/**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     */
	@Override
	public boolean add(E e) {		
		boolean s = offer(e);
		if (!s)
			throw new NullPointerException();
		return s;
	}

	/**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     */
	@Override
	public boolean offer(E e) {
		if (e == null){
			return false;
		}

		//System.out.println("Add value:  " + e + " ... START");
		nodeList.add(e);
		//System.out.println("After add value:  " + this);
		if (size() <= 1)
			return true;
		int lastIndex = size()-1;
		
		int parentOfLastIndex;
		E parentValue;
		int c;

		while (lastIndex > 0) {
			// compare e with its parent
			parentOfLastIndex = getIndexOfParent(lastIndex);
			parentValue = nodeList.get(parentOfLastIndex);
			c = this.comp.compare(e, parentValue);
			if (c < 0) {
				// switch - last with parent?
				//System.out.println("Switch: parentIndex = " + parentOfLastIndex + "(" + parentValue + "), lastIndex = "+ lastIndex + " ("+e+")");
				nodeList.set(lastIndex, parentValue);
				nodeList.set(parentOfLastIndex, e);
			}
			// else if (c == 0) {
			// 	// do nothing and stop
			// 	break;
			// }
			else {
				// do nothing and stop
				break;
			}
			lastIndex = parentOfLastIndex;
		}

		//System.out.println("After add value:  " + this + " ...FINAL");
		return true;
	}

	/**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll() poll()} only in that it throws an exception if
     * this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
	@Override
	public E remove() {
		E head = poll();
		if (head == null){
			throw new NoSuchElementException();
		}
		return head;
	}

	/**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
	@Override
	public E poll() {
		if (peek() == null){
			return null;
		}

		E head = nodeList.get(0);
		//System.out.println("Remove head:  " + this + " ... START");
		if (size() <= 2) {
			head = nodeList.remove(0);
			return head;
		}

		// move last item to root node
		nodeList.set(0, nodeList.remove(nodeList.size()-1));
		//System.out.println("Remove head:  " + this + " ... REMOVE and SWAP to root");

		percDown(0);

		//System.out.println("After add value:  " + this + " ...FINAL");

		return head;
	}

	/**
	 * PercDown - Percolates node as far down as needed - ie. Swaps parent with min Value child recursively.
	 * @param parentIndex the parent index that needs to be percolated down
	 */
	private void percDown(int parentIndex) {
		int firstChildIndex = parentIndex * maxK + 1;
		//System.out.println("percDown: parentIndex = " + parentIndex + ", firstChildIndex = " + firstChildIndex);

		E parentValue = nodeList.get(parentIndex);

		int swapIndex = -1;
		E minValue = parentValue;
		// go through all siblings, find the smallest
		for (int childIndex = firstChildIndex; childIndex < firstChildIndex + maxK && childIndex < size(); childIndex++) {
			E childValue = nodeList.get(childIndex);
			int c = this.comp.compare(minValue, childValue);
			if (c > 0) {
				// child node value is smaller
				swapIndex = childIndex;
				minValue = childValue;
			}
		}

		if (swapIndex > 0) {	// found smaller value
			nodeList.set(parentIndex, minValue);
			nodeList.set(swapIndex, parentValue);
			//System.out.println("percDown: after swap: " + this.toString());
			percDown(swapIndex);
		}
	}


	/**
	 * Switches values upwards.
	 * @param parentIndex the index of the parent node.
	 * @param childIndex the index of the child node.
	 * @return boolean - true switch or false - no need. 
	 */
	private boolean percUp(int parentIndex, int childIndex) {
		E parentValue = nodeList.get(parentIndex);
		E childValue = nodeList.get(childIndex);

		int swapIndex = -1;
		E switchValue = parentValue;
		// go through all siblings, find the smallest
		for (int i = childIndex; i < childIndex + maxK && i < size(); i++) {
			childValue = nodeList.get(i);
			int c = this.comp.compare(switchValue, childValue);
			if (c > 0) {
				// switch - parent with child?
				swapIndex = i;
				switchValue = childValue;
			}
		}

		if (swapIndex > 0) {	// found smaller value
			nodeList.set(childIndex, parentValue);
			nodeList.set(parentIndex, childValue);
			return true;
		}

		// int c = this.comp.compare(parentValue, childValue);
		// if (c > 0) {
		// 	// switch - parent with child?
		// 	nodeList.set(childIndex, parentValue);
		// 	nodeList.set(parentIndex, childValue);
		// 	return true;
		// }
		// else {
		// 	// do nothing and stop
		// }
		return false;
	}


	@Override
	public E element() {
		if (size() <= 0){
			// throw exception
			throw new NoSuchElementException();
		}
		return nodeList.get(0);
	}

	@Override
	public E peek() {
		if (size() <= 0){
			return null;
		}
		return nodeList.get(0);
	}
}