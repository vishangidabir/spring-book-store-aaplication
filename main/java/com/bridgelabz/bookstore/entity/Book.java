package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.BookDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonComponent
@Table(name = "book_Details")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)

    private int bookID;
    private String bookName;
    private String authorName;
    private Integer price;
    private Integer quantity;
    private String bookDescription;
    private String bookImg;

    public Book(BookDTO dto) {
        this.bookName = dto.getBookName();
        this.authorName = dto.getAuthorName();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
        this.bookDescription = dto.getBookDescription();
        this.bookImg = dto.getBookImg();
    }

    public String toJson() {
        return  "bookId: " + bookID +
                ", \nbookName: " + bookName +
                ", \nauthorName: " + authorName +
                ", \nbookDescription: " + bookDescription +
                ", \nbookImg: " + bookImg +
                ", \nprice: " + price +
                ", \nquantity: " + quantity ;
    }

}
