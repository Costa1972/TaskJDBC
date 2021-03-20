package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.dropUsersTable();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Petr", "Sazonov", (byte) 19);
//        userDaoJDBC.saveUser("Ivan", "Dyatlov", (byte) 23);
//        userDaoJDBC.saveUser("Maria", "Alekseeva", (byte) 34);
//        userDaoJDBC.saveUser("Igor", "Stravinsky", (byte) 39);
    }
}
