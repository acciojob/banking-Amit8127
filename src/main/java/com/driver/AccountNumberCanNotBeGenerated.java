package com.driver;

public class AccountNumberCanNotBeGenerated extends Exception{
    public AccountNumberCanNotBeGenerated() {
        super("Account Number cannot be generated");
    }
}
