package me.ziok.application.service;

import me.ziok.application.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageService {

    public void fileUpload(MultipartFile[] multipartFiles, Long postId);

    public void imageUpdate(String[] imageNames, Long postId);

    public void deleteImage(Long postId);

}
