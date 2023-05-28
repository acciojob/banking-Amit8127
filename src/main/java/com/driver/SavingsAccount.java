package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;
    private static final double minBalance = 0;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name, balance,minBalance);
        this.rate = rate;
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount > maxWithdrawalLimit) {
            throw new MaximumWithdrawLimitException();
        }
        if(super.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }
        super.withdraw(amount);
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double simpleInterest=((super.getBalance() * this.rate) / 100) * years;
        return simpleInterest + super.getBalance();
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        int month = years * 12;
        int  noOfTimes = month / times;
        double finalBalance = super.getBalance();

        for(int i = 1; i < noOfTimes; i++){
            double interest = (finalBalance * this.rate) / (double) 100;
            finalBalance += interest;
        }
        return finalBalance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    public void setMaxWithdrawalLimit(double maxWithdrawalLimit) {
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }
}
