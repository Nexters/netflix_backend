package me.ziok.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Comment {

    @Id @GeneratedValue
    private Integer id;

    //ToDo: String 형식이 괜찮을 지
    private String contents;
}
