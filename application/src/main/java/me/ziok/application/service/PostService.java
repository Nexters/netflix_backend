package me.ziok.application.service;

import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import me.ziok.application.model.PostSortType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PostService {

    void savePost(Post post, Long id, MultipartFile[] multipartFiles);

    //public Post updatePost(Post post, Long postId);

    void updatePost(Post post, Long id, String[] imageNames,MultipartFile[] multipartFiles);

    Post loadPost(Long postId, Long accountId);

    List<Post> loadOpenPostsWithAccountId(Long accountId);

    public List<Post> loadTop20ByOrderByIdDesc();

    List<Post> loadClosedPostsWithAccountId(Long accountId);

    Post loadPostByComment(Comment comment);


    List<Post> loadPostByLimit(Long id, PostSortType sortType);

    List<Post> loadPostByConditions(int number, int periodStart, int periodEnd);

    List<Post> loadPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType);

    void deleteById(Long id);

    List<Post> findPostsWithCommentsByAccountId(Long accountId);
}