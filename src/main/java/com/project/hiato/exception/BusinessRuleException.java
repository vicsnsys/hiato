package com.project.hiato.exception;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(String exception){
        super(exception);
    }
}
