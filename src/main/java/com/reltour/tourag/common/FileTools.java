package com.reltour.tourag.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

@Component
public class FileTools {

    @Value("${upload.path}")
    private String uploadPath;


    public static void deleteFile(String filename) throws IOException, URISyntaxException {
        //Files.delete(filePath);
    }

    public String extractFilename(MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);

        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }
}
