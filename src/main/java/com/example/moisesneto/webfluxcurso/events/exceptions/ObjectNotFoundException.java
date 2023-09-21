package com.example.moisesneto.webfluxcurso.events.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message){
        super(message);
    }
}
