package com.souza.charles.accountservice.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {

    private String cpf;
    private String fullName;

}