package me.ziok.application.dao;

import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PostRepository postRepository;


    @Test
    public void createComment(){
        Comment comment1 = new Comment();
        Account account = accountRepository.findById(20).get();//test를 위해 Account테이블에 레코드를 넣어주었음.
        Post post = postRepository.findById(17).get();//test를 위해 Post테이블에 레코드를 넣어주었음.

        comment1.setContent("결제는 어떻게 되는건가요");
        comment1.setCreateDate(LocalDateTime.now());
        comment1.setAccount(account);
        comment1.setPost(post);

        Comment comment2 = commentRepository.save(comment1);
        assertEquals(comment1, comment2);
    }

    @Test
    public void updateComment(){
        Comment comment1 = new Comment();
        Account account = accountRepository.findById(20).get();//test를 위해 Account테이블에 레코드를 넣어주었음.
        Post post = postRepository.findById(17).get();//test를 위해 Post테이블에 레코드를 넣어주었음.

        comment1.setContent("결제는 어떻게 되는건가요");
        comment1.setCreateDate(LocalDateTime.now());
        comment1.setAccount(account);
        comment1.setPost(post);

        Comment comment2 = commentRepository.save(comment1);
        comment2.setContent("결제는 오픈카톡방에서 진행되는건가요?");

        comment1 = commentRepository.save(comment2);
        assertEquals(comment1, comment2);
    }

    @Test
    public void deleteComment(){
        Account account = accountRepository.findById(20).get();//test를 위해 Account테이블에 레코드를 넣어주었음.
        Post post = postRepository.findById(17).get();//test를 위해 Post테이블에 레코드를 넣어주었음.

        Comment comment1 = new Comment();
        comment1.setContent("결제는 어떻게 되는건가요");
        comment1.setCreateDate(LocalDateTime.now());
        comment1.setAccount(account);
        comment1.setPost(post);

        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("3개월로 하실 생각은 없으신가요?");
        comment2.setCreateDate(LocalDateTime.now());
        comment2.setAccount(account);
        comment2.setPost(post);

        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setContent("한 자리밖에 안남은건가요?");
        comment3.setCreateDate(LocalDateTime.now());
        comment3.setAccount(account);
        comment3.setPost(post);

        commentRepository.save(comment3);

        List<Comment> list = commentRepository.findAll();

        for(Comment c : list)
            commentRepository.delete(c);

        assertTrue(list.size()>0);

        list = commentRepository.findAll();
        assertTrue(list.size()==0);

    }
}
