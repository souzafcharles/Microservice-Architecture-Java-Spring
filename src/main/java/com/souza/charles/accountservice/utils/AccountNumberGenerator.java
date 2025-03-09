package com.souza.charles.accountservice.utils;

import java.security.SecureRandom;

public class AccountNumberGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final String PREFIX = "8899";
    private static final int ACCOUNT_LENGTH = 6;

    private AccountNumberGenerator() {
        throw new IllegalStateException("Utility class, cannot be instantiated.");
    }

    public static String generate() {
        StringBuilder account = new StringBuilder(PREFIX);

        for (int i = 0; i < ACCOUNT_LENGTH; i++) {
            account.append(random.nextInt(10));
        }

        return account.toString();
    }
}