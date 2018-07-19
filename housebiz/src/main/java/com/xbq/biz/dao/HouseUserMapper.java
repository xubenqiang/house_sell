package com.xbq.biz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xbq.biz.model.HouseUser;

import java.util.List;

public interface HouseUserMapper extends BaseMapper<HouseUser> {

    HouseUser selectByHouseId(Long houseId);
}
