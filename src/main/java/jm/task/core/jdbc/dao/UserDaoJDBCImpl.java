package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = getConnection().createStatement()){
            statement.execute("CREATE TABLE `mydb`.`users` (\n" +
                    "  `usersId` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `usersName` VARCHAR(45) NOT NULL,\n" +
                    "  `usersLastName` VARCHAR(45) NOT NULL,\n" +
                    "  `usersAge` INT NULL,\n" +
                    "  PRIMARY KEY (`usersId`));");
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement()){
            statement.execute("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO users(usersName, usersLastName, usersAge) values (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println(name + " " + lastName + ", " + age + " добавлен в БД.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
