import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Comparator;

//For interface Queue, see: http://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
//Note that all interface methods for Queue should be implemented here, those for
//the Collection interface should be in SimpleTree.

//IMPORTANT: This class _extends_ SimpleKTree, don't re-implement anything here, use inheritance!

//NOTE: You may assume null values are not allowed in the Queue.

public class PriorityTree<E> extends SimpleKTree<E> implements Queue<E> {
	//you code here
	//private or protected fields only
	
	private Comparator<? super E> comp;
	
	public PriorityTree(Comparator<? super E> comp, int k) {
		//constructor which takes a comparator to be used later when adding/swapping
		//values in the queue and the k value of the k-ary tree
		
		//if you are unfamiliar with the Comparator interface, you can find it here:
		//see: http://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
		//it's just a way of comparing objects using something other than their
		//"natural ordering"
	}
	
	public PriorityTree(Comparator<? super E> comp, E[] arrayTree, int k) {
		//OPTIONAL METHOD (you do not get extra points for doing it, just an imaginary pat on the
		//back). Takes an array representation of a tree and performs "heapify" to create the queue
		//use the method which forms the heap from the bottom up (not the method that repeatedly
		//adds elements). This may (or may not) be covered in all sections of the class, so
		//it is not required, but encouraged for those interested.
		
		//DO NOT DELETE METHODS YOU DON'T COMPLETE.
		
		super(-1);
	}
	
	//you code here
	//private or protected methods only (except those required by the Queue interface)
	
	
	
	//********************************************************************************
	// Testing code... edit this as much as you want!
	//********************************************************************************
	
	public static void main(String[] arg) {
		//maybe add some yays?
		
		
		//When you think you're ready for testing, uncomment this block. It's a
		//secondary check to make sure your code compiles with the test cases used
		//for grading.
		
		/*
		class Banana { int size; }
		
		int x;
		String s;
		boolean b;
		Banana a;
		PriorityTree<Banana> tree1;
		PriorityTree<String> tree2;
		
		
		//comparator for bananas in size order
		Comparator<Banana> comp = new Comparator<Banana>() {
			public int compare(Banana o1, Banana o2) { return ((Integer)(o1.size)).compareTo(o2.size); }
		};
		
		//comparator for strings in reverse alphabetical order
		Comparator<String> revComp = new Comparator<String>() {
			public int compare(String o1, String o2) { return o2.compareTo(o1); }
		};
		
		
		tree1 = new PriorityTree<>(comp, 2);
		tree2 = new PriorityTree<>(revComp, new String[] {"a", "b", "c"}, 7);
		
		b = tree1.add(new Banana());
		b = tree1.offer(new Banana());
		a = tree1.element();
		a = tree1.peek();
		a = tree1.poll();
		a = tree1.remove();
		
		x = tree1.height();
		tree1.clear();
		s = tree1.toString();
		s = tree1.toStringPreOrder();
		s = tree1.toStringPostOrder();
		s = tree1.toStringWithLevels();
		x = tree1.size();
		b = tree1.isEmpty();
		*/
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
}