package me.ziok.application.service;

import me.ziok.application.dao.CommentRepository;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void saveComment(Comment comment, long postId){
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);

        if(comment.getParentCommentId()==null) {
            //댓글일 경우 parentCommentId가 본인이기 때문에 저장한 객체의 commnentParentId를 update해야한다.
            Comment comment1 = commentRepository.save(comment);
            comment1.setParentCommentId(comment1.getId());
            commentRepository.save(comment);
        }else{
            commentRepository.save(comment);
        }
    }
    //post에서 테스트
    public List<Comment> findByPostIdOrderByParentCommentIdAscIdAsc(long accountId, Post post){
        List<Comment> commentList = commentRepository.findByPostIdOrderByParentCommentIdAscIdAsc(post.getId());
        //비밀댓글 시 댓글 작성자와 글 작성자만 볼 수 있도록.
        for(Comment c : commentList){
            if(c.isSecret()){//비밀댓글일 때
                if(!c.getAccount().getId().equals(accountId) && !post.getAccount().getId().equals(accountId)){
                    System.out.println("비밀댓글"+c.getAccount().getId());
                    c.setContent("글쓴이와 댓글 작성자만 확인 가능합니다.");
                }
            }
        }
        return commentList;
    }

    //본인이 쓴 댓글 최신 순
    public List<Comment> findByAccountIdOrderByIdDesc(long accountId){
        return commentRepository.findByAccountIdOrderByIdDesc(accountId);
    }

    //본인이 쓴 댓글 수
    public long countByAccountId(long accountId){
        return commentRepository.countByAccountId(accountId);
    }

    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }

    public void deleteAllComment(long postId){
        commentRepository.deleteByPostId(postId);
    }

    public void deleteByAccountId(long accountId){
        commentRepository.deleteByAccountId(accountId);
    }


}