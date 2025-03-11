package souza.souza.charles.transactionservice.services;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import souza.souza.charles.transactionservice.dtos.TransferRequestDTO;
import souza.souza.charles.transactionservice.dtos.TransferResponseDTO;
import souza.souza.charles.transactionservice.entities.Transfer;
import souza.souza.charles.transactionservice.repositories.TransferRepository;
import souza.souza.charles.transactionservice.utils.Operation;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService extends BaseService implements Serializable {

    @Autowired
    TransferRepository transferRepository;

    public Object save(TransferRequestDTO dto) {
        dto.setDateTime(LocalDateTime.now());
        Transfer transfer = mapper.map(dto, Transfer.class);
        return transferRepository.save(transfer);
    }

    public List<TransferResponseDTO> getAllByAccountNumber(String accountNumber) {
        var transfers = transferRepository.getTransfersByAccountFrom(accountNumber);
        Type listType = new TypeToken<List<TransferResponseDTO>>() {
        }.getType();

        List<TransferResponseDTO> transferResponse = mapper.map(transfers, listType);

        transferResponse.forEach(t -> {
            if (t.getAccountFrom().equals(accountNumber)) {
                t.setOperation(Operation.Transfer_Sent);
                t.setValue(t.getValue().negate());
            } else {
                t.setOperation(Operation.Transfer_Received);
            }
        });

        return transferResponse;
    }
}