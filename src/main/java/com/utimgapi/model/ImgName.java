package com.utimgapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ImgName {

    String img_channame;
    String img_oriname;
    String img_ext;
    String img_uptime;
    String img_size;
    String m_id, m_service;


}
