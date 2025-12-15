package com.example.customer.exception;

public class NoSuchCustomerExistsException extends RuntimeException {
    private String msg;
    public NoSuchCustomerExistsException() {

    }

    public NoSuchCustomerExistsException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
