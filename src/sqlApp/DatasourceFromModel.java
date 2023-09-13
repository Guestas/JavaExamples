package sqlApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatasourceFromModel {
    public static final String DB_NAME = "testdata.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/martince/Documents/Java/JavaExamples/database/"+DB_NAME;
    public static final String TABLE_CARS = "cars";
    public static final String COLUM_BRAND = "brand";
    public static final String COLUM_KMS = "kms";
    public static final String TABLE_USERS = "users";
    public static final String COLUM_NAME = "name";
    public static final String COLUM_CAR = "car";
    public static final String COLUM_PHONE = "phone";
    public static final String VIEW_CARS = "viewCars";
    private Connection conn;

    private PreparedStatement insertUser;
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection: " + e.getMessage());
        }
    }

    public void createTables() {
        try (Statement statement = conn.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS "+TABLE_CARS);
            statement.execute("DROP TABLE IF EXISTS "+TABLE_USERS);
            statement.execute("CREATE TABLE IF NOT EXISTS %s (`_id` INTEGER PRIMARY KEY, %s text, %s integer) ".formatted(TABLE_CARS, COLUM_BRAND, COLUM_KMS));
            statement.execute("CREATE TABLE IF NOT EXISTS %s (`_id` INTEGER PRIMARY KEY, %s text, %s integer, %s integer) ".formatted(TABLE_USERS, COLUM_NAME, COLUM_PHONE, COLUM_CAR));

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
/*
    public static void insertUser(String name, int phone, int car) throws SQLException {
       try {
            ResultSet r = state.executeQuery("SELECT COUNT(*) FROM %s WHERE %s=%d".formatted(TABLE_CARS, "_id", car));
            System.out.println(r.getInt(1));
            if (r.getInt(1)!=0){
                try (Statement statement = conn.createStatement();
                     ResultSet results = statement.executeQuery(sb.toString())) {

                    List<String> albums = new ArrayList<>();
                    while (results.next()) {
                        albums.add(results.getString(1));
                    }

                    return albums;

                } catch (SQLException e) {
                    System.out.println("Query failed: " + e.getMessage());
                    return null;
                }
                state.execute("INSERT INTO %s (%s, %s, %s) VALUES ('%s',%d, %d)"
                        .formatted(TABLE_USERS, COLUM_NAME, COLUM_PHONE, COLUM_CAR, name, phone, car));
            }
        }catch (SQLException e){
           System.out.println("Error: "+ e);
           e.printStackTrace();
       }
    }

    public static void insertCar(Statement state, String brand, int kms) throws SQLException {
        try {
            state.execute("INSERT INTO %s (%s, %s) VALUES ('%s',%d)"
                    .formatted(TABLE_CARS, COLUM_BRAND, COLUM_KMS, brand, kms));
        }catch (SQLException e){
            System.out.println("Error: "+ e);
            e.printStackTrace();
        }
    }*/
}
