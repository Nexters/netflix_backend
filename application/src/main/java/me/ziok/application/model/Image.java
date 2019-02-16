package me.ziok.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="img_name")
    private String imgName;

    @NotBlank
    @Column(name="img_url")
    private String imgUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id_fk", updatable = false)
    private Post post;
}
