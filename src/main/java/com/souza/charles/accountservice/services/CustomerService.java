package com.souza.charles.accountservice.services;

import com.souza.charles.accountservice.dtos.CustomerDTO;
import com.souza.charles.accountservice.dtos.CustomerResponseDTO;
import com.souza.charles.accountservice.entities.Customer;
import com.souza.charles.accountservice.repositories.CustomerRepository;

import com.souza.charles.accountservice.utils.AccountNumberGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
public class CustomerService implements Serializable{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public CustomerResponseDTO save(CustomerDTO dto) {
        Customer entity = mapper.map(dto, Customer.class);
        entity.setEnable(true);
        entity.setAccountNumber(AccountNumberGenerator.generate());
        return mapper.map(customerRepository.save(entity), CustomerResponseDTO.class);
    }
}
