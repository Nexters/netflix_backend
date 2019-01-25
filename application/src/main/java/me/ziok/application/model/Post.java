package me.ziok.application.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="post_name")
    private String postName;

    @NotBlank
    @Column
    private String content;

    @Column
    private int period; //모집 기간

    @Column
    private int number; //모집 인원 수

    @Column
    private int fee; //요금

    @Column
    private String membership; //요금제

    @Column(name="create_date", updatable = false)
   // @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="is_open_flag")
    private boolean isOpen;

    @ManyToOne
    @JoinColumn(name="account_id_fk",  updatable = false)
    private Account account;

    @OneToMany
    @JoinColumn(name="post_id_fk", updatable = false)
    private List<Comment> comment;

}