package com.driver;


import java.util.HashMap;
import java.util.Map;


public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    private static final double minBalance = 5000;

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws InsufficientBalanceException {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, minBalance);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < minBalance) {
            throw new InsufficientBalanceException();
        }
    }

    private boolean isIdValid(String id) {
        for(int i = 1; i < id.length(); i++) {
            if(id.charAt(i) == id.charAt(i-1)) {
                return true;
            }
        }
        return false;
    }

    public void validateLicenseId() throws ValidLicenseCanNotBeGenerated {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(isIdValid(tradeLicenseId)) {
            String newTradeLicenseId = generateNewTradeLicenseId(tradeLicenseId);
            if(newTradeLicenseId.isEmpty()) {
                throw new ValidLicenseCanNotBeGenerated();
            } else {
                this.tradeLicenseId = newTradeLicenseId;
            }
        }
    }

    private String generateNewTradeLicenseId(String tradeLicenseId) {
        int n = tradeLicenseId.length();

        Map<Character, Integer> count = new HashMap<>();
        for (char ch : tradeLicenseId.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        char ch_max = getCountChar(count);
        int maxCount = count.get(ch_max);

        if (maxCount > (n + 1) / 2) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append(' ');
        }

        int ind = 0;
        while (maxCount > 0) {
            res.setCharAt(ind, ch_max);
            ind += 2;
            maxCount--;
        }

        count.remove(ch_max);
        for (char ch : count.keySet()) {
            int freq = count.get(ch);
            while (freq > 0) {
                ind = (ind >= n) ? 1 : ind;
                res.setCharAt(ind, ch);
                ind += 2;
                freq--;
            }
        }

        return res.toString();
    }

    private char getCountChar(Map<Character, Integer> count) {
        char maxFreqChar = ' ';
        int maxCount = Integer.MIN_VALUE;
        for (char ch : count.keySet()) {
            if (count.get(ch) > maxCount) {
                maxFreqChar = ch;
                maxCount = count.get(ch);
            }
        }
        return maxFreqChar;
    }
}
