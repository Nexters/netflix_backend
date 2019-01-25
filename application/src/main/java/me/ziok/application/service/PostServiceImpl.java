package me.ziok.application.service;

import me.ziok.application.dao.PostRepository;
import me.ziok.application.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Post> getPostList(){
        return postRepository.findAll();
    }

    public void deletePost(Post post){
        postRepository.delete(post);
    }
}