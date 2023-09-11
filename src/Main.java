public class Main {
    public static void main(String[] args) {
    /*
        var is universal variable
        var i = 5 or var s = „Something“
        beter format long numbers you can typa 5_000_000.567
        char can be typed in unicode or number or just letter/char
        String is set of chars could be typed in \u0066
     */
    // byte: 8 bits (1 byte)
    // Range: -128 to 127
        System.out.println("byte Max Value: " + Byte.MAX_VALUE);
        System.out.println("byte Min Value: " + Byte.MIN_VALUE);

    // short: 16 bits (2 bytes)
    // Range: -32,768 to 32,767
        System.out.println("short Max Value: " + Short.MAX_VALUE);
        System.out.println("short Min Value: " + Short.MIN_VALUE);

    // int: 32 bits (4 bytes)
    // Range: -2^31 to (2^31)-1
        System.out.println("int Max Value: " + Integer.MAX_VALUE);
        System.out.println("int Min Value: " + Integer.MIN_VALUE);

    // long: 64 bits (8 bytes)
    // Range: -2^63 to (2^63)-1
        System.out.println("long Max Value: " + Long.MAX_VALUE);
        System.out.println("long Min Value: " + Long.MIN_VALUE);

    // float: 32 bits (4 bytes)
    // Range: Approximately ±3.4e38 with about 7 significant digits
        System.out.println("float Max Value: " + Float.MAX_VALUE);
        System.out.println("float Min Value: " + Float.MIN_VALUE);

    // double: 64 bits (8 bytes)
    // Range: Approximately ±1.7e308 with about 15 significant digits
        System.out.println("double Max Value: " + Double.MAX_VALUE);
        System.out.println("double Min Value: " + Double.MIN_VALUE);

    // char: 16 bits (2 bytes)
    // Represents a single character (Unicode code point)
        System.out.println("char Max Value: " + (int) Character.MAX_VALUE);
        System.out.println("char Min Value: " + (int) Character.MIN_VALUE);

    // boolean: Typically 1 bit (size may vary)
    // Represents true or false values
    // Note: Memory usage can vary; it's typically optimized by the JVM.

    }
}