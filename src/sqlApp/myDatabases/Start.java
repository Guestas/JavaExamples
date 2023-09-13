package sqlApp.myDatabases;

import sqlApp.myDatabases.model.DatasourceFromModel;

import java.sql.*;


public class Start {

    public static void main(String[] args) {


        // library in file -> project structure...
        DatasourceFromModel data = new DatasourceFromModel();
        data.open();
        try {
            data.createTables();

            data.insertCar("FIAT", 45);
            data.insertCar("GMC", 30);
            data.insertCar("BMW", 125);
            data.insertUser("John Wane", 777, 2);
            data.insertUser("Jim Shadow", 458, 2);
            data.insertUser("James Brown", 56, 1);
            data.insertUser("James Brown", 56, 1);
            data.createViewForCarsRentedTimes();
            data.updateCar("BMW", 160);
            data.getAllUsersOther();

        }catch (SQLException e){
            System.out.println("There is an error: " + e.getMessage());
        }
        data.close();
    }

}
