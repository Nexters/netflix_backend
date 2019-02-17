package me.ziok.application.service;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import me.ziok.application.dao.AccountRepository;
import me.ziok.application.dao.CommentRepository;
import me.ziok.application.model.Account;
import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AccountRepository accountRepository;

    public void saveComment(Comment comment, Long postId){
         comment.setAccount(accountRepository.findByEmail(comment.getAccount().getEmail()).orElse(null));

        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);

        if(comment.getParentCommentId()==null) {
            //댓글일 경우 parentCommentId가 본인이기 때문에 저장한 객체의 commnentParentId를 update해야한다.
            comment = commentRepository.save(comment);
            comment.setParentCommentId(comment.getId());
             commentRepository.save(comment);
        }else{
             commentRepository.save(comment);
        }
    }

    //비밀댓글 시 댓글 작성자와 글 작성자만 볼 수 있도록. accountId : 조회하려는 accountId, postAccountId : post작성자accountId
    public List<Comment> findByPostIdOrderByParentCommentIdAscIdAsc(Long accountId, Post post){
        List<Comment> commentList = commentRepository.findByPostIdOrderByParentCommentIdAscIdAsc(post.getId());

        List<BigInteger> accountWriteCommentId = commentRepository.findByAccountIdAndPostId(accountId, post.getId());

        for(Comment c : commentList) {
            if(isCommentSecret(c, accountId, post, accountWriteCommentId)){
                c.setContent("글쓴이와 댓글 작성자만 확인 가능합니다.");
            }
        }
        return commentList;
    }

    //게시글 작성자, 본인이 댓글을 작성했을 때, 본인의 댓글에 대한 비밀답글 검사
    public boolean isCommentSecret(Comment c, Long accountId, Post post, List<BigInteger> accountWriteCommentId){

        Long postAccountId = post.getAccount().getId();

        if(c.isSecret()){
          if (!c.getAccount().getId().equals(accountId) && !postAccountId.equals(accountId) && !accountWriteCommentId.contains(BigInteger.valueOf(c.getParentCommentId()))) {
                return true;
          }
        }

        return false;
    }

    //본인이 쓴 댓글 최신 순
    public List<Comment> findByAccountEmailOrderByIdDesc(String email){
        return commentRepository.findByAccountEmailOrderByIdDesc(email);
    }

    @Override
    public List<Comment> loadByAccountIdOrderByIdDesc(Long accountId) {
        return commentRepository.findByAccountIdOrderByIdDesc(accountId);
    }

    //본인이 쓴 댓글 수
    public Long countByAccountEmail(String email){
        return commentRepository.countByAccountEmail(email);
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public void deleteAllComment(Long postId){
        commentRepository.deleteByPostId(postId);
    }

    public void deleteByAccountEmail(String email){
        commentRepository.deleteByAccountEmail(email);
    }


}