package com.example.project.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class DownloadService {

    public String upload(MultipartFile file) {

        String dirName = File.separator + "temp";
        File dir = new File(dirName);

        if (!dir.exists())
            dir.mkdir();

        // Pegando o lugar em que foi salvo
        String filename = file.getOriginalFilename();
        File destFile = new File(dir + File.separator + filename);

        if (!dir.exists())
            dir.mkdir();

        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return destFile.getAbsolutePath();
    }

}