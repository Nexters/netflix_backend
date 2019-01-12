package me.ziok.application.dao;

import me.ziok.application.model.Account;
import me.ziok.application.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void createPost(){
        Post post1 = new Post();
        Account account = accountRepository.findById(19).get(); //test를 위해 Account테이블에 레코드를 넣어주었음.

        post1.setPostName("넷플하실분구해요~");
        post1.setContent("넷플릭스 일단 1개월만 하실 분 구합니당");
        post1.setPeriod(1);
        post1.setNumber(3);
        post1.setFee(4950);
        post1.setCreateDate(LocalDateTime.now()); //출력 형태 변환 시 : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        post1.setOpen(true);
        post1.setAccount(account);

        Post post2 = postRepository.save(post1);

        assertEquals(post1, post2);
    }

    @Test
    public void updatePost(){
        Post post1 = new Post();
        Account account = accountRepository.findById(19).get();

        post1.setPostName("넷플하실분구해요~");
        post1.setContent("넷플릭스 일단 1개월만 하실 분 구합니당");
        post1.setPeriod(1);
        post1.setNumber(3);
        post1.setFee(4950);
        post1.setCreateDate(LocalDateTime.now()); //출력 형태 변환 시 : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        post1.setOpen(true);
        post1.setAccount(account);

        Post post2 = postRepository.save(post1);

        post2.setPostName("넷플릭스 3개월 구합니다.");
        post2.setContent("넷플릭스 3개월만 하실 분 구합니다!!");
        post2.setPeriod(3);
        post2.setNumber(2);
        post2.setFee(3250);
        post2.setCreateDate(LocalDateTime.now());
        post2.setOpen(false);

        post1 = postRepository.save(post2);
        assertEquals(post1, post2);
    }

    @Test
    public void deletePost() {
        Account account = accountRepository.findById(19).get();

        Post post1 = new Post();
        post1.setPostName("넷플하실분구해요~");
        post1.setContent("넷플릭스 일단 1개월만 하실 분 구합니당");
        post1.setPeriod(1);
        post1.setNumber(3);
        post1.setFee(4950);
        post1.setCreateDate(LocalDateTime.now()); //출력 형태 변환 시 : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        post1.setOpen(true);
        post1.setAccount(account);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setPostName("넷플1분구해요~");
        post2.setContent("넷플릭스 일단 1개월 구해요");
        post2.setPeriod(1);
        post2.setNumber(1);
        post2.setFee(5550);
        post2.setCreateDate(LocalDateTime.now());
        post2.setOpen(true);
        post2.setAccount(account);
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setPostName("넷플2분구해요~");
        post3.setContent("넷플릭스 일단 3개월 구해요");
        post3.setPeriod(3);
        post3.setNumber(2);
        post3.setFee(4000);
        post3.setCreateDate(LocalDateTime.now());
        post3.setOpen(true);
        post3.setAccount(account);
        postRepository.save(post3);

        List<Post> list = postRepository.findAll();
        assertTrue(list.size() > 0);

        for (Post p : list)
            postRepository.delete(p);

        list = postRepository.findAll();
        assertTrue(list.size()==0); //db에 저장되어있는 레코드가 모두 삭제된 것을 확인.

    }
}
