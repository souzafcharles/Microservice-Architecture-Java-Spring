package com.souza.charles.accountservice;

import com.souza.charles.accountservice.environments.LoadEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        LoadEnvironment.loadEnv();
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}