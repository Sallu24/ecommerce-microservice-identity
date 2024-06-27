package com.microservice_ecommerce.identity.jwt;

public class JwtInvalidException extends RuntimeException {

    public JwtInvalidException(String message) {
        super(message);
    }

}
