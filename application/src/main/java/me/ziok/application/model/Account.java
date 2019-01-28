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
    //ToDo: 현재 왜인지 모르겠지만, collum이름에 "email"이 없으면 Unable to create unique key constraint (email) on table account: database column 'email' not found. Make sure that you use the correct column name which depends on the naming strategy in use (it may not be the same as the property name in the entity, especially for relational types) 에러가 뜹니다. 일단은(이 브랜치에서는) collum 이름을 email이라고 진행하겠습니다.
    @NonNull
    @Column(name="email", nullable = false, unique = true)
    @Email
    private String accountId; //아이디(이메일형식)

    @NotBlank
    @JsonIgnore
    @Column
    @NonNull
    private String password;

    @Column(name="nick_name")
    private String nickName; //닉네임

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    private String imageUrl;


    @Enumerated(EnumType.STRING)
    private AuthProviderType provider;

    private String providerId;
}