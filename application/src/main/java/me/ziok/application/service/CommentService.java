package me.ziok.application.service;

import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {

    public void saveComment(Comment comment, Long postId, Long accountId);

    //게시판 load시 댓글 order by 조회 - postServiceImpl에서 accountId보내줌.
    public List<Comment> loadByPostIdOrderByParentCommentIdAscIdAsc(Long accountId, Post post);

    //비밀댓글 여부를 알려주는 메서드.
    public boolean isCommentSecret(Comment c, Long accountId, Post post,List<BigInteger> accountWriteCommentId);

    //본인이 쓴 댓글 최신 순
    public List<Comment> loadByAccountEmailOrderByIdDesc(String email);

    List<Comment> loadByAccountIdOrderByIdDesc(Long accountId);

    //본인이 쓴 댓글 수
    public Long countByAccountEmail(String email);

    public void deleteComment(Long id);

    public void deleteAllComment(Long postId);

    public void deleteByAccountEmail(String email);
}