package me.ziok.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Post {

    @Id @GeneratedValue
    private Integer id;

    private Account driver;

    //ToDo: ArrayList등 다른 자료구조 고려하기
    @OneToMany
    private List<Account> passengers;

    private Period period;

    private boolean isOpen;

    private List<Comment> comments;
}
