package me.ziok.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileDTO {
    private int inCompleteNum;

    private int completeNum;

    private int commentNum;

    private List<Post> incompletePosts;

    private List<Post> completePosts;

    private List<Post> PostsWithComment;
}
