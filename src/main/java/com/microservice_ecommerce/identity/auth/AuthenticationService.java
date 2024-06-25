package com.microservice_ecommerce.identity.auth;

import com.microservice_ecommerce.identity.auth.clients.UserClient;
import com.microservice_ecommerce.identity.auth.external.User;
import com.microservice_ecommerce.identity.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    protected UserClient userClient;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserClient userClient, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> register(CustomerRegisterDTO customerRegisterDTO) {
        String email = customerRegisterDTO.getEmail();
        String password = customerRegisterDTO.getPassword();
        String decodedPassword = passwordEncoder.encode(password);
        customerRegisterDTO.setPassword(decodedPassword);

        boolean exists = userClient.userExistsByEmail(email);
        System.out.println(exists);

        if (!exists) {
            userClient.createUser(customerRegisterDTO);
        }

        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> login(CustomerLoginDTO customerLoginDTO) {
        String email = customerLoginDTO.getEmail();
        String password = customerLoginDTO.getPassword();

        User user = userClient.getUserByEmail(email);

        if (passwordEncoder.matches(password, user.getPassword())) {
            String jwtToken = jwtUtils.generateJwtToken(user);

            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone(),
                    jwtToken
            );

            return ResponseEntity.ok(responseDTO);
        } else {
            throw new CredentialsMismatchException("Incorrect password ");
        }
    }

    public boolean validateToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }

    public String extractToken(String token) {
        return jwtUtils.getUserNameFromJwtToken(token);
    }

}
