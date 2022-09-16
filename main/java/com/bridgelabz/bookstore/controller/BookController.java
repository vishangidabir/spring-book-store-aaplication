package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResposeDTO;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookdetails")
public class BookController {
    @Autowired
    private IBookService bookService;

    /**
     * Inserted book details to database
     * this is POST call
     * http://localhost:8080/bookdetails/insert
     */
    @PostMapping("/insert")
    public ResponseEntity<ResposeDTO> insertBook(@RequestHeader(name = "Authorization") String token, @RequestBody BookDTO bookDTO) {
        ResposeDTO responseDTO = new ResposeDTO("Inserted new Book", bookService.insertBook(token, bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieve all book details to database
     * this is GET call
     * http://localhost:8080/bookdetails/retrieveAllBooks
     */
    @GetMapping("/retrieveAllBooks")
    public ResponseEntity<ResposeDTO> getAllBooks() {
        ResposeDTO responseDTO = new ResposeDTO("GET call Success", bookService.getAllBooks());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieve book details by ascending order to database
     * this is GET call
     * http://localhost:8080/bookdetails/sort/asce
     */
    @GetMapping("/sort/asce")
    public ResponseEntity<ResposeDTO> sortBooksAscending() {
        ResposeDTO responseDTO = new ResposeDTO("GET call Success", bookService.sortBooksAscending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieve book details by descending order to database
     * this is GET call
     * http://localhost:8080/bookdetails/sort/desc
     */
    @GetMapping("/sort/desc")
    public ResponseEntity<ResposeDTO> sortBooksDescending() {
        ResposeDTO responseDTO = new ResposeDTO("GET call Success", bookService.sortBooksDescending());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieve book details by id to database
     * this is GET call
     * http://localhost:8080/bookdetails/retrieve/1
     */
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ResposeDTO> getBookById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) {
        ResposeDTO responseDTO = new ResposeDTO("GET call Success", bookService.getBookById(token, id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Retrieve book details by name to database
     * this is GET call
     * http://localhost:8080/bookdetails/retrieve
     */
    @GetMapping("/retrieve")
    public ResponseEntity<ResposeDTO> searchBookByName(@RequestHeader(name = "Authorization") String token, @RequestParam String bookName) {
        ResposeDTO responseDTO = new ResposeDTO("GET call Success", bookService.getBookByName(token, bookName));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Delete book details by name to database
     * this is DELETE call
     * http://localhost:8080/bookdetails/delete/1
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResposeDTO> deleteBookById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) {
        ResposeDTO responseDTO = new ResposeDTO("Book deleted", bookService.deleteBookById(token, id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Update book details by id to database
     * this is PUT call
     * http://localhost:8080/bookdetails/update/1
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResposeDTO> updateBookById(@RequestHeader(name = "Authorization") String token, @PathVariable int id, @RequestBody BookDTO bookDTO) {
        ResposeDTO responseDTO = new ResposeDTO("Book Updated", bookService.updateBookById(token, id, bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Update book details by quantity to database
     * this is PUT call
     * http://localhost:8080/bookdetails/updatequantity
     */
    @PutMapping("/updatequantity")
    public ResponseEntity<ResposeDTO> updateQuantity(@RequestHeader(name = "Authorization") String token, @RequestParam int id, int quantity) {
        ResposeDTO responseDTO = new ResposeDTO("Book quantity updated", bookService.updateQuantity(token, id, quantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
