package me.ziok.application.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="rewrite_comment_id")
    private int rewriteCommentId; //답글에 대한 정보를 나타내는 변수

    @NotBlank
    @Column
    private String content;

    @Column(name="create_date", updatable=false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="account_id_fk")
    private Account account;

    @ManyToOne
    @JoinColumn(name="post_id_fk")
    private Post post;
}