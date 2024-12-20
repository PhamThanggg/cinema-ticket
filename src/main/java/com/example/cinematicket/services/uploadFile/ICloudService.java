package com.example.cinematicket.services.uploadFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ICloudService {
    List<Map> uploadFiles(List<MultipartFile> files) throws IOException;

    Map uploadVideo(MultipartFile file) throws IOException;

    void deleteImage(String publicId) throws IOException;

    void deleteVideo(String publicId) throws IOException;
}
