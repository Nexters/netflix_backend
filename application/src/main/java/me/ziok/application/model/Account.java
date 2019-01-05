package me.ziok.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
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

    @ManyToOne
    private List<Post> posts;

    private Integer successNumber;

    private Integer failNumber;

}
