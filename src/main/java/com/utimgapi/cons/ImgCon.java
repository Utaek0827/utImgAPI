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
@RequestMapping("/api")
public class ImgCon {

    @Value("${ytimg.directory}")
    private String imageUploadDirectory;

    MemberService memberService;

    @Autowired
    public ImgCon(MemberService memberService) {
        this.memberService = memberService;
    }

    // 이미지 한개 보여주기
    // 이미지 중복이름 불러오는 방법

    @GetMapping("/imgList/{m_id}")
    @ResponseBody
    public ResponseEntity userImgList(@PathVariable("m_id") String m_id){
        System.out.println("userImgList");
        System.out.println(memberService.getImgListBym_id(m_id));

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(memberService.getImgListBym_id(m_id));
    }

    @GetMapping("/{serviceNumber}/{imageName}")
    @ResponseBody
    public ResponseEntity viewImg(@PathVariable String serviceNumber, @PathVariable String imageName) throws MalformedURLException {

        //memberService.getImgUNameByoriName(imageName);
        Path imagePath = Paths.get(imageUploadDirectory +"/" +serviceNumber +"/" + imageName);
        System.out.println(imageUploadDirectory +"/" +serviceNumber +"/" + imageName);
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
    public ResponseEntity<String> receiveFile( //이미지 저장용 컨트롤러
            @RequestPart("file") MultipartFile file,
            @RequestPart("service") String service,
            @RequestHeader("Memberid") String memberId,
            @RequestHeader("Key") String imgkey) throws IOException {

        System.out.println("업로드 실행");

        String msg = memberService.getMemberByKey(imgkey, file, service, memberId);
        return ResponseEntity.ok(msg);

    }

    @DeleteMapping("/{img_channame}")
    public ResponseEntity<String> delImg(
            @PathVariable ("img_channame") String img_channame
            ){

        String msg = "삭제 실패";

        System.out.println("삭제:"+img_channame);
        if(memberService.delImg(img_channame) == 1){
            msg = "삭제 완료";
        }

        return ResponseEntity.ok(msg);
    }










}
