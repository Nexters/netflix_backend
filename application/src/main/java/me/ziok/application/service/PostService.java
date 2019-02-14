package me.ziok.application.service;

import me.ziok.application.model.Post;
import me.ziok.application.model.PostSortType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface PostService {

    public Post savePost(Post post);

    public Post loadPost(Long id, String email);

    List<Post> loadOpenPostsWithAccountId(Long accountId);

    List<Post> loadClosedPostsWithAccountId(Long accountId);

    public List<Post> findTop5ByOrderByIdDesc();

    public List<Post> findPostByLimit(Long id, PostSortType sortType);

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd);

    public List<Post> findPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType);

    public void deleteById(Long id);


}