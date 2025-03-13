package com.souza.charles.transactionservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.souza.charles.transactionservice.dtos.AccountStatementDTO;
import com.souza.charles.transactionservice.dtos.TransferRequestDTO;
import com.souza.charles.transactionservice.services.TransactionService;
import com.souza.charles.transactionservice.services.TransferService;
import com.souza.charles.transactionservice.utils.TransferMessages;

import java.io.Serializable;

@RestController
@RequestMapping("/transfers")
public class TransferController extends BaseController implements Serializable {

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity create(@RequestBody TransferRequestDTO dto) {
        AccountStatementDTO accountStatementDto = transactionService.getAccountStatement(dto.getAccountFrom());
        var response = transferService.processTransfer(dto, accountStatementDto);
        if (response != null) {
            return getResponseSuccess(response, HttpStatus.CREATED);
        } else {
            return getResponseError(TransferMessages.INSUFFICIENT_BALANCE, HttpStatus.OK);
        }
    }

    @GetMapping("{accountNumber}")
    public ResponseEntity getAllByAccountNumber(@PathVariable String accountNumber) {
        var transferHistory = transferService.getAllByAccountNumber(accountNumber);
        if (!transferHistory.isEmpty()) {
            return getResponseSuccess(transferHistory, HttpStatus.OK);
        }
        return getResponseError(TransferMessages.NO_TRANSFERS_FOUND, HttpStatus.OK);
    }
}