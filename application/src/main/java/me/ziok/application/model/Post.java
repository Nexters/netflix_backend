package me.ziok.application.model;

import java.util.List;

//ToDo: JPA 적용
public class Post {

    private Integer id;

    private Account driver;

    //ToDo: ArrayList등 다른 자료구조 고려하기
    private List<Account> passengers;

    private Period period;

    private boolean isOpen;

    private List<Comment> comments;
}
