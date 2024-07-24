package com.example.cinematicket.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CloudService implements ICloudService{
    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    private static final Set<String> IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "bmp");
    private static final Set<String> VIDEO_EXTENSIONS = Set.of("mp4", "avi", "mov", "wmv", "flv", "mkv");

    private static final int MAX_SIZE_IMAGE = 10 * 1024 * 1024;

    private Cloudinary cloudinary;

    @PostConstruct
    public void init() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    @Override
    public List<Map> uploadFiles(List<MultipartFile> files) throws IOException {
        List<Map> uploadResults = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!isImage(file)) {
                throw new IllegalArgumentException("File is not a valid image: " + file.getOriginalFilename());
            }
            if (file.getSize() > MAX_SIZE_IMAGE) {
                throw new IllegalArgumentException("File size exceeds " + MAX_SIZE_IMAGE + " : " + file.getOriginalFilename());
            }
        }

        for(MultipartFile file : files){
            Map uploadResult = cloudinary.uploader().upload(file.getBytes()
                    ,ObjectUtils.asMap("folder", "cinema/movieImages"
                            ,"unique_filename", true)
            );
            uploadResults.add(uploadResult);
        }

        return uploadResults;
    }

    @Override
    public Map uploadVideo(MultipartFile file) throws IOException {
        if (!isVideo(file)) {
            throw new IllegalArgumentException("File is not a valid video: " + file.getOriginalFilename());
        }
        return cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.
                        asMap("resource_type", "video",
                                "folder", "cinema/movieVideo")
                );
    }

    private boolean isImage(MultipartFile file) {
        String extension = getExtension(file.getOriginalFilename());
        return IMAGE_EXTENSIONS.contains(extension != null ? extension.toLowerCase() : null);
    }

    private boolean isVideo(MultipartFile file) {
        String extension = getExtension(file.getOriginalFilename());
        return VIDEO_EXTENSIONS.contains(extension != null ? extension.toLowerCase() : null);
    }

    private String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1);
        }
        return "";
    }
}
