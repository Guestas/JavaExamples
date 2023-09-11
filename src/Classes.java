public class Classes {
    public static void main(String[] args) {
        // Create a "Person" object
        Person person = new Person("Alice", 30);

        // Call the "introduce" method of the "Person" class
        person.introduce();

        // Create an "EmployeeImpl" object
        Employee employee = new EmployeeImpl("Bob", "Software Engineer");

        // Call the "work" method of the "Employee" interface
        employee.work();

        // Create an "Address" object (nested class)
        Person.Address address = person.new Address("123 Main St", "Cityville");

        // Call the "displayAddress" method of the "Address" class (nested class)
        address.displayAddress();
    }
}

// Define a normal class called "Person."
class Person {
    // Instance variables
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Normal method
    public void introduce() {
        System.out.println("Hello, I'm " + name + " and I'm " + age + " years old.");
    }

    // Inner class called "Address" (Nested Class)
    class Address {
        private String street;
        private String city;

        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        public void displayAddress() {
            System.out.println("Address: " + street + ", " + city);
        }
    }
}

// Define an interface called "Employee."
interface Employee {
    // Abstract method to be implemented by classes implementing this interface
    void work();

    // Default method (Java 8+ feature)
    default void displayJob() {
        System.out.println("I am an employee.");
    }
}

// Create a class called "EmployeeImpl" that implements the "Employee" interface.
class EmployeeImpl implements Employee {
    private String name;
    private String jobTitle;

    public EmployeeImpl(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }

    // Implement the "work" method from the "Employee" interface.
    @Override
    public void work() {
        System.out.println(name + " is working as a " + jobTitle);
    }
}
