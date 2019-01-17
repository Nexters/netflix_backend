package me.ziok.application.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
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

    //todo: 소셜 로그인과 연동을 하니, 회원가입을 해서 만드는 아이디도 이메일로 하는 게 어떤지 논의하기
    @NotBlank
    @Column(name="account_id", nullable = false, unique = true)
    private String accountId; //아이디

    @NotBlank
    @JsonIgnore
    @Column
    private String password;

    @NotBlank
    @Column(name="nick_name", nullable = false)
    private String nickName; //닉네임

    @NotBlank
    @Column(name="phone_number")
    private String phoneNumber;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProviderType provider;

    private String providerId;
}