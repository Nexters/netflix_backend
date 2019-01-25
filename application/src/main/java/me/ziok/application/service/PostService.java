package me.ziok.application.service;

import me.ziok.application.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    public void savePost(Post post);

    public Post getPost(int id);

    public List<Post> getPostList();

    public void deletePost(Post post);
}
