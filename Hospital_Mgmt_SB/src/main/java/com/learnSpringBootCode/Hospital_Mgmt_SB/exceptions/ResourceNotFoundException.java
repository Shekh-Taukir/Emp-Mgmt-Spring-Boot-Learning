package com.learnSpringBootCode.Hospital_Mgmt_SB.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
