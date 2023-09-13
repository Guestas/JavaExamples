package sqlApp;

import java.sql.*;

public class Start {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/martince/Documents/Java/JavaExamples/database/"+DB_NAME;
    public static final String TABLE_CARS = "cars";
    public static final String COLUM_BRAND = "brand";
    public static final String COLUM_KMS = "kms";
    public static final String TABLE_USERS = "users";
    public static final String COLUM_NAME = "name";
    public static final String COLUM_CAR = "car";
    public static final String COLUM_PHONE = "phone";
    public static final String VIEW_CARS = "viewCars";

    public static void main(String[] args) {
        try {
            DatasourceFromModel datasource = new DatasourceFromModel();
            datasource.open();
            datasource.createTables();
            datasource.close();

            Connection con = DriverManager.getConnection(CONNECTION_STRING);
            Statement st = con.createStatement();

            st.execute("DROP TABLE IF EXISTS "+TABLE_CARS);
            st.execute("DROP TABLE IF EXISTS "+TABLE_USERS);
            st.execute("CREATE TABLE IF NOT EXISTS %s (`_id` INTEGER PRIMARY KEY, %s text, %s integer) ".formatted(TABLE_CARS, COLUM_BRAND, COLUM_KMS));
            st.execute("CREATE TABLE IF NOT EXISTS %s (`_id` INTEGER PRIMARY KEY, %s text, %s integer, %s integer) ".formatted(TABLE_USERS, COLUM_NAME, COLUM_PHONE, COLUM_CAR));

            insertCar(st, "BMW", 45);
            insertCar(st, "GMC", 40);
            insertCar(st, "Fiat", 45);

            insertUser(st, "xx", 111, 2);
            insertUser(st, "yy",222, 3);
            insertUser(st, "xx", 333,2);





            st.execute("DROP VIEW IF EXISTS "+VIEW_CARS);
            st.execute(("CREATE VIEW IF NOT EXISTS '%s' AS SELECT %s.%s, COUNT(%s.%s) AS 'rented' FROM %s INNER JOIN %s WHERE %s.%s=%s.%s GROUP BY %s.%s")
                    .formatted(VIEW_CARS,TABLE_CARS, COLUM_BRAND, TABLE_USERS, COLUM_CAR, TABLE_USERS, TABLE_CARS, TABLE_USERS, COLUM_CAR, TABLE_CARS, "_id", TABLE_USERS, COLUM_CAR));

            st.execute("UPDATE %s SET %s=121 WHERE %s='xx'".formatted(TABLE_USERS, COLUM_PHONE, COLUM_NAME));
            //st.execute("DELETE FROM %s WHERE %s='BB'".formatted(TABLE_CONTACTS, COLUM_NAME));

            ResultSet res = st.executeQuery("SELECT * FROM "+ TABLE_CARS);
            while (res.next()){
                System.out.printf("%8s|%7s%n",res.getString(COLUM_BRAND), res.getString(COLUM_KMS));
            }

            ResultSet res1 = st.executeQuery("SELECT * FROM "+ TABLE_USERS);
            while (res1.next()){
                System.out.printf("%8s|%7s|%4s%n",res1.getString(COLUM_NAME), res1.getString(COLUM_PHONE), res1.getString(COLUM_CAR));
            }

            res.close();
            st.close();
            con.close();
        }catch (SQLException e){
            System.out.println("Error: "+ e);
            e.printStackTrace();
        }

    }
    private static void insertUser(Statement state, String name, int phone, int car) throws SQLException {
        ResultSet r = state.executeQuery("SELECT COUNT(*) FROM %s WHERE %s=%d".formatted(TABLE_CARS, "_id", car));
        System.out.println(r.getInt(1));
        if (r.getInt(1)!=0){
            state.execute("INSERT INTO %s (%s, %s, %s) VALUES ('%s',%d, %d)"
                    .formatted(TABLE_USERS, COLUM_NAME, COLUM_PHONE, COLUM_CAR, name, phone, car));
        }

    }

    private static void insertCar(Statement state, String brand, int kms) throws SQLException {

        state.execute("INSERT INTO %s (%s, %s) VALUES ('%s',%d)"
                .formatted(TABLE_CARS, COLUM_BRAND, COLUM_KMS, brand, kms));
    }




}
