package Collections;

import java.util.TreeSet;

public class Trees {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(8);
        treeSet.add(1);

        System.out.println(treeSet); // Output: [1, 2, 5, 8]


        TreeSet<String> treeSet2 = new TreeSet<>();
        treeSet2.add("Apple");
        treeSet2.add("Banana");
        treeSet2.add("Cherry");
        System.out.println(treeSet2); // Output: [Apple, Cherry]

        treeSet2.remove("Banana");

        System.out.println(treeSet2); // Output: [Apple, Cherry]




        TreeSet<Student> studentSet = new TreeSet<>();

        // Add 6 Collections.Student objects to the TreeSet
        studentSet.add(new Student("John", "Doe"));
        studentSet.add(new Student("Alice", "Smith"));
        studentSet.add(new Student("Bob", "Johnson"));
        studentSet.add(new Student("Alice", "Brown"));
        studentSet.add(new Student("Charlie", "Smith"));
        studentSet.add(new Student("David", "Lee"));

        // Display the entire TreeSet
        System.out.println("Collections.Student Set: ");
        studentSet.forEach(System.out::println);

        // HeadSet: Elements less than ("Alice", "Smith")
        System.out.println("\nHeadSet (Elements before 'Alice Smith'):");
        TreeSet<Student> headSet = new TreeSet<>(studentSet.headSet(new Student("Alice", "Smith")));
        headSet.forEach(System.out::println);

        // SubSet: Elements between ("Alice", "Smith") and ("David", "Lee")
        System.out.println("\nSubSet (Elements between 'Alice Brown' and 'David Lee'):");
        TreeSet<Student> subSet = new TreeSet<>(studentSet.subSet(new Student("Alice", "Brown"), new Student("David", "Lee")));
        subSet.forEach(System.out::println);

        // TailSet: Elements greater than or equal to ("David", "Lee")
        System.out.println("\nTailSet (Elements after or equal to 'David Lee'):");
        TreeSet<Student> tailSet = new TreeSet<>(studentSet.tailSet(new Student("David", "Lee")));
        tailSet.forEach(System.out::println);
    }
}


class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Collections.Student{ firstName=' %s', lastName=' %s'}'", firstName, lastName);
    }

    @Override
    public int compareTo(Student o) {
        return lastName.compareTo(o.getLastName())==0?this.firstName.compareTo(o.getFirstName()):lastName.compareTo(o.getLastName());
    }

}