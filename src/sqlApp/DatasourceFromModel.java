package sqlApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DatasourceFromModel {
    public static final String DB_NAME = "testdata.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/martince/Documents/Java/JavaExamples/database/"+DB_NAME;
    public static final String TABLE_CARS = "cars";
    public static final String COLUM_CAR_ID = "_id";
    public static final String COLUM_CAR_BRAND = "brand";
    public static final String COLUM_CAR_KMS = "kms";

    public static final String TABLE_USERS = "users";
    public static final String COLUM_USER_ID = "_id";
    public static final String COLUM_USER_NAME = "name";
    public static final String COLUM_USER_CAR = "car";
    public static final String COLUM_USER_PHONE = "phone";
    public static final String VIEW_CARS = "viewCars";
    private Connection conn;

    public static final String INSERT_USER = "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)"
            .formatted(TABLE_USERS, COLUM_USER_NAME, COLUM_USER_PHONE, COLUM_USER_CAR);
    public static final String INSERT_CAR= "INSERT INTO %s (%s, %s) VALUES (?,?)"
            .formatted(TABLE_CARS, COLUM_CAR_BRAND, COLUM_CAR_KMS);
    public static final String QUERY_USER = "SELECT %s FROM %s WHERE %s = ?"
            .formatted(COLUM_USER_ID, TABLE_USERS, COLUM_USER_NAME);
    public static final String QUERY_CAR = "SELECT %s FROM %s WHERE %s = ?"
            .formatted(COLUM_CAR_ID, TABLE_CARS, COLUM_CAR_BRAND);

    public static final String VIEV_FOR_CARS = "CREATE VIEW IF NOT EXISTS '%s' AS SELECT %s.%s, COUNT(%s.%s) AS 'rented' FROM %s INNER JOIN %s WHERE %s.%s=%s.%s GROUP BY %s.%s"
        .formatted(VIEW_CARS,TABLE_CARS, COLUM_CAR_BRAND, TABLE_USERS, COLUM_USER_CAR, TABLE_USERS, TABLE_CARS, TABLE_USERS, COLUM_USER_CAR, TABLE_CARS, COLUM_CAR_ID, TABLE_USERS, COLUM_USER_CAR);

    public static final String QUERY_UPDATE_CARS = "UPDATE %s SET %s=? WHERE %s=?".formatted(TABLE_CARS, COLUM_CAR_KMS, COLUM_CAR_BRAND);


    private PreparedStatement insertUser;
    private PreparedStatement insertCar;

    private PreparedStatement queryCar;
    private PreparedStatement queryUser;
    private PreparedStatement queryUpdateCar;


    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertUser = conn.prepareStatement(INSERT_USER);
            insertCar = conn.prepareStatement(INSERT_CAR);
            queryCar = conn.prepareStatement(QUERY_CAR);
            queryUser = conn.prepareStatement(QUERY_USER);
            queryUpdateCar = conn.prepareStatement(QUERY_UPDATE_CARS);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(insertUser != null) {
                insertUser.close();
            }
            if(insertCar != null) {
                insertCar.close();
            }
            if(queryCar != null) {
                queryCar.close();
            }
            if(queryUser != null) {
                queryUser.close();
            }
            if(queryUpdateCar != null) {
                queryUpdateCar.close();
            }
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
            statement.execute("DROP VIEW IF EXISTS "+VIEW_CARS);

            statement.execute("CREATE TABLE IF NOT EXISTS %s (`%s` INTEGER PRIMARY KEY, %s text, %s integer) ".formatted(TABLE_CARS, COLUM_CAR_ID, COLUM_CAR_BRAND, COLUM_CAR_KMS));
            statement.execute("CREATE TABLE IF NOT EXISTS %s (`%s` INTEGER PRIMARY KEY, %s text, %s integer, %s integer) ".formatted(TABLE_USERS, COLUM_USER_ID, COLUM_USER_NAME, COLUM_USER_PHONE, COLUM_USER_CAR));

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void insertUser(String name, int phone, int car) throws SQLException {
        try {
           queryUser.setString(1, name);
           ResultSet result = queryUser.executeQuery();
           conn.setAutoCommit(false);
           if (result.next()){
               System.out.println(result.getInt(1));
           }else{
               insertUser.setString(1, name);
               insertUser.setInt(2, phone);
               insertUser.setInt(3, car);

               int affectedRows = insertUser.executeUpdate();
               if(affectedRows != 1) {
                   throw new SQLException("Couldn't insert user!");
               }

               Statement statement = conn.createStatement();
               ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
               if (generatedKeys.next()) {
                   System.out.println("User added with ID: " + generatedKeys.getInt(1)); // Retrieve the generated user ID
                   conn.commit();
               } else {
                   throw new SQLException("Couldn't get user ID");
               }

           }


        }catch (SQLException e){
           System.out.println("Error: "+ e);
           //e.printStackTrace();
       }
    }

    public void insertCar(String brand, int kms) throws SQLException {
        try {
            queryCar.setString(1, brand);
            ResultSet result = queryCar.executeQuery();
            conn.setAutoCommit(false);
            if (result.next()){
                System.out.println(result.getInt(1));
            }else{
                insertCar.setString(1, brand);
                insertCar.setInt(2, kms);

                int affectedRows = insertCar.executeUpdate();
                if(affectedRows != 1) {
                    throw new SQLException("Couldn't insert Car!");
                }

                Statement statement = conn.createStatement();
                ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
                if (generatedKeys.next()) {
                    System.out.println("Car added with ID: " + generatedKeys.getInt(1)); // Retrieve the generated user ID
                    conn.commit();
                } else {
                    throw new SQLException("Couldn't get car ID");
                }

            }
        }catch (SQLException e){
            System.out.println("Error: "+ e);
            //e.printStackTrace();
        }
    }

    public void createViewForCarsRentedTimes(){
        try (Statement statement = conn.createStatement()) {
            statement.execute(VIEV_FOR_CARS);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Create View failed: " + e.getMessage());
        }
    }


    public void updateCar(String brand, int newKms) throws SQLException {
        try {
            queryCar.setString(1, brand);
            ResultSet result = queryCar.executeQuery();
            conn.setAutoCommit(false);
            if (result.next()){
                queryUpdateCar.setInt(1, newKms);
                queryUpdateCar.setString(2, brand);

                int affectedRows = queryUpdateCar.executeUpdate();
                if(affectedRows != 1) {
                    throw new SQLException("Couldn't update Car!");
                }

                Statement statement = conn.createStatement();
                ResultSet generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
                if (generatedKeys.next()) {
                    System.out.println("Car updated with ID: " + generatedKeys.getInt(1)); // Retrieve the generated user ID
                    conn.commit();
                } else {
                    throw new SQLException("Couldn't get car ID");
                }
            }else{
                System.out.println("Car updated with ID: " + brand + "Not in db"); // Retrieve the generated user ID

            }
        }catch (SQLException e){
            System.out.println("Error: "+ e);
            //e.printStackTrace();
        }
    }

    public void getAllUsersOther(){
        try (Statement statement = conn.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM "+ TABLE_CARS);
            while (res.next()){
                System.out.printf("%8s|%7s%n",res.getString(COLUM_CAR_BRAND), res.getString(COLUM_CAR_KMS));
            }

            ResultSet res1 = statement.executeQuery("SELECT * FROM "+ TABLE_USERS);
            while (res1.next()){
                System.out.printf("%-12s|%5s|%4s%n",res1.getString(COLUM_USER_NAME), res1.getString(COLUM_USER_PHONE), res1.getString(COLUM_USER_CAR));
            }
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}
