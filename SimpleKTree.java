// For interface Collection, see: http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html
// Note that all interface methods for Collection should be implemented here in addition to those
// outlined below.
import java.util.Collection;
import java.util.Iterator;

//This is imported for you, and you're welcome to use an ArrayList for storage,
//or expand/shrink your own array storage, whichever you find more fun.
import java.util.ArrayList;

public class SimpleKTree<E> implements Collection<E> {
	//you code here
	//private or protected fields only
	
	//Notes: for this assignment, you may assume that the tree is always nearly complete
	//this will significantly simplify both your size() and height() methods
	
	public SimpleKTree(int k) {
		//creates an empty k-ary tree
		//throws InvalidKException (you need to create this) if the k value is invalid (< 2)
	}
	
	public SimpleKTree(E[] arrayTree, int k) {
		//creates a k-ary tree from an array (which is already in k-ary tree array representation)
		//throws InvalidKException (you need to create this) if the k value is invalid (< 2)
		
		//NOTE: You may assume null values are not allowed in the tree, so any elements
		//in the array are real elements and the array will not contain more space than
		//is needed for the nearly complete tree (so no nulls appearing anywhere).
	}
	
	public int height() {
		return 0;
		//returns the height of the k-ary tree
		//can be done in O(1) time... use math!
		//worst case for this assignment: O(log-base-k(n))
	}
	
	public void clear() {
		//removes all the elements from the k-ary tree
	}
	
	public String toString() {
		return null;
		//creates a string representation of the current tree where each element
		//is separated by spaces
		
		//EXAMPLES:
		
		//The following is a k-ary tree of where k=2, height=2
		//(2 and 3 are children of 1; 4 and 5 are children of 2; 6 and 7 are children of 3):
		//  "1 2 3 4 5 6 7 "
		//Note the space at the end is allowed, but not required, this is also ok:
		//  "1 2 3 4 5 6 7"
		
		//The following is a k-ary tree of where k=3, height=2 
		//(2, 3, and 4 are children of 1; 5, 6, and 7 are children of 2; 8, 9, and 10 are children of 3):
		//  "1 2 3 4 5 6 7 8 9 10 "
		//Note the space at the end is allowed, but not required, this is also ok:
		//  "1 2 3 4 5 6 7 8 9 10"
		
		//NOTE: Any values not in the heap are not printed (no printing nulls for incomplete levels!)
		
		//HINT: Heaps are already stored in level order, you just need to return
		//the values space separated in a string!
	}
	
	public String toStringPreOrder() {
		return null;
		//prints out a pre-order walk of the tree
		
		//Examples for the k=2 and k=3 trees from toString():
		//    k=2:  "1 2 4 5 3 6 7 "
		//    k=3:  "1 2 5 6 7 3 8 9 10 4 "
		//Note the space at the end is allowed, but not required,
		//so for k=2 this is also ok: "1 2 4 5 3 6 7"
		
		//NOTE: Any values not in the heap are not printed (no printing nulls for incomplete levels!)
		
		//HINT: Think recursive helper methods and look back at Project 2!
	}
	
	public String toStringPostOrder() {
		return null;
		//prints out a post-order walk of the tree
		
		//Examples for the k=2 and k=3 trees from toString():
		//    k=2:  "4 5 2 6 7 3 1 "
		//    k=3:  "5 6 7 2 8 9 10 3 4 1 "
		//Note the space at the end is allowed, but not required,
		//so for k=2 this is also ok: "4 5 2 6 7 3 1"
		
		//NOTE: Any values not in the heap are not printed (no printing nulls for incomplete levels!)
		
		//HINT: Think recursive helper methods and look back at Project 2!
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	//********************************************************************************
	//   THIS SECTION IS EXTRA CREDIT. DON'T WASTE TIME ON THESE AT THE EXPENSE OF
	//   GETTING NORMAL CREDIT FOR THE ASSIGNMENT! If you can do these in a few
	//   minutes, great, but otherwise come back later, there are many much easier
	//   points in the assignment. DO NOT DELETE METHODS YOU DON'T COMPLETE.
	//********************************************************************************
	
	public String toStringWithLevels() {
		return null;
		//creates a string representation of the current tree with line breaks
		//after each level of the tree
		
		//Examples for the k=2 and k=3 trees from toString():
		
		//k=2:
		//  1 
		//  2 3 
		//  4 5 6 7 
		//In string form, that is: "1 \n2 3 \n4 5 6 7 "
		//(a space after each element, a \n for each new level), the space at the end is optional
		
		//k=3:
		//  1 
		//  2 3 4 
		//  5 6 7 8 9 10 
		//In string form, that is: "1 \n2 3 4 \n5 6 7 8 9 10 "
		//(a space after each element, a \n for each new level), the space at the end is optional
		
		//NOTE: Any values not in the heap are not printed (no printing nulls for incomplete levels!)
		
		//HINT 1: Again, heaps are already in level order, so for this you just need to determine
		//when you're at a new level and add a line break.
		
		//Hint 2: If you know how to get the height of a nearly complete tree of
		//a given size... you can find when items are on the next "level"
		//in the same way in O(1) time.
	}
	
	//********************************************************************************
	// Testing code... edit this as much as you want!
	//********************************************************************************
	
	public static void main(String[] arg) {
		//maybe add some yays?
		
		
		//When you think you're ready for testing, uncomment this block. It's a
		//secondary check to make sure your code compiles with the test cases used
		//for grading.
		
		/*
		class Banana { }
		
		int x;
		String s;
		boolean b;
		SimpleKTree<Integer> tree1;
		SimpleKTree<Banana> tree2;
		
		tree1 = new SimpleKTree<>(new Integer[] {1, 2, 3}, 3);
		tree2 = new SimpleKTree<>(100);
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
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	//********************************************************************************
	
	public static class InvalidKException extends RuntimeException {};
	
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}
	
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}
	
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}
	
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}
	
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}
	
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}
	
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}