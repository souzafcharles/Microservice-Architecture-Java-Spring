package com.souza.charles.accountservice.utils;

import java.io.Serializable;

public class CustomerMessages implements Serializable {

    private CustomerMessages() {
        throw new IllegalStateException("Utility class, cannot be instantiated.");
    }

    public static final String CUSTOMER_ACCOUNT_CREATED_SUCCESSFULLY = "Customer account created successfully.";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found.";
    public static final String CUSTOMER_ACCOUNT_UPDATE_SUCCESS = "Customer account updated successfully.";
    public static final String CUSTOMER_ACCOUNT_DELETE_SUCCESS = "Customer account deleted successfully.";
}