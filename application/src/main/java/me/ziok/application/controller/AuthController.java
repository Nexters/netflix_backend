package me.ziok.application.controller;

import me.ziok.application.exceptions.BadRequestException;
import me.ziok.application.infra.RequestToAccount;
import me.ziok.application.model.Account;
import me.ziok.application.payload.*;
import me.ziok.application.security.JwtTokenProvider;
import me.ziok.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/check")
    public ResponseEntity<?> checkEmailAvailable(CheckEmailRequest request) {

        return ResponseEntity.ok(new CheckEmailResponse(accountService.isEmailAvailable(request.getEmail())));
    }


    @PostMapping(value = "/signIn")
    public ResponseEntity<?> authenticateAccount(SignInRequest signInRequest) {


        //todo: authService든, tokenService든 만들어서 거기서 처리
        //todo: 인자를 이메일, 패스워드 두개 받는 함수를 만들고, 그 안에서 authenticate 호출하기.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        Account account = accountService.loadAccountByEmail(signInRequest.getEmail());

        return ResponseEntity.ok(new AuthResponse(jwt, account.getId()));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerAccount(SignUpRequest signUpRequest) {


        if (!accountService.isAbleToRegister(signUpRequest.getEmail(), signUpRequest.getNickName())) {
            throw new BadRequestException("Imposible to sign up with the email or nickname");
        }

        Account requestedAccount = RequestToAccount.toAccount(signUpRequest);

        Account account = accountService.saveAccount(requestedAccount);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/api/users/{username}")
                .buildAndExpand(account.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Account registered successfully"));

    }

}
