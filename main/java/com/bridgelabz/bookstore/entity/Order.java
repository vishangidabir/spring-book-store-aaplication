package com.bridgelabz.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Data
@Table(name = "OrderDetails")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData user;
    @OneToOne
    private Book book;
    private boolean cancel;

    public Order(Integer orderID, Integer price, Integer quantity, String address, Book book, UserData user, boolean cancel) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.book = book;
        this.user = user;
        this.cancel = cancel;
    }

    public Order(Integer price, Integer quantity, String address, Book book, UserData user, boolean cancel) {
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.book = book;
        this.user = user;
        this.cancel = cancel;
    }
}
