package Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaComparisons {
    public static void main(String[] args) {
        // Define a record "Person" with two fields: firstName and lastName.
        record Person(String firstName, String lastName) {}

// Create a list of Person records.
        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")
        ));

// Sort the list by lastName using a lambda expression.
        list.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        list.forEach(System.out::println); // Output: Mickey Mouse, Minnie Mouse, Peter Pan, Peter PumpkinEater

        System.out.println("------------------------------------");

// Sort the list by lastName using Comparator.comparing() method.
        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println); // Output: Mickey Mouse, Minnie Mouse, Peter Pan, Peter PumpkinEater
        
        System.out.println("------------------------------------");

// Sort the list by lastName, then by firstName using Comparator.comparing() and thenComparing() methods.
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName));
        list.forEach(System.out::println); // Output: Mickey Mouse, Minnie Mouse, Peter Pan, Peter PumpkinEater

        System.out.println("------------------------------------");

// Sort the list by lastName (ascending), then by firstName (ascending), and reverse the order using reversed().
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName).reversed());
        list.forEach(System.out::println); // Output: Peter PumpkinEater, Peter Pan, Minnie Mouse, Mickey Mouse

    }
}
