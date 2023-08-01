create database books;
use books;
CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    available INT NOT NULL
);
select * from books;
