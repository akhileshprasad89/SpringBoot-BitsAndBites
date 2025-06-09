package com.lcwd.electronic.store.ElectronicStore.exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        System.out.println("Resource Not Found");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
