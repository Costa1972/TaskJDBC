package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserServiceImpl implements UserService {
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
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement()){
            statement.execute("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO users(usersName, usersLastName, usersAge) values (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в БД.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Long l = id;
        String query = "DELETE FROM users WHERE usersId = " + id;
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age = resultSet.getByte(4);
                list.add(new User(id, name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users");
            System.out.println("БД очищена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
