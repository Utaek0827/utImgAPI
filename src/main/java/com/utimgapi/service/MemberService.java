package com.utimgapi.service;

import com.utimgapi.mapper.MemberMapper;
import com.utimgapi.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;


@Service
public class MemberService {

    MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> memberList() {
        return memberMapper.member_list();
    }

    // db에서 유효한 키 체크인지 확인하기 => 유효하지 않으면 리턴
    // 키가 유효하다면 전달받은 현재 service 폴더가 존재하는지 확인하기 폴더 유저id/서비스명
    // service 폴더가 존재한다면 해당폴더로 지정
    // service 폴더가 존재하지 않을경우 새로운 폴더 생성 후 지정
    // 파일명을 중복되지 않은 이름으로 변경 후 저장, db에 변경된 이름과 원본이름을 저장함
    // 중복되지 않은 이름을 사용자에게 돌려줌 (이미지 read용 url)
    public String getMemberByKey(String key, MultipartFile file, String service, String memberId) throws IOException {
        
        if (memberMapper.getMember(key) == null){
            return null;
        }else{
            System.out.println("키가 존재함");
            String baseDirectory = System.getProperty("user.dir") + "/images/";
            String memberDirectory = baseDirectory + memberId + "/";
            String serviceDirectory = memberDirectory + service + "/";
            String filename = file.getOriginalFilename();

            try {
                // memberId 디렉터리 확인 및 생성
                if (!Files.exists(FileSystems.getDefault().getPath(memberDirectory))) {
                    Files.createDirectories(FileSystems.getDefault().getPath(memberDirectory));
                    System.out.println("Member directory created: " + memberDirectory);
                }

                // service 디렉터리 확인 및 생성
                if (!Files.exists(FileSystems.getDefault().getPath(serviceDirectory))) {
                    Files.createDirectories(FileSystems.getDefault().getPath(serviceDirectory));
                    System.out.println("Service directory created: " + serviceDirectory);
                }

                String originalFilename = file.getOriginalFilename();
                int lastDotIndex = originalFilename.lastIndexOf(".");
                String extension = (lastDotIndex != -1) ? originalFilename.substring(lastDotIndex + 1) : "";

                String originalFilenameWithoutExtension = (lastDotIndex != -1) ? originalFilename.substring(0, lastDotIndex) : originalFilename;

                // UUID를 사용하여 중복 방지를 위한 파일명 생성
                String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

                // 파일 저장
                Path filePath = FileSystems.getDefault().getPath(serviceDirectory, uniqueFilename);
                file.transferTo(filePath);

                System.out.println("uniqueFilename:" + uniqueFilename);
                System.out.println("extension:" + extension);
                System.out.println("originalFilenameWithoutExtension:"+ originalFilenameWithoutExtension);

            } catch (Exception e) {
                e.printStackTrace();
                // 예외 처리를 추가하세요.
            }
            return "파일이 저장됨";
        }
        
    }




}
