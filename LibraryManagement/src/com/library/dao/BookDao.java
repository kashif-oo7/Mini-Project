package com.library.dao;

import com.library.model.Book;
import java.util.List;

public interface BookDao {
    void create(Book book);
    List<Book> readAll();
	void update(int bookIdToBorrow, boolean b);
	void delete(String bookTitle, boolean b);
	void borrowbook(String borrowbook, boolean b);
}