import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.PriorityQueue;


public class PriorityQueueTest {
    
    public static void main(String[] arg) {
        //maybe add some yays?
        
        
        //When you think you're ready for testing, uncomment this block. It's a
        //secondary check to make sure your code compiles with the test cases used
        //for grading.
        

        class Banana { int size; }
        
        int x, x1, x2;
        String s;
        boolean b;
        Banana a;
        PriorityQueue<Banana> tree1;
        PriorityQueue<Integer> tree3;
        
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
            public int compare(Integer o1, Integer o2) { 
                System.out.println("intComp: o1 = "+ o1 + "; o2 = "+ o2);
                return o1.compareTo(o2); 
            }
        };
        
        tree1 = new PriorityQueue<>(comp);
        
        
        tree3 = new PriorityQueue<>(intComp);
        String k2walk = tree1.toString();
        System.out.println("k2walk.toString(): " + k2walk);
        if (tree3.isEmpty() == true && tree3.size() == 0 && k2walk.equals("[]") ) {
            System.out.println("Yay 1a");
        }

        b = tree3.add(1);

        k2walk = tree3.toString();
        System.out.println("k2walk.toString(): " + k2walk);
        if (tree3.size() == 1 && k2walk.equals("[1]") ) {
            System.out.println("Yay 1b");
        }

        b = tree3.add(3);

        k2walk = tree3.toString();
        System.out.println("k2walk.toString(): " + k2walk);
        if (tree3.size() == 2 && k2walk.equals("[1, 3]") ) {
            System.out.println("Yay 1c");
        }

        b = tree3.offer(2);

        k2walk = tree3.toString();
        System.out.println("k2walk.toString(): " + k2walk);
        if (tree3.size() == 3 && k2walk.equals("[1, 3, 2]") ) {
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

        tree3.clear();
        // 5, 6, 3, 2, 7, 1
        System.out.println("Add: 5, 6, 3, 2, 7, 1");
        System.out.println(tree3);
        tree3.add(5); System.out.println(tree3);
        tree3.add(6); System.out.println(tree3);
        tree3.add(3); System.out.println(tree3);
        tree3.add(2); System.out.println(tree3);
        tree3.add(7); System.out.println(tree3);
        tree3.add(1); System.out.println(tree3);

        System.out.println("Remove:");
        tree3.remove(tree3.element()); System.out.println(tree3);
        tree3.remove(tree3.element()); System.out.println(tree3);
        tree3.remove(tree3.element()); System.out.println(tree3);
        tree3.remove(tree3.element()); System.out.println(tree3);
    }

}
