package com.utimgapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class ImgName {

    String img_channame;
    String img_oriname;
    String img_ext;

    public ImgName(String img_channame, String img_oriname, String img_ext){
        this.img_channame = img_channame;
        this.img_oriname = img_oriname;
        this.img_ext = img_ext;
    }


}
