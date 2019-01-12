package me.ziok.application.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="user_id")
    private String userId; //아이디

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
}