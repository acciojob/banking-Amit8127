package com.driver;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException() {
        super("Insufficient Balance");
    }
}
