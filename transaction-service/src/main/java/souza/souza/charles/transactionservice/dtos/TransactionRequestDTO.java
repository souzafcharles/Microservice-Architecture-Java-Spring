package souza.souza.charles.transactionservice.dtos;

import lombok.Data;
import souza.souza.charles.transactionservice.utils.Operation;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TransactionRequestDTO implements Serializable {
    private String accountNumber;
    private Operation operation;
    private BigDecimal value;
}