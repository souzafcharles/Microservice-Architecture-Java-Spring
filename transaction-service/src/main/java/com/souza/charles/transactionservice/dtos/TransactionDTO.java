package com.souza.charles.transactionservice.dtos;

import lombok.Data;
import com.souza.charles.transactionservice.utils.Operation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO implements Serializable {
    private LocalDateTime dateTime;
    private Operation operation;
    private BigDecimal value;
}