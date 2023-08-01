package com.library.main;

import com.library.dao.BookDao;
import com.library.dao.BookDaoImpl;
import com.library.model.Book;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDao bookDao = new BookDaoImpl();

        // Hardcoded username and password for demonstration purposes
        String username = "admin";
        String password = "password";

        // Login process
        System.out.println("Welcome to the Library Management System!");
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();

        if (!inputUsername.equals(username) || !inputPassword.equals(password)) {
            System.out.println("Invalid credentials. Exiting...");
            scanner.close();
            System.exit(0);
        }

        System.out.println("Login successful! You have access to the library.");

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter author name: ");
                    String author = scanner.nextLine();
                    Book book = new Book(title, author);
                    bookDao.create(book);
                    break;
                case 2:
                    List<Book> books = bookDao.readAll();
                    if (books.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter the Book ID to Borrow: ");
                    int bookId = scanner.nextInt();
                    bookDao.update(bookId, false);
                    break;
                 // Inside the switch statement in the main method
                case 4:
                    System.out.println("Enter book title to return: ");
                    String bookTitleToReturn = scanner.nextLine();
                    bookDao.delete(bookTitleToReturn, true);
                    break;

                case 5:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}