package com.souza.charles.transactionservice.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.souza.charles.transactionservice.dtos.ResponseDTO;


@FeignClient(name = "account-service")
public interface AccountRequest {

    @GetMapping("/customers/validate/{account}")
    ResponseDTO getUserAccount(@PathVariable String account);
}