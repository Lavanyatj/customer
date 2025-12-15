package com.example.customer.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    private String msg;
    public CustomerAlreadyExistsException() {

    }

    public CustomerAlreadyExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
