package com.utimgapi.mapper;

import com.utimgapi.model.ImgName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgNameMapper {

    public List<ImgName> getImgList(String m_id);

    public int save_imgname(ImgName imgName);

    public ImgName getImg(String ori_name);

    public int delImg(String img_channame);

}
