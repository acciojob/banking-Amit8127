package com.driver;

public class ValidLicenseCanNotBeGenerated extends RuntimeException{
    public ValidLicenseCanNotBeGenerated() {
        super("Valid License can not be generated");
    }
}
