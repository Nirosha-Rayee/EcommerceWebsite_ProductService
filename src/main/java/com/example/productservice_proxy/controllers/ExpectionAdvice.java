package com.example.productservice_proxy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
<<<<<<< HEAD
//@ControllerAdvice // this is a global exception handler
public class ExpectionAdvice {
    //@ExceptionHandler({Exception.class})
=======
@ControllerAdvice // this is a global exception handler
public class ExpectionAdvice {
    @ExceptionHandler({Exception.class})
>>>>>>> origin/master
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Kuch toh phat hai like", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
