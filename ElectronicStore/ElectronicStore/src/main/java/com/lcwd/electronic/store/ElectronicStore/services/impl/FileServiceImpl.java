package com.lcwd.electronic.store.ElectronicStore.services.impl;

import com.lcwd.electronic.store.ElectronicStore.exception.BadApiRequestException;
import com.lcwd.electronic.store.ElectronicStore.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger= LoggerFactory.getLogger(FileService.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        logger.info("this is file name hai = {}",originalFilename);
        String fileName= UUID.randomUUID().toString();
        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension=fileName + extension;
        String fullPathWithFileName=path+fileNameWithExtension;
        logger.info("full image oath {}",fullPathWithFileName);
        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")){
            logger.info("file extention is {}",extension);
            File folder=new File(path);
            if(!folder.exists()){
                boolean created = folder.mkdirs();
                logger.info("Folder created successfully? {}", created);
            }else {
                logger.info("Folder already exists: {}", path);
            }
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        }else {
            throw new BadApiRequestException("File with this "+extension+ " Not Allowed");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
