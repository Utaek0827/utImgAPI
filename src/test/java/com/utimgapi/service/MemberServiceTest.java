package com.utimgapi.service;

import com.utimgapi.mapper.MemberMapper;
import com.utimgapi.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.ArgumentMatchers.any;


import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MemberServiceTest {

    @Test
    void testGetMemberByKey() throws IOException {
        // Mock 객체 생성
        MemberMapper memberMapper = mock(MemberMapper.class);
        MultipartFile file = mock(MultipartFile.class);

        // MemberService에 Mock 객체 주입
        MemberService memberService = new MemberService(memberMapper);

        // Mocking: getMember 메서드 호출 시 가짜 Member 객체 반환하도록 설정
        when(memberMapper.getMember("testKey4")).thenReturn(new Member());

        // Mocking: transferTo 메서드 호출 시 어떠한 동작도 하지 않도록 설정

        // 테스트 수행
    }
}