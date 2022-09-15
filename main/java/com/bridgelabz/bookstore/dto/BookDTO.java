package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class BookDTO {

    private String bookName;
    private String authorName;
    private Integer price;
    private Integer quantity;
    private String bookDescription;
    private String bookImg;
}
