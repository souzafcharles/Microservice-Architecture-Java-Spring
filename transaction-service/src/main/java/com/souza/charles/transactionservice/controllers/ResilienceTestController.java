package com.souza.charles.transactionservice.controllers;

import com.souza.charles.transactionservice.dtos.ResponseDTO;
import com.souza.charles.transactionservice.utils.ResilienceMessages;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("resilience")
public class ResilienceTestController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ResilienceTestController.class);

    private final RestTemplate restTemplate;

    public ResilienceTestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @Retry(name = "account", fallbackMethod = "fallbackMethod")
    public ResponseEntity<ResponseEntity<ResponseDTO>> getResponseEntity() {
        logger.info(ResilienceMessages.REQUEST_RECEIVED);
        var response =
                new RestTemplate().getForEntity("http://localhost:8000/customerss", ResponseDTO.class);

        return ResponseEntity.ok(response);
    }

    private ResponseEntity fallbackMethod(Exception error) {
        return getResponseError(ResilienceMessages.SERVICE_UNAVAILABLE, HttpStatus.OK);
    }
}