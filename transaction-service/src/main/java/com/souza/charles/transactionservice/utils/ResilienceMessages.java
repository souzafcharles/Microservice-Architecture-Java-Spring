package com.souza.charles.transactionservice.utils;

import java.io.Serializable;

public final class ResilienceMessages implements Serializable {

    private ResilienceMessages() {
        throw new UnsupportedOperationException("Utility class, instantiation is prohibited.");
    }

    public static final String REQUEST_RECEIVED = "Request received!";
    public static final String SERVICE_UNAVAILABLE = "Service unavailable.";
}