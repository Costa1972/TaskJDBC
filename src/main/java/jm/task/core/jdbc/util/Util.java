package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3306/mydb";
    private static final String dbUSERNAME = "root";
    private static final String dbPASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        Driver driver;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Не найден класс драйвера!");
        }
        try {
            connection = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
