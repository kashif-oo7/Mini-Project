package com.User;
import com.library.dao.BookDao;
import com.library.dao.BookDaoImpl;
import com.library.model.Book;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDaoImpl();

        // Registration process
        System.out.println("Welcome to the Library Management System!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int registrationChoice = scanner.nextInt();
        scanner.nextLine();

        if (registrationChoice == 1) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Code to store the user registration details (e.g., in a database) can be added here
            System.out.println("Registration successful! Please login.");
        }

        // Login process
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();

        if (inputUsername.equals(ADMIN_USERNAME) && inputPassword.equals(ADMIN_PASSWORD)) {
            System.out.println("Admin login successful! Admins should login from the admin panel.");
            scanner.close();
            System.exit(0);
        }

        // Assuming the user is successfully authenticated, display the user menu
        System.out.println("User login successful! You have access to the library.");

        while (true) {
            System.out.println("\n1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Book> books = bookDao.readAll();
                    if (books.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter the Book ID to Borrow: ");
                    int bookId = scanner.nextInt();
                    bookDao.update(bookId, false);
                    break;
                case 3:
                    System.out.println("Enter book title to return: ");
                    String bookTitleToReturn = scanner.nextLine();
                    bookDao.delete(bookTitleToReturn, true);
                    break;
                case 4:
                    System.out.println("User logout successful!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}