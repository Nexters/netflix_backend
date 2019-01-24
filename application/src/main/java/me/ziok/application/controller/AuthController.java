package me.ziok.application.controller;

import me.ziok.application.dao.AccountRepository;
import me.ziok.application.model.Account;
import me.ziok.application.payload.ApiResponse;
import me.ziok.application.payload.JwtAuthenticationResponse;
import me.ziok.application.payload.LoginRequest;
import me.ziok.application.payload.SignUpRequest;
import me.ziok.application.security.JwtTokenProvider;
import me.ziok.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AccountService accountService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateAccount(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (!accountService.isAbleToRegister(signUpRequest.getEmail(), signUpRequest.getNickName())) {

        }

        Account account = accountService.saveAccount(signUpRequest.getEmail());


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/api/users/{username}")
                .buildAndExpand(account.getAccountId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Account registered successfully"));

    }

}
