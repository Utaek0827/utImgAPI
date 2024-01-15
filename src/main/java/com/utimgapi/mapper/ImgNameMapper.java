package com.utimgapi.mapper;

import com.utimgapi.model.ImgName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgNameMapper {

    int save_imgname(ImgName imgName);

    ImgName getImg(String ori_name);

}
