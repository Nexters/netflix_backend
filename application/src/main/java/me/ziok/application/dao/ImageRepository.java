package me.ziok.application.dao;

import me.ziok.application.model.Comment;
import me.ziok.application.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {

    public List<Image> findByPostId(Long postId);

    public void deleteByImgName(String ImgName);

    public void deleteByPostId(Long postId);


}
