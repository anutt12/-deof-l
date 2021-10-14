package com.odeofil.odeofil.exception;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {

    public InformationExistException(String message) {super(message);}

}
