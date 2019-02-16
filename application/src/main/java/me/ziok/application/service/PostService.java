package me.ziok.application.service;

import me.ziok.application.model.Post;
import me.ziok.application.model.PostSortType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PostService {

    void savePost(Post post, String email, MultipartFile[] multipartFiles);

    //public Post updatePost(Post post, Long postId);

    void updatePost(Post post, String email, String[] imageNames,MultipartFile[] multipartFiles);

    Post loadPost(Long id, String email);

    List<Post> loadOpenPostsWithAccountId(Long accountId);

    List<Post> loadClosedPostsWithAccountId(Long accountId);

    List<Post> findTop5ByOrderByIdDesc();

    List<Post> findPostByLimit(Long id, PostSortType sortType);

    List<Post> findPostByConditions(int number, int periodStart, int periodEnd);

    List<Post> findPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType);

    void deleteById(Long id);
}