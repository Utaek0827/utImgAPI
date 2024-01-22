package com.utimgapi.mapper;

import com.utimgapi.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> member_list();

    Member getMember(String key);


}
