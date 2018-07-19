package com.xbq.service.impl;

import com.xbq.biz.dao.CommunityMapper;
import com.xbq.biz.model.Community;
import com.xbq.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;


}
