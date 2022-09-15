package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exception.CustomException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService implements IBookService{

    //Autowired BookRepository to inject its dependency here
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailService emailService;

    //Ability to serve to controller's insert api call
    public Book insertBook(String token, BookDTO bookDTO) {
        long userId = tokenUtility.decodeToken(token);
        UserData user = userRepository.findById((int) userId).orElseThrow(() -> new CustomException("tokens do not match the user"));
        if (user.isAdmin()) {
            Book book =  bookRepository.save(new Book(bookDTO));
            emailService.sendEmail(user.getEmail(), "new book added", "new book if id "+ book.getBookID()+" is added to book Store by "+ user.getFirstName()+" "+user.getLastName() +". \nBook Details:\n"+book.toJson());
            return book;
        } else throw new CustomException("User is not an Admin");
    }

    //Ability to serve to controller's retrieving all records api call
    public List<Book> getAllBooks() {
        if (!bookRepository.findAll().isEmpty()) {
            return bookRepository.findAll();
        } else throw new CustomException("Books Table is Empty!");
    }

    //Ability to serve to controller's retrieving records by id api call
    public Book getBookById(String token, int bookId) {
        long userId = tokenUtility.decodeToken(token);
        userRepository.findById((int) userId).orElseThrow(() -> new CustomException("tokens do not match the user"));
        return bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Books id " + bookId + " not found!"));
    }

    //Ability to serve to controller's update record by id api call
    public Book updateBookById(String token, int bookId, BookDTO bookDTO) {
        long userId = tokenUtility.decodeToken(token);
        userRepository.findById((int) userId).orElseThrow(() -> new CustomException("tokens do not match the user"));
        bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Books id " + bookId + " not found!"));
        Book book = new Book(bookDTO);
        book.setBookId(bookId);
        return bookRepository.save(book);
    }

    //Ability to serve to controller's retrieve record by book name api call
    public List<Book> getBookByName(String token, String name) {
        int userId = (int) tokenUtility.decodeToken(token);
        userRepository.findById(userId).orElseThrow(() -> new CustomException("tokens do not match the user"));
        if (!bookRepository.findBookByName(name).isEmpty()) {
            return bookRepository.findBookByName(name);
        } else throw new CustomException("Books name " + name + " not found!");
    }

    //Ability to serve to controller's delete record by id api call
    public String deleteBookById(String token, int bookId) {
        long userId = tokenUtility.decodeToken(token);
        UserData user = userRepository.findById((int) userId).orElseThrow(() -> new CustomException("tokens do not match the user"));
        bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Books id " + bookId + " not found!"));
        if (user.isAdmin()) {
            bookRepository.deleteById(bookId);
            return "Book id: " + bookId;
        } else throw new CustomException("User is not Admin");
    }

    //Ability to serve to controller's sort all records in ascending order api call
    public List<Book> sortBooksAscending() {
        if (!bookRepository.findAll().isEmpty()) {
            return bookRepository.sortBooksAscending();
        } else throw new CustomException("Books Table is Empty!");
    }

    //Ability to serve to controller's sort all records in descending order api call
    public List<Book> sortBooksDescending() {
        if (!bookRepository.findAll().isEmpty()) {
            return bookRepository.sortBooksDescending();
        } else throw new CustomException("Books Table is Empty!");
    }

    //Ability to serve to controller's update book quantity api call
    public Book updateQuantity(String token, int bookId, int quantity) {
        long userId = tokenUtility.decodeToken(token);
        userRepository.findById((int) userId).orElseThrow(() -> new CustomException("tokens do not match to the user"));
        bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Books id " + bookId + " not found!"));
        Book book = bookRepository.findBookById(bookId);
        book.setQuantity(quantity);
        return bookRepository.save(book);
    }
}
