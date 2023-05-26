package com.driver;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    private static final double minBalance = 5000;

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws InsufficientBalanceException {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, minBalance);
        if(balance < this.minBalance) {
            throw new InsufficientBalanceException();
        }
        if(isIdValid(tradeLicenseId)) {
            this.tradeLicenseId = tradeLicenseId;
        } else {
            this.tradeLicenseId = tradeLicenseId;
            validateLicenseId();
        }
    }

    private boolean isIdValid(String id) {
        for(int i = 1; i < id.length(); i++) {
            if(id.charAt(i) == id.charAt(i-1)) {
                return false;
            }
        }
        return true;
    }

    public void validateLicenseId() throws ValidLicenseCanNotBeGenerated {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        int size = tradeLicenseId.length();
        char[] arr = new char[size];
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for (char c : tradeLicenseId.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            maxFreq = Math.max(maxFreq, entry.getValue());
        }

        int filling = 0;
        int index = 0;
        boolean flag = true;
        if (rearranging(maxFreq)) {
            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                int freq = entry.getValue();
                char c = entry.getKey();
                int i = 0;
                if (filling == 0) {
                    for (i = 0; i < freq && index < size; i++) {
                        arr[index] = c;
                        index += 2;
                        if (i >= size || index >= size) {
                            index = 1;
                            filling = 1;
                        }
                    }
                }
                if (filling == 1) {
                    for (; i < freq && index < size; i++) {
                        arr[index] = entry.getKey();
                        index += 2;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for(char ch : arr) {
                sb.append(ch);
            }
            this.tradeLicenseId = sb.toString();
        } else {
            throw new ValidLicenseCanNotBeGenerated();
        }
    }
    private Boolean rearranging(int maxFreq) {
        int size = tradeLicenseId.length();
        if(size % 2 == 0) {
            int temp = size / 2;
            return temp >= maxFreq;
        }
        int temp = (size + 1)/2;
        return temp > maxFreq;
    }
}
