package com.utimgapi.cons;

import com.utimgapi.mapper.MemberMapper;
import com.utimgapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;

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

    MemberService memberService;

    @Autowired
    public ImgCon(MemberService memberService) {
        this.memberService = memberService;
    }

    // 이미지 한개 보여주기
    @GetMapping("/{serviceName}/{imageName}")
    @ResponseBody
    public ResponseEntity wewtImg(@PathVariable String serviceName, @PathVariable String imageName) throws MalformedURLException {

        Path imagePath = Paths.get(imageUploadDirectory +"/" +serviceName +"/" + imageName);
        System.out.println(imageUploadDirectory +"/" +serviceName +"/" + imageName);
        System.out.println(memberService.toString());
        System.out.println(memberService.memberList());

        Resource resource = new UrlResource(imagePath.toUri());

        if(resource.exists()){

            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpg") // 이미지 타입에 맞게 변경
                    .body(resource);
        }else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<String> receiveFile(
            @RequestPart("file") MultipartFile file,
            @RequestPart("service") String service,
            @RequestPart("memberId") String memberId,
            @RequestPart("key") String imgkey) throws IOException {


        if(memberService.getMemberByKey(imgkey, file, service, memberId) == null){
            return ResponseEntity.ok("유효하지 않은 키");
        }else{
            return ResponseEntity.ok("File received.");
        }
    }










}
