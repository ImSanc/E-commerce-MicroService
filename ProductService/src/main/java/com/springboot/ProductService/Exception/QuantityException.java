package com.springboot.ProductService.Exception;

import lombok.Data;

@Data
public class QuantityException extends RuntimeException{

    private String errorCode;

    public QuantityException(String errorMessage,String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
