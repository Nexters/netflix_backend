package me.ziok.application.service;

import me.ziok.application.model.Post;
import me.ziok.application.model.PostSortType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PostService {

    public void savePost(Post post, String email, MultipartFile[] multipartFiles);

    //public Post updatePost(Post post, Long postId);

    public void updatePost(Post post, String email, String[] imageNames,MultipartFile[] multipartFiles);

        public Post loadPost(Long id, String email);

    public List<Post> findTop5ByOrderByIdDesc();

    public List<Post> findPostByLimit(Long id, PostSortType sortType);

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd);

    public List<Post> findPostByConditions(Long id, int number, int periodStart, int periodEnd, PostSortType sortType);

    public void deleteById(Long id);

}