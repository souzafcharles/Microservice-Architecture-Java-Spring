package souza.souza.charles.transactionservice.utils;

import java.io.Serializable;

public class TransactionMessages implements Serializable {

    private TransactionMessages() {
        throw new IllegalStateException("Utility class, cannot be instantiated.");
    }

    public static final String TRANSACTION_REGISTRATION = " successfully registered!";
}