package com.souza.charles.accountservice.services;

import com.souza.charles.accountservice.dtos.CustomerDTO;
import com.souza.charles.accountservice.entities.Customer;
import com.souza.charles.accountservice.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class CustomerService implements Serializable {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public CustomerDTO save(CustomerDTO dto) {
        Customer entity = mapper.map(dto, Customer.class);
        entity.setEnable(true);
        entity.setAccountNumber("12345");
        customerRepository.save(entity);
        return dto;
    }
}