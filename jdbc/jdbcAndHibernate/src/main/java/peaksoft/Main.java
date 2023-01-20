package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        while (true) {
            System.out.println("""
                    1 - crate table users
                    2 - drop table users
                    3 - save user
                    4 - remove user by id
                    5 - get all users
                    6 - clean table
                    """);
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> userService.createUsersTable();
                case 2 -> userService.dropUsersTable();
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = new Scanner(System.in).nextLine();
                    System.out.print("Enter age: ");
                    byte age = new Scanner(System.in).nextByte();
                    userService.saveUser(name, lastName, age);
                }
                case 4 -> {
                    System.out.print("Enter user id: ");
                    long id = new Scanner(System.in).nextLong();
                    userService.removeUserById(id);
                }
                case 5 -> userService.getAllUsers().forEach(System.out::println);
                case 6 -> userService.cleanUsersTable();
                default -> System.out.println("Error !!!!");
            }
        }
    }
}
