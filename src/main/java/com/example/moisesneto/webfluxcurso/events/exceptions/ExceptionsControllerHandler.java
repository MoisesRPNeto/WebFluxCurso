package com.example.moisesneto.webfluxcurso.events.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice //Erros do controler
public class ExceptionsControllerHandler {

    @ExceptionHandler(DuplicateKeyException.class) //tratando o erro de duplicidade de email
    ResponseEntity<Mono<StandardError>> duplicateKeyException(
            DuplicateKeyException ex, ServerHttpRequest request
    ) {
        return ResponseEntity.badRequest().body(
                Mono.just(
                        StandardError.builder().timestamp(now())
                                .status(BAD_REQUEST.value())
                                .error(BAD_REQUEST.getReasonPhrase())
                                .message(verifyDuplicateKey(ex.getMessage()))
                                .path(request.getPath().toString())
                                .build()
                ));
    }

    private String verifyDuplicateKey(String message) { //verificar o erro
        return message.contains("email dup key") ? "Email já registrado" : "DUP Key Exception";
    }
}