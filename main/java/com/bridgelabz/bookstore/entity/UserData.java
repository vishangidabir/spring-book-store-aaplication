package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.UserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "usersDetails")
public class UserData {

    @Id
    @GeneratedValue
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    private LocalDate dob;
    private String password;

    private String token;
    private boolean isAdmin;

    private boolean isLogin;


    public UserData(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.dob = userDTO.getDob();
        this.password = userDTO.getPassword();
        this.token = userDTO.getToken();
        this.isAdmin = userDTO.isAdmin();
        this.isLogin = userDTO.isLogin();
    }

    public UserData(int userID, UserData userData) {
        this.userID = userData.getUserID();
        this.firstName = userData.getFirstName();
        this.lastName = userData.getLastName();
        this.email = userData.getEmail();
        this.address = userData.getAddress();
        this.dob = userData.getDob();
        this.password = userData.getPassword();
        this.token = userData.getToken();
        this.isAdmin = userData.isAdmin();
        this.isLogin = userData.isLogin;
    }

}
