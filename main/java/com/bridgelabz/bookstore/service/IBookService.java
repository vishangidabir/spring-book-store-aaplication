package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;

import java.util.List;

public interface IBookService {
    public Book insertBook(String token, BookDTO bookDTO);

    public List<Book> getAllBooks();

    public Book getBookById(String token, int bookId);

    public Book updateBookById(String token, int bookId, BookDTO bookDTO);

    public List<Book> getBookByName(String token, String name);

    public String deleteBookById(String token, int bookId);

    public List<Book> sortBooksAscending();

    public List<Book> sortBooksDescending();

    public Book updateQuantity(String token, int bookId, int quantity);
}
