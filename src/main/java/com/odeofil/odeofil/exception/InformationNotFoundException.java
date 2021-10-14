package com.odeofil.odeofil.exception;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class InformationNotFoundException extends RuntimeException {

    public InformationNotFoundException(String message) {super(message);}

}
