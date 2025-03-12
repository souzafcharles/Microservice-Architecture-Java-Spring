package souza.souza.charles.transactionservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import souza.souza.charles.transactionservice.utils.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Document(collection = "transaction")
public class Transaction implements Serializable {

    @Id
    private String id;

    @Field("account_number")
    private String accountNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime date;

    private Operation operation;
    private BigDecimal value;
}