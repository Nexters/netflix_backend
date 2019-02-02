package me.ziok.application.service;

import me.ziok.application.dao.PostRepository;
import me.ziok.application.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    public void savePost(Post post){
        postRepository.save(post);
    }

    public Post getPost(int id){
        return postRepository.findById((long)id).get();
    }

    public List<Post> findTop5ByOrderByIdDesc(){
        return postRepository.findTop5ByOrderByIdDesc();
    }

    public List<Post> findPostByLimit(int id){
        return postRepository.findPostByLimit(id);
    }

    public List<Post> findPostByConditions(int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(number, periodStart, periodEnd);
    }

    public List<Post> findPostByConditions(int id, int number, int periodStart, int periodEnd){
        return postRepository.findPostByConditions(id, number, periodStart, periodEnd);
    }

    public List<Post> getPostList(){
        return postRepository.findAll();
    }

    public void deletePost(Post post){
        postRepository.delete(post);
    }
}