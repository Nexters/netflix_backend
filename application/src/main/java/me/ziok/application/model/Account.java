package me.ziok.application.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="account_id", nullable = false, unique = true)
    private String accountId; //아이디

    @NotBlank
    @Column
    private String password;

    @NotBlank
    @Column(name="account_name")
    private String accountName; //닉네임

    @NotBlank
    @Column(name="phone_number")
    private String phoneNumber;

    @NotBlank
    @Column
    private String email;

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProviderType provider;

    private String providerId;

}