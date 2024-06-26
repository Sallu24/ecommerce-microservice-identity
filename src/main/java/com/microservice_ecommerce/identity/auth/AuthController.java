package com.microservice_ecommerce.identity.auth;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    protected AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerRegisterDTO customerRegisterDTO) {
        return authenticationService.register(customerRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody CustomerLoginDTO customerLoginDTO) {
        return authenticationService.login(customerLoginDTO);
    }

    @PostMapping("/validate")
    public Long validateTokenAndGetUserDetails(@RequestParam("token") String token) {
        return authenticationService.validateTokenAndGetUserDetails(token);
    }

}
