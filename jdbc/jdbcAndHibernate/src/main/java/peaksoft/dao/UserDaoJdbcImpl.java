package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private Connection connection;

    public UserDaoJdbcImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sql = """
                create table if not exists users(
                id  serial primary key,
                name varchar (50) not null,
                last_name varchar(50) not null,
                age smallint not null
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Table users created successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = """
                drop table users;
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Drop table successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            if (name == null || lastName == null || age == 0) {
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String sql = """
                insert into users (name, last_name, age) values
                (?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User saved successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            if (id == 0) throw new Exception();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


        String sql = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.close();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
                SELECT * FROM users;
                """;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()){
                String name =resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");
                User user = new User(name,lastName,age);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = """
                truncate table users;
                """;
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            rs.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}