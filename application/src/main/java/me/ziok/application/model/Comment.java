package me.ziok.application.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="comment_group_id")
    private int commentGroupId; //답글에 대한 정보를 나타내는 변수

    @NotBlank
    @Column
    private String content;

    @Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="is_secret")
    private boolean isSecret;

    @ManyToOne
    @JoinColumn(name="account_id_fk")
    private Account account;

    @ManyToOne
    @JoinColumn(name="post_id_fk")
    private Post post;
}