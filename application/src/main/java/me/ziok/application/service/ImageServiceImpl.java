package me.ziok.application.service;

import com.amazonaws.util.IOUtils;
import me.ziok.application.dao.ImageRepository;
import me.ziok.application.model.Image;
import me.ziok.application.model.Post;
import me.ziok.application.util.S3Util;
import me.ziok.application.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    S3Util s3Util;

    @Autowired
    ImageRepository imageRepository;

    private String uploadPath = "postImage";//s3 폴더명

    //CREATE
    //파일 업로드
    public void fileUpload(MultipartFile[] multipartFiles, Long postId) {
        Post post = new Post();
        post.setId(postId);
        for(MultipartFile file : multipartFiles) {
            if (file.getSize() <= 0) return;
            Image img = new Image();
            img.setPost(post);
            try {
                img.setImgName(UploadFileUtils.uploadFile("postImage", file.getOriginalFilename(), file.getBytes()));
                img.setImgUrl(new URL(s3Util.getFileURL(img.getImgName())).toString());
                imageRepository.save(img);
            }catch(Exception exception){
                System.out.println(exception.toString());
            }
        }
    }

    //UPDATE
    public void imageUpdate(String[] imageNames, Long postId){
        List<Image> imageList = imageRepository.findByPostId(postId);

        List<String> imageNamesList = Arrays.asList(imageNames);

        for(Image i : imageList)
            if(!imageNamesList.contains(i.getImgName())) {
                imageRepository.deleteByImgName(i.getImgName());
                s3Util.fileDelete("postImage"+i.getImgName());
            }
    }

    //DELETE
    //aws s3와 db에 저장된 해당 postId의 파일들 삭제
    public void deleteImage(Long postId){
        List<Image> imageList = imageRepository.findByPostId(postId);
        for(Image img : imageList)
            s3Util.fileDelete("postImage"+img.getImgName());
        imageRepository.deleteByPostId(postId);
    }



}
