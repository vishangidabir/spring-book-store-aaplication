package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer quantity;
    private String address;
    private Integer price;
    private Integer userID;
    private String email;
    private Integer bookID;
    private boolean cancel;

}
