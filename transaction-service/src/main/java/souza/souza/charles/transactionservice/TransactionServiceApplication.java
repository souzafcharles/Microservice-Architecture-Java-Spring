package souza.souza.charles.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import souza.souza.charles.transactionservice.environments.LoadEnvironment;

@SpringBootApplication
@EnableFeignClients
public class TransactionServiceApplication {

	public static void main(String[] args) {
		LoadEnvironment.loadEnv();
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

}