package me.ziok.application.model;

import lombok.Data;

<<<<<<< HEAD
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
=======
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
>>>>>>> bd256af276800dde4207162035126e8c727c484c
import java.util.List;

@Entity
@Data
public class Account {

    @Id @GeneratedValue
    private Integer id;

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
