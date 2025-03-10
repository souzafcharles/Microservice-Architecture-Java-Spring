package souza.souza.charles.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import souza.souza.charles.transactionservice.environments.LoadEnvironment;

@SpringBootApplication
public class TransactionServiceApplication {

	public static void main(String[] args) {
		LoadEnvironment.loadEnv();
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

}