package souza.souza.charles.transactionservice.utils;

import java.io.Serializable;

public enum Operation implements Serializable {

    Withdrawal,
    Deposit,
    Transfer_Sent,
    Transfer_Received,
    Pix_Sent,
    Pix_Received

}