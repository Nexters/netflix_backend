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
@RequiredArgsConstructor
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NonNull
    @Column(name="email", nullable = false, unique = true)
    @Email
    private String email; //아이디(이메일형식)

    @NonNull
    @NotBlank
    @JsonIgnore
    @Column
    private String password;

    @Column(name="nick_name")
    private String nickName; //닉네임

    @Column(name="authentication_code")
    private String authenticationCode; //비밀번호 찾기 시 전송되는 인증코드

    @Enumerated(EnumType.STRING)
    private AuthProviderType providerType;

    private String providerId;
}