package me.ziok.application.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Post {

    @Id @GeneratedValue
    private Integer id;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account driver;

    //ToDo: ArrayList등 다른 자료구조 고려하기
    @OneToMany
    private List<Account> passengers;

    private Period period;

    private boolean isOpen;

    @OneToMany
    private List<Comment> comments;
}
