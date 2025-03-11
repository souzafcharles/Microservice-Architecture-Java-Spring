package souza.souza.charles.transactionservice.controllhers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import souza.souza.charles.transactionservice.dtos.ResponseDTO;

import java.io.Serializable;

public class BaseController implements Serializable {

    @Autowired
    private Environment environment;

    private String getPort() {
        return environment.getProperty("local.server.port");
    }

    protected ResponseEntity getResponseError(String message, HttpStatus status) {
        return new ResponseEntity(ResponseDTO.builder().port(getPort()).message(message).build(), status);
    }

    protected ResponseEntity getResponseSuccess(Object data, String message, HttpStatus status) {
        return new ResponseEntity(ResponseDTO.builder().port(getPort()).message(message).build(), status);
    }

    protected ResponseEntity getResponseSuccess(Object data, HttpStatus status) {
        return new ResponseEntity(ResponseDTO.builder().port(getPort()).build(), status);
    }
}