package br.com.ufrn.troquinhasrestapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ContatoControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(ContatoNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> roleNotFound(ContatoNotFoundException roleNotFound){
        MessageExceptionHandler error = new MessageExceptionHandler(
            new Date(), HttpStatus.NOT_FOUND.value(), "Contato Não Encontrado");
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
