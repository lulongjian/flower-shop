package com.example.mall.service;

import com.example.mall.dao.NoticeDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeDOMapper noticeDOMapper;
}
