package me.ziok.application.dao;

import me.ziok.application.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //게시글에서 댓글 order by 조회
    public List<Comment> findByPostIdOrderByParentCommentIdAscIdAsc(long postId);

    //내가 쓴 댓글(최신순) 조회
    public List<Comment> findByAccountIdOrderByIdDesc(long accountId);

    //내가 쓴 댓글 개수
    public Long countByAccountId(long accountId);

    //게시글 삭제 시 전에 댓글 먼저 모두 삭제
    public void deleteByPostId(long postId);

    //한 사용자가 작성한 댓글 모두 삭제
    public void deleteByAccountId(long accountId);

}
