package me.ziok.application.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Data
public class Post {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Post name is mandatory")
    private String postName;

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

    private SimpleDateFormat createdDate;

    private SimpleDateFormat updatedDate;
}
