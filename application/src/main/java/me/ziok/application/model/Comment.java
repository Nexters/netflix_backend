package me.ziok.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="parent_comment_id")
    private int parentCommentId; //답글에 대한 정보를 나타내는 변수

    @NotBlank
    @Column
    private String content;

    @Column(name="create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name="is_secret")
    private boolean isSecret;

    @ManyToOne
    @JoinColumn(name="account_id_fk", updatable = false)
    private Account account;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id_fk", updatable = false)
    private Post post;
}