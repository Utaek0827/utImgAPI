package com.utimgapi.service;

import com.utimgapi.mapper.ImgNameMapper;
import com.utimgapi.mapper.MemberMapper;
import com.utimgapi.mapper.Service_numbersMapper;
import com.utimgapi.model.ImgName;
import com.utimgapi.model.Member;
import com.utimgapi.model.Service_numbers;
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

    ImgNameMapper imgNameMapper;

    Service_numbersMapper service_numbersMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper, ImgNameMapper imgNameMapper, Service_numbersMapper service_numbersMapper) {

        this.memberMapper = memberMapper;
        this.imgNameMapper = imgNameMapper;
        this.service_numbersMapper = service_numbersMapper;
    }

    public String getImgUNameByoriName(String ori_name){
        ImgName in = imgNameMapper.getImg(ori_name);
        System.out.println("in.toString()"+in.toString());

        return null;
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
    
    // 올라와서 할거 => url 추출해서 보내준 url로 이미지 뷰어 확인
    public String getMemberByKey(String key, MultipartFile file, String m_service, String m_id) throws IOException {
        
        if (memberMapper.getMember(key) == null){
            return "키가 존재하지 않음";
        }else{
            System.out.println("키가 존재함");

            System.out.println("memberId:" + m_id + " service:" + m_service);
            
            // 키가 존재하는 경우 id와 service값으로 해당 서비스의 폴더번호 받음
            int snumber = service_numbersMapper.getService_number(m_id, m_service).getService_number(); 

            // 폴더번호로 경로 설정
            String baseDirectory = System.getProperty("user.dir") + "/images/";
            String memberDirectory = baseDirectory + snumber + "/";
            String msg = "파일 저장실패";

            try {
                // 폴더번호에 해당하는 폴더가 없을 경우 새로운 폴더생성
                if (!Files.exists(FileSystems.getDefault().getPath(memberDirectory))) {
                    Files.createDirectories(FileSystems.getDefault().getPath(memberDirectory));
                    System.out.println("Member directory created: " + memberDirectory);
                }

                // 파일저장시 중복방지를 위해 기존파일이름의 UUID값 + 확장자로 파일저장함
                // db에는 UUID값, 원본파일명, 확장자 저장됨
                String originalFilename = file.getOriginalFilename();
                int lastDotIndex = originalFilename.lastIndexOf(".");
                String extension = (lastDotIndex != -1) ? originalFilename.substring(lastDotIndex + 1) : "";

                String originalFilenameWithoutExtension = (lastDotIndex != -1) ? originalFilename.substring(0, lastDotIndex) : originalFilename;

                // UUID를 사용하여 중복 방지를 위한 파일명 생성
                String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

                // 파일 저장
                Path filePath = FileSystems.getDefault().getPath(memberDirectory, uniqueFilename);
                file.transferTo(filePath);
                String uptime = System.currentTimeMillis() + "";

                System.out.println("uptime:"+uptime);
                System.out.println("uniqueFilename:" + uniqueFilename);
                System.out.println("extension:" + extension);
                System.out.println("originalFilenameWithoutExtension:"+ originalFilenameWithoutExtension);

                int res = imgNameMapper.save_imgname(
                        new ImgName(uniqueFilename, originalFilenameWithoutExtension, extension, uptime, m_id, m_service));
                msg = originalFilenameWithoutExtension + "-uptime" + uptime;

                System.out.println("res:"+res);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return msg;
        }
        
    }


    public List<ImgName> getImgListBym_id(String mId) {

        return imgNameMapper.getImgList(mId);
    }
}
