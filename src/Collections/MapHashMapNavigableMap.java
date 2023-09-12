package Collections;

import java.util.*;

public class MapHashMapNavigableMap {
    public static void main(String[] args) {
        // Create a map to store students by their student ID (assuming student IDs are unique)
        Map<Integer, Student2> studentMap = new HashMap<>();

        // Add students to the map
        Student2 student1 = new Student2(1, "Alice", "Smith");
        Student2 student2 = new Student2(2, "Bob", "Johnson");
        Student2 student3 = new Student2(3, "Charlie", "Brown");


        studentMap.put(student1.getStudentId(), student1);
        studentMap.put(student2.getStudentId(), student2);
        studentMap.put(student3.getStudentId(), student3);


        // Try to add a student with an existing ID
        Student2 newStudent = new Student2(2, "Eve", "Taylor");

        Student2 existingStudent = studentMap.putIfAbsent(newStudent.getStudentId(), newStudent);

        if (existingStudent == null) {
            System.out.println("New student added.");
        } else {
            System.out.println("Student with ID " + newStudent.getStudentId() + " already exists.");
        }

        // Retrieve a student by student ID
        int studentIdToFind = 2;
        Student2 foundStudent = studentMap.get(studentIdToFind);
        System.out.printf("%s%n",
                foundStudent!=null?"Found Student: " + foundStudent:
                        "Student not found with ID: " + studentIdToFind);



        // Iterate through the students in the map
        System.out.println("\nAll Students:");
        studentMap.forEach((k, v)-> System.out.printf("key: %d value:%s%n", k,v));


        //Get key values for checking if value already exist
        Set<Integer> keysView = studentMap.keySet();
        System.out.println("\nKey values: "+ keysView);

        //get only some IDs
        keysView.retainAll(List.of(1,3));
        studentMap.forEach((k, v)-> System.out.printf("key: %d value:%s%n", k,v));

        //get only some IDs
        keysView.remove(1);
        studentMap.forEach((k, v)-> System.out.printf("key: %d value:%s%n", k,v));


        // Create a NavigableMap with TreeMap
        NavigableMap<Integer, Student2> studentMaps = new TreeMap<>();

        // Add students to the map
        Student2 student4 = new Student2(4, "David", "Lee");
        Student2 student5 = new Student2(5, "Eve", "Taylor");

        studentMaps.put(student1.getStudentId(), student1);
        studentMaps.put(student2.getStudentId(), student2);
        studentMaps.put(student3.getStudentId(), student3);
        studentMaps.put(student4.getStudentId(), student4);
        studentMaps.put(student5.getStudentId(), student5);
        // Print the entire map
        System.out.println("NavigableMap: " + studentMaps);

        // Find the ceiling entry (smallest entry greater than or equal to a given key)
        System.out.println("Ceiling entry for key 2: " + studentMaps.ceilingEntry(2));

        // Find the floor entry (largest entry less than or equal to a given key)
        System.out.println("Floor entry for key 2: " + studentMaps.floorEntry(2));

        // Find the higher entry (strictly greater than a given key)
        System.out.println("Higher entry for key 3: " + studentMaps.higherEntry(3));

        // Find the lower entry (strictly less than a given key)
        System.out.println("Lower entry for key 3: " + studentMaps.lowerEntry(3));

        // Get a submap from key 2 (inclusive) to key 4 (exclusive)
        NavigableMap<Integer, Student2> subMap = studentMaps.subMap(2, true, 4, false);
        System.out.println("Submap from key 2 to key 4: " + subMap);



        enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}
        List<WeekDay> annsWorkDays = new ArrayList<>(List.of(WeekDay.MONDAY,
                WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));

        var annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
        annsDaysSet.forEach(System.out::println);

        var allDaysSet = EnumSet.allOf(WeekDay.class);
        System.out.println("---------------------");
        allDaysSet.forEach(System.out::println);

        Set<WeekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
        System.out.println("---------------------");
        newPersonDays.forEach(System.out::println);

        Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsDaysSet);
        System.out.println("---------------------");
        anotherWay.forEach(System.out::println);

        Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
        System.out.println("---------------------");
        businessDays.forEach(System.out::println);

        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);

        employeeMap.put(WeekDay.FRIDAY, new String[]{"Ann", "Mary", "Bob"});
        employeeMap.put(WeekDay.MONDAY, new String[]{"Mary", "Bob"});
        employeeMap.forEach(
                (k, v) -> System.out.println(k + " : " + Arrays.toString(v)));
    }
}

class Student2 {
    private int studentId;
    private String firstName;
    private String lastName;

    public Student2(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        System.out.printf("%s already in map", obj);
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        System.out.printf("%s already in map %s", this.studentId, this.firstName);
        return Objects.hash(studentId);
    }
}