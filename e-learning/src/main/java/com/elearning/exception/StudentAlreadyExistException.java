package com.elearning.exception;

public class StudentAlreadyExistException extends RuntimeException {
    public StudentAlreadyExistException(String msg) {
        super(msg);
    }
}
