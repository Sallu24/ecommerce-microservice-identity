package com.microservice_ecommerce.identity.jwt;

public class JwtExpiredException extends RuntimeException {

    public JwtExpiredException(String message) {
        super(message);
    }

}
