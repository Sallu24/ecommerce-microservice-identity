package com.microservice_ecommerce.identity.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JWTNotValidatedException extends RuntimeException {

    public JWTNotValidatedException(String message) {
        super(message);
    }

}
