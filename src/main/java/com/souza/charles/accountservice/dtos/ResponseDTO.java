package com.souza.charles.accountservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class ResponseDTO implements Serializable {

    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    private String port;
}