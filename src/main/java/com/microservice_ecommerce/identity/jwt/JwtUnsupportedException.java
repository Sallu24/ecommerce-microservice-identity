package com.microservice_ecommerce.identity.jwt;

public class JwtUnsupportedException extends RuntimeException {

    public JwtUnsupportedException(String message) {
        super(message);
    }

}
