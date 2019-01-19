package me.ziok.application.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="account_id", nullable = false, unique = true)
    @Email
    private String accountId; //아이디(이메일형식)

    @NotBlank
    @JsonIgnore
    @Column
    private String password;

    @NotBlank
    @Column(name="nick_name", nullable = false)
    private String nickName; //닉네임

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProviderType provider;

    private String providerId;
}