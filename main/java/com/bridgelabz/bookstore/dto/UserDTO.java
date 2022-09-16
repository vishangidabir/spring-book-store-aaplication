package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDTO {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String address;
    private LocalDate dob;
    private String token;
    private String password;
    private boolean isAdmin;
    private boolean login;

}
