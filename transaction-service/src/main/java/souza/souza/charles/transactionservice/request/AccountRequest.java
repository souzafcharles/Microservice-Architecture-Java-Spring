package souza.souza.charles.transactionservice.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import souza.souza.charles.transactionservice.dtos.ResponseDTO;

@FeignClient(name = "customers", url = "http://localhost:8000")
public interface AccountRequest {

    @GetMapping("/customers/validate/{account}")
    ResponseDTO getUserAccount(@PathVariable String account);
}