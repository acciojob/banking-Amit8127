package com.driver;

public class InsufficientBalanceException extends Exception{

    public InsufficientBalanceException() {
        super("Insufficient Balance");
    }
}
