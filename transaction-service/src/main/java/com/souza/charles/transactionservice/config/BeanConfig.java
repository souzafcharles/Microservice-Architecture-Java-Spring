package com.souza.charles.transactionservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class BeanConfig implements Serializable {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}