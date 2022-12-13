package com.example.studentmap.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException(){
        super(String.format("Passwords do not match"));
    }
}
