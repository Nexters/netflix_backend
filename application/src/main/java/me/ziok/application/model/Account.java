package me.ziok.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @NotNull
    private String userName;

    private String phoneNumber;

    private String email;

    private String passWord;

    @OneToOne
    private Post post;

    private Integer successNumber;

    private Integer failNumber;

    public Account(String userName) {
        this.userName = userName;
    }

}
