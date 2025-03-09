package com.souza.charles.accountservice.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerResponseDTO extends CustomerDTO implements Serializable {

    private String accountNumber;

}