public class Enum {
    public static void main(String[] args) {
        // Iterate through the values of the Topping enum
        for (Topping topping : Topping.values()) {
            System.out.println(topping.name() + " : " + topping.getPrice());

            // Check if the current topping is MUSTARD
            if (topping == Topping.MUSTARD) {
                System.out.println("It is mustard");
            }
        }

        // Accessing properties of the Suit enum
        for (Suit suit : Suit.values()) {
            System.out.println("Suit: " + suit.name() + ", Image: " + suit.getImage());
        }
    }


    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        // Custom method to get the corresponding card symbol
        public char getImage() {
            return (new char[] { 9827, 9830, 9829, 9824 })[this.ordinal()];
        }
    }

    public enum Topping {
        MUSTARD, PICKLES, BACON, CHEDDAR, TOMATO;

        // Custom method to get the price of a topping
        public double getPrice() {
            return switch (this) {
                case BACON -> 1.5;
                case CHEDDAR -> 1.0;
                default -> 0.0;
            };
        }
    }
}

