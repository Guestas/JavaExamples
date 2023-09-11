package Lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {
    public static void main(String[] args) {
        // Lambda.Lambda with no arguments
        Runnable noArgLambda = () -> System.out.println("Lambda.Lambda with no arguments.");
        noArgLambda.run();

        // Lambda.Lambda with one argument (explicit type)
        Consumer<String> oneArgLambda = (s) -> System.out.println("Lambda.Lambda with one argument: " + s);
        oneArgLambda.accept("Hello");

        // Lambda.Lambda with one argument (type inference)
        Consumer<String> inferredTypeLambda = s -> System.out.println("Lambda.Lambda with one argument (inferred type): " + s);
        inferredTypeLambda.accept("World");

        // Lambda.Lambda with one argument using "var" (type inference)
        Consumer<String> varTypeLambda = (var s) -> System.out.println("Lambda.Lambda with one argument (var type): " + s);
        varTypeLambda.accept("Java");

        // Lambda.Lambda with two arguments (explicit types)
        MyBiConsumer<String, Integer> twoArgsLambda = (s, t) ->
                System.out.println("Lambda.Lambda with two arguments (explicit types): " + s + ", " + t);
        twoArgsLambda.accept("The answer is", 42);

        // Lambda.Lambda with two arguments using "var" (type inference)
        MyBiConsumer<String, Double> varTwoArgsLambda = (var s, var t) ->
                System.out.println("Lambda.Lambda with two arguments (var type): " + s + ", " + t);
        varTwoArgsLambda.accept("PI is approximately", 3.14159265359);

        // Lambda.Lambda with two arguments (explicit types)
        MyBiConsumer<String, List<String>> complexArgsLambda = (String s, List<String> list) -> {
            System.out.println("Lambda.Lambda with complex arguments:");
            System.out.println("String: " + s);
            System.out.println("List: " + list);
        };
        complexArgsLambda.accept("Colors", List.of("Red", "Green", "Blue"));

        System.out.println("-".repeat(5)+"Consumer"+"-".repeat(5));
// Consumer accepts a single argument and performs some operation on it.
        List<String> names = List.of("Alice", "Bob", "Charlie");
        Consumer<String> printName = name -> System.out.println("Hello, " + name);
        names.forEach(printName);

        System.out.println("-".repeat(5)+"Function"+"-".repeat(5));
// Function accepts an argument, performs a computation, and returns a result.
        Function<Integer, Integer> square = x -> x * x;
        int result = square.apply(5);
        System.out.println("Square of 5 is: " + result);

        System.out.println("-".repeat(5)+"Predicate"+"-".repeat(5));
// Predicate takes an argument and returns a boolean result.
        Predicate<Integer> isEven = x -> x % 2 == 0;
        boolean result1 = isEven.test(6);
        System.out.println("Is 6 even? " + result1);

        System.out.println("-".repeat(5)+"Supplier"+"-".repeat(5));
// Supplier takes no arguments and supplies a result.
        // Example of a Supplier that generates a random number
        Supplier<Integer> randomInteger = () -> (int) (Math.random() * 100);
        int randomNumber = randomInteger.get();
        System.out.println("Random number: " + randomNumber);
    }

    @FunctionalInterface
    interface MyBiConsumer<T, U> {
        void accept(T t, U u);
    }
}
