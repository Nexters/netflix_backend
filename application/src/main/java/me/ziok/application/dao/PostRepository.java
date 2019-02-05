package me.ziok.application.dao;

import me.ziok.application.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    //최근게시글 5개 리턴
    List<Post> findTop5ByOrderByIdDesc();

    //마지막 페이지 번호 부여 시 그 다음 글 5개 리턴
    @Query(value = "SELECT * FROM post WHERE id < ?1 ORDER BY id DESC limit 5", nativeQuery = true)
    List<Post> findPostByLimit(int id);
    //남은 인원수
    @Query(value = "SELECT * FROM post WHERE id < ?1 ORDER BY number ASC, id DESC limit 5", nativeQuery = true)
    List<Post> findPostByLimitOrderByNumber(int id);
    //저가순
    @Query(value = "SELECT * FROM post WHERE id < ?1 ORDER BY fee ASC, id DESC limit 5", nativeQuery = true)
    List<Post> findPostByLimitOrderByFee(int id);


    //number : 구하는 사람 수, periodStart ~ periodEnd : 함께 시청할 기간
    @Query(value="SELECT * FROM post WHERE NUMBER = ?1 AND (period BETWEEN ?2 AND ?3) ORDER BY id DESC limit 5", nativeQuery = true)
    List<Post> findPostByConditions(int number, int periodStart, int periodEnd);

    //id : 마지막 글 번호 , number : 구하는 사람 수, periodStart ~ periodEnd : 함께 시청할 기간
    @Query(value="SELECT * FROM post WHERE id < ?1 AND NUMBER = ?2 AND (period BETWEEN ?3 AND ?4) ORDER BY id DESC limit 5", nativeQuery = true)
    List<Post> findPostByConditions(int id, int number, int periodStart, int periodEnd);

    //남은인원수
    @Query(value="SELECT * FROM post WHERE id < ?1 AND NUMBER = ?2 AND (period BETWEEN ?3 AND ?4) ORDER BY number ASC, id DESC limit 5", nativeQuery = true)
    List<Post> findPostByConditionsOrderByNumber(int id, int number, int periodStart, int periodEnd);
    //저가순
    @Query(value="SELECT * FROM post WHERE id < ?1 AND NUMBER = ?2 AND (period BETWEEN ?3 AND ?4) ORDER BY fee ASC, id DESC limit 5", nativeQuery = true)
    List<Post> findPostByConditionsOrderByFee(int id, int number, int periodStart, int periodEnd);
}