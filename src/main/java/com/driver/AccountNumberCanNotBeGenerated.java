package com.driver;

public class AccountNumberCanNotBeGenerated extends RuntimeException{
    public AccountNumberCanNotBeGenerated() {
        super("Account Number can not be generated");
    }
}
