package com.utimgapi.cons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImgCon {

    @Value("${ytimg.directory}")
    private String imageUploadDirectory;

    @Value("${IMG_KEY}")
    private String imgkey;

    // 이미지 한개 보여주기
    @GetMapping("/{serviceName}/{imageName}")
    @ResponseBody
    public ResponseEntity wewtImg(@PathVariable String serviceName, @PathVariable String imageName) throws MalformedURLException {

        Path imagePath = Paths.get(imageUploadDirectory +"/" +serviceName +"/" + imageName);
        System.out.println(imageUploadDirectory +"/" +serviceName +"/" + imageName);
        System.out.println("imgkey:"+imgkey);

        Resource resource = new UrlResource(imagePath.toUri());

        if(resource.exists()){

            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpg") // 이미지 타입에 맞게 변경
                    .body(resource);
        }else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @RestController
    public class FileReceiveController {

        @PostMapping("/upload")
        public ResponseEntity<String> receiveFile(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) throws IOException {
            String uploadDirectory = System.getProperty("user.dir") + "/images"; // 파일을 저장할 위치
            File storeFile = new File(uploadDirectory + "/" + file.getOriginalFilename());

            // 파일 저장
            file.transferTo(storeFile);

            return ResponseEntity.ok("File received.");
        }
    }






}
