package com.example.productservice_proxy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

//@ControllerAdvice
public class ExceptionAdvice {
    //@ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Kuch toh phat hai", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
