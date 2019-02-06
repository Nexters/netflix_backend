package me.ziok.application.dao;

import me.ziok.application.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //게시글에서 댓글 order by 조회
    public List<Comment> findByPostIdOrderByParentCommentIdAscIdAsc(Long postId);

    //특정 게시글에서 내가 쓴 댓글 id 조회
    @Query(value="select id from comment where account_id_fk = ?1 and post_id_fk = ?2",nativeQuery = true)
    public List<BigInteger> findByAccountIdAndPostId(Long accountId , Long postId);

    //내가 쓴 댓글(최신순) 조회
    public List<Comment> findByAccountEmailOrderByIdDesc(String email);

    //내가 쓴 댓글 개수
    public Long countByAccountEmail(String email);

    //게시글 삭제 시 전에 댓글 먼저 모두 삭제
    public void deleteByPostId(Long postId);

    //한 사용자가 작성한 댓글 모두 삭제
    public void deleteByAccountEmail(String email);

    //댓글 삭제
    public void deleteById(Long id);

}
