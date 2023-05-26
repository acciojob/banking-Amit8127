package com.driver;

public class MaximumWithdrawLimitException extends RuntimeException{
    public MaximumWithdrawLimitException() {
        super("Maximum Withdraw Limit Exceeded");
    }
}
