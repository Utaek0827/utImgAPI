package com.utimgapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class Member {

    String m_key;
    String m_id;
    String m_service;
    Date m_start_date;
    Date m_end_date;


}
