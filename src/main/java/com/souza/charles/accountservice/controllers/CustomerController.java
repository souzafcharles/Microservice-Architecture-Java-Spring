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
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO dto) {
        return getResponseSuccess(customerService.create(dto), CustomerMessages.CUSTOMER_ACCOUNT_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> readAll() {
        return getResponseSuccess(customerService.readAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> readOne(@PathVariable Long id) {
        return getResponseSuccess(customerService.readOne(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return getResponseSuccess(customerService.update(id, dto), CustomerMessages.CUSTOMER_ACCOUNT_UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return getResponseSuccess(null, CustomerMessages.CUSTOMER_ACCOUNT_DELETE_SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/validate/{account}")
    public ResponseEntity<CustomerDTO> isValid(@PathVariable String account) {
        return getResponseSuccess(customerService.isValid(account), HttpStatus.OK);
    }

}