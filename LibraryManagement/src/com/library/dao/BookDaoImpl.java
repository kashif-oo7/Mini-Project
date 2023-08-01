package com.library.dao;

import com.library.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.library.util.DBHandler;

public class BookDaoImpl implements BookDao {

    public void create(Book book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBHandler.getConnection();
            String query = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setBoolean(3, book.isAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHandler.closeResources(connection, statement, null);
        }
    }

    public List<Book> readAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
            connection = DBHandler.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                boolean available = resultSet.getBoolean("available");
                Book book = new Book(title, author);
                book.setAvailable(available);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHandler.closeResources(connection, statement, resultSet);
        }
        return books;
    }

    public void update(int bookId, boolean availability) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBHandler.getConnection();
            String query = "UPDATE books SET available = ? WHERE book_id = ?";
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, availability);
            statement.setInt(2, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHandler.closeResources(connection, statement, null);
        }
    }
	@Override
	public void borrowbook(String borrowbook, boolean b) {
		// TODO Auto-generated method stub
		
	}
	 @Override
	    public void delete(String bookTitle, boolean b) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        try {
	            connection = DBHandler.getConnection();
	            String query = "UPDATE books SET available = ? WHERE title = ?";
	            statement = connection.prepareStatement(query);
	            statement.setBoolean(1, true); // Set availability to true to mark the book as returned
	            statement.setString(2, bookTitle);
	            int c=statement.executeUpdate();
	           if(c>0)
	           {
	            System.out.println("Book '" + bookTitle + "' has been returned.");
	           }
	           else
	           {
	        	   System.out.println("Book '" + bookTitle + "' not borrowed.");
	           }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBHandler.closeResources(connection, statement, null);
	        }
	    }

	    // ... (existing code)
	}