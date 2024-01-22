package com.utimgapi.mapper;

import com.utimgapi.model.Service_numbers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Service_numbersMapper {

    Service_numbers getService_number(@Param("m_id") String m_id, @Param("m_service") String m_service);


}
