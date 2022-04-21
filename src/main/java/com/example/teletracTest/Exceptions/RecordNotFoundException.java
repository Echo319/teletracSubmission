package com.example.teletracTest.Exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id) {
        super("Could not find Record with Id: " + id);
    }

}
