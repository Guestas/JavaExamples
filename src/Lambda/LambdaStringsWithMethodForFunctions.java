package Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class LambdaStringsWithMethodForFunctions {
        // Initialize a random number generator.
        private static Random random = new Random();

// Define a record "Person" with a single field "first".
        private record Person(String first) {
            // Define a method to concatenate the "first" field with a substring of the input string.
            public String last(String s) {
                return first + " " + s.substring(0, s.indexOf(" "));
            }

        }

        public static void main(String[] args) {
            // Array of names.
            String[] names = {"Anna", "Eva", "Francis"};

            // Create a Person record "tim" with the first name "Tim".
            Person tim = new Person("Tim");

            // Create a list of UnaryOperator functions.
            List<UnaryOperator<String>> list = new ArrayList<>(List.of(
                    String::toUpperCase,                            // Uppercase transformation
                    //[ANNA, EVA, FRANCIS]
                    s -> s += " " + getRandomChar('D', 'M') + ".",  // Append a random character and a dot
                    //[ANNA L., EVA D., FRANCIS I.]
                    s -> s += " " + reverse(s, 0, s.indexOf(" ")),   // Append a reversed substring
                    //[ANNA L. ANNA, EVA D. AVE, FRANCIS I. SICNARF]
                    LambdaStringsWithMethodForFunctions::reverse,   // Reverse the whole string
                    //[ANNA .L ANNA, EVA .D AVE, FRANCIS .I SICNARF]
                    String::new,                                    // Create a new empty string
                    //[ANNA .L ANNA, EVA .D AVE, FRANCIS .I SICNARF]
                    //s -> new String("Howdy " + s),               // Disabled line (not used)
                    String::valueOf,                                // Convert to String value
                    //[ANNA .L ANNA, EVA .D AVE, FRANCIS .I SICNARF]
                    tim::last,                                      // Use the "last" method of "tim" record
                    //[Tim ANNA, Tim EVA, Tim FRANCIS]
                    (new Person("MARY"))::last                      // Use the "last" method of a new "Person" record
                    //[MARY Tim, MARY Tim, MARY Tim, MARY Tim, MARY Tim, MARY Tim]
            ));

            // Apply the transformations to the names and print the results.
            applyChanges(names, list);
        }

// Apply a list of transformations to an array of strings.
        private static void applyChanges(String[] names, List<UnaryOperator<String>> stringFunctions) {
            // Create a list backed by the array of names.
            List<String> backedByArray = Arrays.asList(names);

            // Apply each transformation and print the array after each step.
            for (var function : stringFunctions) {
                backedByArray.replaceAll(s -> s.transform(function));
                System.out.println(Arrays.toString(names));
            }
        }

// Generate a random character between startChar and endChar (inclusive).
        private static char getRandomChar(char startChar, char endChar) {
            return (char) random.nextInt((int) startChar, (int) endChar + 1);
        }

// Reverse a string.
        private static String reverse(String s) {
            return reverse(s, 0, s.length());
        }

// Reverse a substring of a string.
        private static String reverse(String s, int start, int end) {
            return new StringBuilder(s.substring(start, end)).reverse().toString();
        }
}
