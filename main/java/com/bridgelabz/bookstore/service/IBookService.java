package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;

import java.util.List;

public interface IBookService {
    Book insertBook(String token, BookDTO bookDTO);

    List<Book> getAllBooks();

    Book getBookById(String token, int bookId);
    List<Book> getBookByName(String token, String name);

    public Book updateBookById(String token, int bookID, BookDTO bookDTO);

    String deleteBookById(String token, int bookId);

    List<Book> sortBooksAscending();

    List<Book> sortBooksDescending();

    Book updateQuantity(String token, int bookId, int quantity);
}
