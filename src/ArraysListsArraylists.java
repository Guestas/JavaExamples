import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class ArraysListsArraylists {
    public static void main(String[] args) {
        // Create a new ArrayList of Strings.
        ArrayList<String> newL = new ArrayList<>();

        // Add an element to the ArrayList.
        newL.add("something");
        System.out.println(newL.get(0)); // Output: something

        // Create a new List and add its elements to the ArrayList.
        List<String> newList = List.of("A", "D");
        newL.addAll(newList);

        // Add elements from an array to the ArrayList.
        newL.addAll(Arrays.asList("b", "c"));

        // Check if the ArrayList contains "A".
        boolean containsA = newL.contains("A");
        System.out.println(containsA); // Output: true

        // Set the first element to "a".
        newL.set(0, "a");

        // Remove the element at index 0.
        newL.remove(0);

        // Find the index of "a".
        int indexOfA = newL.indexOf("a");
        System.out.println("Index of 'a': " + indexOfA); // Output: Index of 'a': 0

        // Find the last index of "B" (not present in the list).
        int lastIndexOfB = newL.lastIndexOf("B");
        System.out.println("Last Index of 'B': " + lastIndexOfB); // Output: Last Index of 'B': -1

        // Sort the ArrayList in reverse order.
        newL.sort(Comparator.reverseOrder());

        // Print the final contents of the ArrayList.
        System.out.println(newL); // Output: [c, b, D, A]



        // Create a LinkedList of Strings.
        LinkedList<String> list = new LinkedList<>();
        var placesToVisit1 = new LinkedList<String>();

        // Add elements to the LinkedList.
        list.addFirst("Darwin");
        list.addLast("Hobart");

        // Queue methods
        list.offer("Melbourne");
        list.offerFirst("Brisbane");
        list.offerLast("Toowoomba");

        // Stack Methods
        list.push("Alice Springs");

        // Remove elements by index or value.
        list.remove(4); // Removes element at index 4
        list.remove("Brisbane"); // Removes element with value "Brisbane"

        System.out.println(list); // Output: [Brisbane, Hobart, Darwin, Melbourne, Toowoomba]

        // Remove and print the first element.
        String s1 = list.remove();
        System.out.println(s1 + " was removed");

        // Remove and print the first element.
        String s2 = list.removeFirst();
        System.out.println(s2 + " was removed");

        // Remove and print the last element.
        String s3 = list.removeLast();
        System.out.println(s3 + " was removed");

        // Queue/Deque poll methods
        String p1 = list.poll(); // Removes and prints the first element
        System.out.println(p1 + " was removed");

        String p2 = list.pollFirst(); // Removes and prints the first element
        System.out.println(p2 + " was removed");

        String p3 = list.pollLast(); // Removes and prints the last element
        System.out.println(p3 + " was removed");

        // Push elements onto the stack.
        list.push("Sydney");
        list.push("Brisbane");
        list.push("Canberra");
        System.out.println(list);

        // Pop and print the top element of the stack.
        String p4 = list.pop();
        System.out.println(p4 + " was removed");

        // Use a ListIterator to add an element after a specific value.
        var iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("Brisbane")) {
                iterator.add("Lake Wivenhoe");
            }
        }

        // Print elements in reverse order using the ListIterator.
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        System.out.println(list);

        // Create a ListIterator starting from index 3.
        var iterator2 = list.listIterator(3);
        System.out.println(iterator2.previous());



    }
}
