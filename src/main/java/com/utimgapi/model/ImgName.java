package com.utimgapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class ImgName {

    String img_channame;
    String img_oriname;
    String img_ext;
    String img_uptime;
    String m_id, m_service;

    public ImgName(String img_channame, String img_oriname, String img_ext, String img_uptime, String m_id, String m_service){
        this.img_channame = img_channame;
        this.img_oriname = img_oriname;
        this.img_ext = img_ext;
        this.img_uptime = img_uptime;
        this.m_id = m_id;
        this.m_service = m_service;
    }


}
