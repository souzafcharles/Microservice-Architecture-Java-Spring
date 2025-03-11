package souza.souza.charles.transactionservice.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransferResponseDTO extends TransactionDTO implements Serializable {
    private String accountFrom;
    private String accountTo;
}