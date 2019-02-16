package me.ziok.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
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

    @Column(name="recruit_number")
    private int recruitNumber; //모집 인원 수

    @Column
    private int fee; //요금

    @Column
    private String membership; //요금제

    @CreationTimestamp
    @Column(name="create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name="is_open_flag")
    private boolean isOpen=true;

    @Column(nullable=false)
    private Long hits = Long.valueOf(0);

    @ManyToOne
    @JoinColumn(name="account_id_fk",  updatable = false)
    private Account account;

    @OneToMany
    @JoinColumn(name="post_id_fk", updatable = false)
    private List<Comment> comment;

    @OneToMany
    @JoinColumn(name="post_id_fk", updatable = false)
    private List<Image> img;

/*
    @Transient
    private Map<String, String> imageMap;
*/


}