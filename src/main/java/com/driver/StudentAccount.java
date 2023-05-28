package com.driver;

public class StudentAccount extends BankAccount{

    String  institutionName;
    private static final double minBalance = 0;
    public StudentAccount(String name, double balance, String  institutionName) {
        //minimum balance is 0 by default
        super(name, balance,minBalance);
        this.institutionName = institutionName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
}
