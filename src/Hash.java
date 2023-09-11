import java.util.HashSet;
import java.util.Set;

public class Hash {
    public static void main(String[] args) {
        Set<String> stringSetA = new HashSet<>();

        // Add elements to the HashSet
        stringSetA.add("Banana");
        stringSetA.add("Cherry");
        stringSetA.add("Orange");
        stringSetA.add("Pear");

        // Print the HashSet
        System.out.println(stringSetA); // Output: [Banana, Apple, Cherry]

        Set<String> stringSetB = new HashSet<>();

        // Add elements to the HashSet
        stringSetB.add("Apple");
        stringSetB.add("Cherry");
        stringSetB.add("Orange");
        stringSetB.add("Pear");

        System.out.println(stringSetB); // Output: [Banana, Apple, Cherry]


        Set<String> unionAB = new HashSet<>();
        unionAB.addAll(stringSetA);
        unionAB.addAll(stringSetB);
        printData("(A ∪ B) Union of emails (A) with phones (B)", unionAB);

        Set<String> intersectAB = new HashSet<>(stringSetA);
        intersectAB.retainAll(stringSetB);
        printData("(A ∩ B) Intersect emails (A) and phones (B)",
                intersectAB);

        Set<String> intersectBA = new HashSet<>(stringSetB);
        intersectBA.retainAll(stringSetA);
        printData("(B ∩ A) Intersect phones (B) and emails (A)",
                intersectBA);

        Set<String> AMinusB = new HashSet<>(stringSetA);
        AMinusB.removeAll(stringSetB);
        printData("(A - B) emails (A) - phones (B)",
                AMinusB);

        Set<String> BMinusA = new HashSet<>(stringSetB);
        BMinusA.removeAll(stringSetA);
        printData("(B - A) phones (B) - emails (A)",
                BMinusA);

        Set<String> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric Difference: phones and emails", symmetricDiff);

        Set<String> symmetricDiff2 = new HashSet<>(unionAB);
        symmetricDiff2.removeAll(intersectAB);
        printData("Symmetric Difference: phones and emails", symmetricDiff2);
    }
    
    public static void printData(String text, Set<String> set){
        System.out.println("----------------------------------------------");
        System.out.println(text);
        System.out.println("----------------------------------------------");
        set.forEach(System.out::println);
    }
}
