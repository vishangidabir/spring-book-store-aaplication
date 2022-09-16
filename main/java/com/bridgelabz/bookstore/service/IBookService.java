package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;

import java.util.List;

public interface IBookService {
    Book insertBook(String token, BookDTO bookDTO);

    List<Book> getAllBooks();

    Book getBookById(String token, int bookId);

    Book updateBookById(String token, int bookId, BookDTO bookDTO);

    List<Book> getBookByName(String token, String name);

    String deleteBookById(String token, int bookId);

    List<Book> sortBooksAscending();

    List<Book> sortBooksDescending();

    Book updateQuantity(String token, int bookId, int quantity);
}
