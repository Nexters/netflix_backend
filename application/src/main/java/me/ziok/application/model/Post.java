package me.ziok.application.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

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

    @Column(name="create_date", updatable=false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name="is_open_flag")
    private boolean isOpen;

    @ManyToOne
    @JoinColumn(name="account_id_fk")
    private Account account;

   /*
    public Post(String postName) {
        this.postName = postName;
    }
    */
}