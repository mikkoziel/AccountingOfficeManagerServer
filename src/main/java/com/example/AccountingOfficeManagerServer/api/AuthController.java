package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.security.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager,
                   JwtTokenUtil jwtTokenUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody @Valid User request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add(HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateAccessToken(user));
            responseHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                    HttpHeaders.AUTHORIZATION);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
