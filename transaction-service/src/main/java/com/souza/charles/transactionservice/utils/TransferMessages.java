package com.souza.charles.transactionservice.utils;

import java.io.Serializable;

public final class TransferMessages implements Serializable {

    private TransferMessages() {
        throw new UnsupportedOperationException("Utility class, instantiation is prohibited.");
    }

    public static final String INSUFFICIENT_BALANCE = "Insufficient funds available.";
    public static final String NO_TRANSFERS_FOUND = "No transfer records found for the specified account.";
}