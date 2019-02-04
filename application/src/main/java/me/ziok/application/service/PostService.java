package me.ziok.application.service;

import me.ziok.application.model.Post;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface PostService {

    public void savePost(Post post);

    public Post getPost(int id, long accountId);

    public List<Post> getPostList();

    public List<Post> findTop5ByOrderByIdDesc();

    public List<Post> findPostByLimit(int id);

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd);

    public List<Post> findPostByConditions(int id, int number, int periodStart, int periodEnd);

    public void deletePost(Post post);
}
