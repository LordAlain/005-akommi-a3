import java.util.Scanner;
import java.util.Comparator;

//You _must_ submit this class with your assignment for full credit. You may not
//edit any of the provided code (except to add JavaDocs).
/**
 * The testing class that uses Priority tree to perform heapsort.
 */
public class PA3 {
	
	/**
	 * Uses a Priority Tree to perform heapify then removes for a proper heapsort.
	 * @param <T> handles any object arrays.
	 * @param values the values that need to be sorted.
	 * @param comp the comparison type for the objects.
	 */
	public static <T> void heapsort(T[] values, Comparator<T> comp) {
		//Your code here... you may add everything to a heap and then remove each item
		//or you may use the "heapify constructor", and then remove everything, whichever
		//sounds more fun to you. Note that your code should modify the original array
		//of values.
		
		//Again, if you are unfamiliar with Comparators, see:
		//https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
		
		//Note: Sorting in any way other than with a heap will result in a 0
		//for this method... you have been warned! This can be done in 3 lines
		//of code, but is more readable in 6-8.

		PriorityTree<T> tree = new PriorityTree<>(comp, values, 2);
		for (int i = 0; i < values.length; i++) {
			values[i] = tree.remove();
		}
	}
	
	
	//********************************************************************************
	// Testing code is pretty much complete... edit this if you want (you may
	// specifically be interested in editing the commented out line lower down).
	//********************************************************************************
	
	/**
	 * Main method for testing code.
	 * @param arg no args.
	 */
	public static void main(String[] arg) {
		Comparator<Integer> revComp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};
		
		Comparator<Integer> compToUse = null;
		String usage = "Usage: java PA3 {min|max}";
		if(arg.length == 1) {
			if(arg[0].equals("max")) {
				compToUse = revComp;
			}
			else if(arg[0].equals("min")) {
				compToUse = new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						return o1.compareTo(o2);
					}
				};
			}
			else {
				System.out.println(usage);
				return;
			}
		}
		else {
			System.out.println(usage);
			return;
		}
		
		//we _will_ test your priority trees with values other than 3
		//you may want to as well...
		PriorityTree<Integer> intTree = new PriorityTree<>(compToUse, 3);
		Scanner keyboard = null;
		try {
			keyboard = new Scanner(System.in);
		}
		catch(Exception e) {
			System.out.println("Error connecting to keyboard input:\n"+e.toString());
			return;
		}
		
		int choice = 4;
		String choiceString = "";
		do {
			System.out.print("\n1. Enqueue a number\n"
				+"2. Dequeue a number\n"
				+"3. Sort a list of numbers\n"
				+"4. Exit\n\n");
				
			choice = forceIntChoice(keyboard, "Enter your choice: ", 1, 4);
			
			switch(choice) {
				case 1:
					int numToAdd = forceIntChoice(keyboard, "Enter a number to add: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
					intTree.add(numToAdd);
					break;
				case 2:
					System.out.println("Removed: " + intTree.poll());
					break;
				case 3:
					System.out.print("Enter numbers to sort, separated by spaces: ");
					try {
						String numberString = keyboard.nextLine();
						String[] valueStrings = numberString.split(" ");
						Integer[] values = new Integer[valueStrings.length];
						for(int i = 0; i < valueStrings.length; i++) {
							values[i] = Integer.parseInt(valueStrings[i]);
						}
						PA3.heapsort(values, compToUse);
						System.out.print("Sorted: ");
						for(Integer value : values) System.out.print(value + " ");
						System.out.println();
					}
					catch(Exception e) {
						System.out.println("Error: one or more values was not a valid integer.");
						System.out.println(e.toString());
						e.printStackTrace();
					}
					break;
			}
			
			if(choice < 3) {
				System.out.println("\nCurrent tree (height " + intTree.height() + ")");
				System.out.println("Level order walk: " + intTree.toString());
				System.out.println("Pre order walk:   " + intTree.toStringPreOrder());
				System.out.println("Post order walk:  " + intTree.toStringPostOrder());
				System.out.println("Level Order with Level Breaks:\n" + intTree.toStringWithLevels());
			}
		}
		while(choice != 4);
	}
	
	/**
	 * Forces user to input a valid integer input within a certain range.
	 * @param input the original input from the user.
	 * @param prompt the prompt for the user.
	 * @param min the min value of the choice.
	 * @param max the max value of the choice.
	 * @return choice - the user input within the bounds.
	 */
	private static int forceIntChoice(Scanner input, String prompt, int min, int max) {
		int choice = -1;
		while(choice == -1) {
			try {
				System.out.print(prompt);
				choice = Integer.parseInt(input.nextLine());
				if(choice >= min && choice <= max) {
					return choice;
				}
				System.out.println("You must enter an integer between "+min+" and "+max+".");
			}
			catch(RuntimeException e) { }
			System.out.println("You must enter a valid integer.");
		}
		return choice;
	}
}