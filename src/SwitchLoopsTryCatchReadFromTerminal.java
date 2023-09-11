import java.util.Scanner;

public class SwitchLoopsTryCatchReadFromTerminal {
    public static void main(String[] args) {
        // old swith
        int number = 2;

        switch (number) {
            case 1:
                System.out.println("One");
                break;
            case 2:
                System.out.println("Two");
                break;
            default:
                System.out.println("Other");
                break;
        }

        // new switch
        String numberOut = switch (number) {
            case 1 -> "One";
            case 2 -> "Two";
            default -> "Other";
        };
        System.out.println(numberOut);

        for (int i = 0; i < 2; i++) {System.out.println(i);}
        int i = 0;
        while (i<2){System.out.println(i++);}
        do{
            System.out.println(i--);
        } while (i<2 && i>=0);


        String num = "n";
        try {
            Integer.parseInt(num);
        } catch (Exception e){ e.printStackTrace();}

        System.out.println("new line");

        //for inputs run in terminal java src/Main.java
        System.console().readLine("");

        // terminal is not needed
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();


    }
}
