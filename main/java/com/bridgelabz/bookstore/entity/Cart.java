package com.bridgelabz.bookstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "cartDetails")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;
    @ElementCollection
    @CollectionTable(name = "cart_books",joinColumns = @JoinColumn(name = "cart_id"))
    private List<Integer> BookIdList;
    @ElementCollection
    @CollectionTable(name = "cart_book_quantity",joinColumns = @JoinColumn(name = "cart_id"))
    private List<Integer> quantity;

    public Cart(long userId, UserData userData, List<Integer> bookIdList, List<Integer> quantity) {
        this.userData = userData;
        BookIdList = bookIdList;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartID +
                ", userData=" + userData +
                ", BookIdList=" + BookIdList +
                ", quantity=" + quantity +
                '}';
    }
}
