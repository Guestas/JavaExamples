package Lambda;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaChained {
    public static void main(String[] args) {
        String name = "Tim";

// Create a function that transforms the string to uppercase.
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name)); // Output: TIM

// Create a function that appends " Buchalka" to the string and then transforms it to uppercase.
        Function<String, String> lastName = s -> s.concat(" Buchalka");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        System.out.println(uCaseLastName.apply(name)); // Output: TIM BUCHALKA

// Create a new uCaseLastName function using method composition.
        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name)); // Output: TIM BUCHALKA

// Create a function that transforms the string to uppercase, appends " Buchalka",
// and then splits it into an array of words.
        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name))); // Output: [TIM, Buchalka]

// Create a function that transforms the string to uppercase, appends " Buchalka",
// splits it into an array of words, and then rearranges the words.
        Function<String, String> f1 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name)); // Output: BUCHALKA, TIM

// Create a function that transforms the string to uppercase, appends " Buchalka",
// splits it into an array of words, joins them with a comma and space, and computes the length of the resulting string.
        Function<String, Integer> f2 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> String.join(", ", s))
                .andThen(String::length);
        System.out.println(f2.apply(name)); // Output: 13

        String[] names = {"Ann", "Bob", "Carol"};

// Create a consumer that prints the first character of a string.
        Consumer<String> s0 = s -> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;

// Iterate through the "names" array and apply the consumers to each element.
        Arrays.asList(names).forEach(s0
                .andThen(s-> System.out.print(" - "))
                .andThen(s1)); // Output: A - B - C -

        Predicate<String> p1 = s -> s.equals("TIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Tim");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

// Combine predicates with logical OR.
        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(name)); // Output: combined1 = true

// Combine predicates with logical AND.
        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(name)); // Output: combined2 = false

// Negate a combined predicate.
        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(name)); // Output: combined3 = true


    }
}
