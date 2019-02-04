package me.ziok.application.service;

import me.ziok.application.model.Comment;
import me.ziok.application.model.Post;

import java.util.List;

public interface CommentService {

    public void saveComment(Comment comment, long postId);

    //게시판 load시 댓글 order by 조회
    public List<Comment> findByPostIdOrderByParentCommentIdAscIdAsc(long accountId, Post post);

    //본인이 쓴 댓글 최신 순
    public List<Comment> findByAccountIdOrderByIdDesc(long accountId);

    //본인이 쓴 댓글 수
    public long countByAccountId(long accountId);

    public void deleteComment(Comment comment);

    public void deleteAllComment(long postId);

    public void deleteByAccountId(long accountId);
}