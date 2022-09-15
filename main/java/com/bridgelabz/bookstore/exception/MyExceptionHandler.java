package com.bridgelabz.bookstore.exception;

import com.bridgelabz.bookstore.dto.ResposeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResposeDTO> BookStoreException(CustomException exeception) {
        ResposeDTO responseDTO = new ResposeDTO("Book Store Exception! ", exeception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
