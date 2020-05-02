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
	private ArrayList<E> nodeList;

	/**
	 * Number of max child per parent node = K.
	 */
	private int maxK;

	//Notes: for this assignment, you may assume that the tree is always nearly complete
	//this will significantly simplify both your size() and height() methods
	
	public SimpleKTree(int k) {
		//creates an empty k-ary tree
		//throws InvalidKException (you need to create this) if the k value is invalid (< 2)
		if (k<2){
			throw new InvalidKException();
		}
		
		maxK = k;
		nodeList = new ArrayList<E>();
	}

	public SimpleKTree(E[] arrayTree, int k) {
		// creates a k-ary tree from an array (which is already in k-ary tree array
		// representation)
		// throws InvalidKException (you need to create this) if the k value is invalid
		// (< 2)

		// NOTE: You may assume null values are not allowed in the tree, so any elements
		// in the array are real elements and the array will not contain more space than
		// is needed for the nearly complete tree (so no nulls appearing anywhere).

		if (k<2){
			throw new InvalidKException();
		}

		maxK = k;
		nodeList = new ArrayList<E>();
		for (int i = 0; i < arrayTree.length; i++){
			nodeList.add(arrayTree[i]);
		}

	}
	
	public int height() {
		//returns the height of the k-ary tree
		//can be done in O(1) time... use math!
		//worst case for this assignment: O(log-base-k(n))

		return height(nodeList.size());
	}
	
	private int height(int n) {
		//returns the height of the k-ary tree
		//can be done in O(1) time... use math!
		//worst case for this assignment: O(log-base-k(n))

		if (n == 0) {
			return 0;
		}

		double maxHeight = (Math.log((n*maxK)-n+1)/Math.log(maxK));
		return (int) Math.ceil(maxHeight)-1;
	}

	public void clear() {
		//removes all the elements from the k-ary tree
		nodeList.clear();
	}
	
	public String toString() {
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

		String strLevel = "";
		if (!isEmpty()) {
			for (int i = 0; i < size()-1; i++) {
				strLevel = strLevel + nodeList.get(i) + " ";
			}
			strLevel = strLevel + nodeList.get(size()-1);
		}
		return strLevel;
	}
	
	public String toStringPreOrder() {
		//prints out a pre-order walk of the tree
		
		//Examples for the k=2 and k=3 trees from toString():
		//    k=2:  "1 2 4 5 3 6 7 "
		//    k=3:  "1 2 5 6 7 3 8 9 10 4 "
		//Note the space at the end is allowed, but not required,
		//so for k=2 this is also ok: "1 2 4 5 3 6 7"
		
		//NOTE: Any values not in the heap are not printed (no printing nulls for incomplete levels!)
		
		//HINT: Think recursive helper methods and look back at Project 2!

		// i (i*k)+1 ((i*k)+1)+1 ((i*k)+1)+2 ... ((i*k)+1)+k

		String strLevel = "";
		if (!isEmpty()) {
			strLevel = toPreOrderString(0);
		}
		return strLevel;
	}
	

	private String toPreOrderString(int index){
		String s = nodeList.get(index).toString();

		int n = index*maxK;
		for (int i = 1; i <= maxK && n+i < size(); i++) {
			s = s + " " + toPreOrderString(n+i);
		}
		return s;
	}

	public String toStringPostOrder() {
		//prints out a post-order walk of the tree
		
		//Examples for the k=2 and k=3 trees from toString():
		//    k=2:  "4 5 2 6 7 3 1 "
		//    k=3:  "5 6 7 2 8 9 10 3 4 1 "
		//Note the space at the end is allowed, but not required,
		//so for k=2 this is also ok: "4 5 2 6 7 3 1"
		
		//NOTE: Any values not in the heap are not printed (no printing nulls for incomplete levels!)
		
		//HINT: Think recursive helper methods and look back at Project 2!

		String strLevel = "";
		if (!isEmpty()) {
			strLevel = toPostOrderString(0);
		}
		return strLevel;

	}

	private String toPostOrderString(int index){
		String s = "";

		int n = index*maxK;
		if (nodeList.get(index) == null){
			return null;
		}
		for (int i = 1; i <= maxK && n+i < size(); i++) {
			s += toPostOrderString(n+i) + " ";
		}
		s += nodeList.get(index).toString();
		return s;
	}
	
	private String toPostOrderString2(int index){
		String s = nodeList.get(index).toString();

		int n = index*maxK;
		if (nodeList.get(index) == null){
			return null;
		}
		for (int i = maxK; i >= 1; i--) {
			if ((n+i) < size()){
				s = toPostOrderString2(n+i) + " " + s;
			}
			
		}
		return s;
	}


	public int size() {
		return nodeList.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	//********************************************************************************
	//   THIS SECTION IS EXTRA CREDIT. DON'T WASTE TIME ON THESE AT THE EXPENSE OF
	//   GETTING NORMAL CREDIT FOR THE ASSIGNMENT! If you can do these in a few
	//   minutes, great, but otherwise come back later, there are many much easier
	//   points in the assignment. DO NOT DELETE METHODS YOU DON'T COMPLETE.
	//********************************************************************************
	
	public String toStringWithLevels() {
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

		String strLevel = "";
		if (!isEmpty()) {
			int prevHeight = 0;
			int currHeight = 0;
			for (int i = 0; i < size()-1; i++) {
				currHeight = height(i+1);
				if (prevHeight < currHeight){
					prevHeight = currHeight;
					strLevel += "\n" + nodeList.get(i) + " ";
				}
				else{
					strLevel += nodeList.get(i) + " ";
				}				
			}
			currHeight = height();
			if (prevHeight < currHeight){
				prevHeight = currHeight;
				strLevel += "\n" + nodeList.get(size()-1);
			}
			else{
				strLevel += nodeList.get(size()-1);
			}				
		}
		return strLevel;

	}
	
	//********************************************************************************
	// Testing code... edit this as much as you want!
	//********************************************************************************
	
	public static void main(String[] arg) {
		//maybe add some yays?
		//Construct SimpleK Object and test inputs.

		SimpleKTree<Integer> tree1;

		tree1 = new SimpleKTree<>(2);
		System.out.println("Yay 1");

		try {
			//tree1 = new SimpleKTree<>(1);
			System.out.println("Nay 1");
		} catch (InvalidKException e) {
			System.out.println("Yay 2");
		}
		
		//When you think you're ready for testing, uncomment this block. It's a
		//secondary check to make sure your code compiles with the test cases used
		//for grading.
		
		class Banana { }
		
		int x;
		String s;
		boolean b;
		//SimpleKTree<Integer> tree1;
		SimpleKTree<Banana> tree2;
		
		tree1 = new SimpleKTree<>(new Integer[] {1, 2, 3}, 3);
		tree2 = new SimpleKTree<>(100);

		if ((tree1.maxK == 3) && (tree1.size() == 3) && (tree1.height() == 1)){
			System.out.println("Yay 3");
		}

		x = tree1.height();
		//tree1.clear();
		s = tree1.toString();
		System.out.println(s);
		if ((tree1.maxK == 3) && (tree1.size() == 3) && (tree1.toString() == "1 2 3")){
			System.out.println("Yay 3b");
		}
		s = tree1.toStringPreOrder();
		if ( s == "1 2 3"){
			System.out.println("Yay 3c");
		}
		s = tree1.toStringPostOrder();
		s = tree1.toStringWithLevels();
		x = tree1.size();
		b = tree1.isEmpty();

		SimpleKTree<Integer> tree3;
		tree3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5, 6, 7}, 2);
		if ((tree3.maxK == 2) && (tree3.size() == 7) && (tree3.height() == 2)){
			System.out.println("Yay 4");
		}

		tree3 = new SimpleKTree<>(new Integer[] {}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 0) && (tree3.height() == 0)){
			System.out.println("Yay 4a = s=0, h=0");
		}
		
		tree3 = new SimpleKTree<>(new Integer[] {1}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 1) && (tree3.height() == 0)){
			System.out.println("Yay 4b = s=1, h=0");
		}

		tree3 = new SimpleKTree<>(new Integer[] {1,  2}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 2) && (tree3.height() == 1)){
			System.out.println("Yay 4c = s=2, h=1");
		}

		tree3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 4) && (tree3.height() == 1)){
			System.out.println("Yay 4d = s=4, h=1");
		}

		tree3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 5) && (tree3.height() == 2)){
			System.out.println("Yay 4e = s=5, h=2");
		}

		tree3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 13) && (tree3.height() == 2)){
			System.out.println("Yay 4f = s=13, h=2");
		}

		tree3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, 3);
		if ((tree3.maxK == 3) && (tree3.size() == 14) && (tree3.height() == 3)){
			System.out.println("Yay 4g = s=14, h=3");
		}

		tree3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}, 2);

		if ((tree3.maxK == 2) && (tree3.height() == 3) && !tree3.isEmpty() ){
			System.out.println("Yay 5");
		}
		System.out.println("#" + tree3.toString() + "#");

		tree3.clear();
		System.out.println("#" + tree3.toString() + "#");
		if ((tree3.maxK == 2) && tree3.size() == 0 && (tree3.height() == 0) && tree3.isEmpty() && tree3.toString() == "" ) {
			System.out.println("Yay 6");
		}

		SimpleKTree<Integer> treek2 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5, 6, 7}, 2);
		SimpleKTree<Integer> treek3 = new SimpleKTree<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 3);

		String k2walk = treek2.toString();
		String k3walk = treek3.toString();

		System.out.println("k2walk.toString(): " + k2walk);
		System.out.println("k3walk.toString(): " + k3walk);

		if (k2walk.equals("1 2 3 4 5 6 7") && k3walk.equals("1 2 3 4 5 6 7 8 9 10") ) {
			System.out.println("Yay 7");
		}

		k2walk = treek2.toStringPreOrder();
		k3walk = treek3.toStringPreOrder();

		System.out.println("k2walk.toStringPreOrder(): " + k2walk);
		System.out.println("k3walk.toStringPreOrder(): " + k3walk);
		
		if (k2walk.equals("1 2 4 5 3 6 7") && k3walk.equals("1 2 5 6 7 3 8 9 10 4") ) {
			System.out.println("Yay 8");
		}

		k2walk = treek2.toStringPostOrder();
		k3walk = treek3.toStringPostOrder();

		System.out.println("k2walk.toStringPostOrder(): " + k2walk);
		System.out.println("k3walk.toStringPostOrder(): " + k3walk);
		
		if (k2walk.equals("4 5 2 6 7 3 1") && k3walk.equals("5 6 7 2 8 9 10 3 4 1") ) {
			System.out.println("Yay 9");
		}

		k2walk = treek2.toStringWithLevels();
		k3walk = treek3.toStringWithLevels();

		System.out.println("k2walk.toStringWithLevels(): " + k2walk);
		System.out.println("k3walk.toStringWithLevels(): " + k3walk);
		
		if (k2walk.equals("1 \n2 3 \n4 5 6 7") && k3walk.equals("1 \n2 3 4 \n5 6 7 8 9 10") ) {
			System.out.println("Yay 10");
		}
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