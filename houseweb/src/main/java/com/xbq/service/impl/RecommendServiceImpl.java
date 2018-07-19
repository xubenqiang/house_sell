package com.xbq.service.impl;

import com.xbq.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


}
