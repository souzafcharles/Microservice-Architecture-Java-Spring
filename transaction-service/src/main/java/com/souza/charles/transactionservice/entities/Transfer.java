package com.souza.charles.transactionservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.souza.charles.transactionservice.utils.Operation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document(collection = "transfer")
public class Transfer implements Serializable {

    @Id
    private String id;
    @NonNull
    private String accountFrom;
    @NonNull
    private String accountTo;
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime dateTime;
    @NonNull
    private BigDecimal value;
    @Field(name = "operation")
    private Operation operation;
}