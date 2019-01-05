package me.ziok.application.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Account {

    @Id @GeneratedValue
    private Integer id;

    @NotNull
    private String userName;

    private String phoneNumber;

    private String email;

    private String passWord;

    @OneToMany(mappedBy = "driver")
    private List<Post> posts = new ArrayList<>();

    private Integer successNumber;

    private Integer failNumber;

}
