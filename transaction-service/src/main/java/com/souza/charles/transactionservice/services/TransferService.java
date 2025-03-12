package com.souza.charles.transactionservice.services;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.souza.charles.transactionservice.dtos.AccountStatementDTO;
import com.souza.charles.transactionservice.dtos.TransferRequestDTO;
import com.souza.charles.transactionservice.dtos.TransferResponseDTO;
import com.souza.charles.transactionservice.entities.Transfer;
import com.souza.charles.transactionservice.repositories.TransferRepository;
import com.souza.charles.transactionservice.utils.Operation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TransferService extends BaseService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountValidationService accountValidationService;

    @Transactional
    public TransferResponseDTO processTransfer(TransferRequestDTO dto, AccountStatementDTO accountStatementDto) {
        accountValidationService.accountsAreValid(dto.getAccountFrom(), dto.getAccountTo());
        if (accountStatementDto.getBalance().compareTo(dto.getValue()) >= 0) {
            dto.setDateTime(LocalDateTime.now(ZoneId.of("UTC")));
            Transfer transfer = mapper.map(dto, Transfer.class);
            transfer.setOperation(Operation.Transfer_Sent);
            Transfer savedTransfer = transferRepository.save(transfer);
            return mapper.map(savedTransfer, TransferResponseDTO.class);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<TransferResponseDTO> getAllByAccountNumber(String accountNumber) {
        var transfers = transferRepository.getTransfersByAccountFrom(accountNumber);
        Type listType = new TypeToken<List<TransferResponseDTO>>() {}.getType();
        List<TransferResponseDTO> transferResponse = mapper.map(transfers, listType);
        transferResponse.forEach(transfer -> {
            if (transfer.getAccountFrom().equals(accountNumber)) {
                transfer.setOperation(Operation.Transfer_Sent);
                transfer.setValue(transfer.getValue().negate());
            } else {
                transfer.setOperation(Operation.Transfer_Received);
            }
        });
        return transferResponse;
    }
}