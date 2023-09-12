package sqlApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Start {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/martince/Documents/Java/JavaExamples/database/testjava.db");

        }catch (SQLException e){
            System.out.println("Error: "+ e);
        }
    }
}
