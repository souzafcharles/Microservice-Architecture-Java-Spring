package com.souza.charles.accountservice.controllers;

import com.souza.charles.accountservice.dtos.CustomerDTO;
import com.souza.charles.accountservice.services.CustomerService;
import com.souza.charles.accountservice.utils.CustomerMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/customers")
public class CustomerController extends BaseController implements Serializable {

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO dto) {
        return getResponseSuccess(customerService.save(dto), CustomerMessages.CUSTOMER_ACCOUNT_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }

}