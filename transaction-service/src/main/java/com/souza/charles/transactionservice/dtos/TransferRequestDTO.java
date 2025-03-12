package com.souza.charles.transactionservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO implements Serializable {

    private String accountFrom;
    private String accountTo;
    private LocalDateTime dateTime;
    private BigDecimal value;
}