package me.ziok.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
public class CommentSaveDTO {

    private Comment comment;

    private long postId;
}
