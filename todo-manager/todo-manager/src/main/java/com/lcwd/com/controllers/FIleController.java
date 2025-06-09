package com.lcwd.com.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping("/upload")
public class FIleController {
    Logger logger= LoggerFactory.getLogger(FIleController.class);
    @PostMapping("/single")
    public String singleFile(@RequestParam("image")MultipartFile file){
        logger.info("Nme : {}",file.getName());
        logger.info("content type {}",file.getContentType());
        logger.info("Original file name {}",file.getOriginalFilename());
        logger.info("file Size {}",file.getSize());
        return "File Test";
    }
    @PostMapping("/multiple")
    public String multipleFile(@RequestParam("images") MultipartFile[] files){
        Arrays.stream(files).forEach(file->{
            logger.info("FIle name {}",file.getOriginalFilename());
            logger.info("File Type {}",file.getContentType());
            System.out.println("--------------------------");
        });
        return "Uploaded";
    }
    @GetMapping("/serve-image")
    public void  serveImageHandler(HttpServletResponse response){
        try{
          FileInputStream inputStrem=  new FileInputStream("images/5th Sem.jpeg");
          response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStrem,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
