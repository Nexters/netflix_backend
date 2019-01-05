package me.ziok.application.model;

import lombok.Data;

<<<<<<< HEAD
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
>>>>>>> ce0cc4dcd20ec2f9491e16c35b22a1dda9be5b0f
import java.util.List;

@Entity
@Data
public class Account {

    @Id @GeneratedValue
    private Integer id;

    @NotNull
    private String userName;

<<<<<<< HEAD
    private String phoneNumber;

    private String email;

    private String passWord;

    @OneToMany(mappedBy = "driver")
    private List<Post> posts = new ArrayList<>();

    private Integer successNumber;

    private Integer failNumber;
=======
    private String email;

    private String passWord;

    @ManyToOne
    private List<Post> posts;
>>>>>>> ce0cc4dcd20ec2f9491e16c35b22a1dda9be5b0f

}
