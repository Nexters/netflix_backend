package me.ziok.application.controller;

import me.ziok.application.model.Account;
import me.ziok.application.payload.AuthResponse;
import me.ziok.application.payload.SocialSignInRequest;
import me.ziok.application.security.JwtTokenProvider;
import me.ziok.application.service.AccountService;
import me.ziok.application.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/oAuth")
public class OAuthController {

    @Autowired
    SocialService socialService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AccountService accountService;



    @PostMapping("/signIn/facebook")
    public ResponseEntity<?> authenticateFacebookAccount(SocialSignInRequest request) {

        Account account = socialService.translateAccessTokenToAccount(request.getToken());
        socialService.saveAccount(account);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        account.getEmail(),
                        account.getProviderId()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new AuthResponse(token, account.getId()));

    }
}
