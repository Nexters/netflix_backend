package me.ziok.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @NotNull
    private String userName;

    private String phoneNumber;

    private String email;

    private String passWord;

    @OneToMany(mappedBy = "driver")
    private List<Post> posts;

    private Integer successNumber;

    private Integer failNumber;

}
