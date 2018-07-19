package com.xbq.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.model.House;

import java.util.List;

public interface HouseService {

    Page<House> getHouseList(Page<House> page, House house);



}
