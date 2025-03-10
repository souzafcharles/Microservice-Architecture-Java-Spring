package com.souza.charles.accountservice.services;

import com.souza.charles.accountservice.dtos.CustomerDTO;
import com.souza.charles.accountservice.dtos.CustomerResponseDTO;
import com.souza.charles.accountservice.entities.Customer;
import com.souza.charles.accountservice.exceptions.CustomerNotFoundException;
import com.souza.charles.accountservice.repositories.CustomerRepository;
import com.souza.charles.accountservice.utils.CustomerMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class CustomerService implements Serializable {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public CustomerResponseDTO create(CustomerDTO dto) {
        Customer entity = mapper.map(dto, Customer.class);
        entity.setEnable(true);
        return mapper.map(customerRepository.save(entity), CustomerResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> readAll() {
        List<Customer> list = customerRepository.findAll();
        return list.stream()
                .map(customer -> mapper.map(customer, CustomerResponseDTO.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO readOne(Long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(CustomerMessages.CUSTOMER_NOT_FOUND + id));
        return mapper.map(entity, CustomerResponseDTO.class);
    }

    @Transactional
    public CustomerResponseDTO update(Long id, CustomerDTO dto) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(CustomerMessages.CUSTOMER_NOT_FOUND + id));
        entity.setFullName(dto.getFullName());
        return mapper.map(customerRepository.save(entity), CustomerResponseDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(CustomerMessages.CUSTOMER_NOT_FOUND + id));
        try {
            customerRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(String.format(CustomerMessages.CUSTOMER_DELETE_RELATED_DATA, id));
        }
    }
}