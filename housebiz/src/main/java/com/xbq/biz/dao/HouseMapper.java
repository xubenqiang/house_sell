package com.xbq.biz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xbq.biz.model.House;

import java.util.List;

public interface HouseMapper extends BaseMapper<House> {

    /**
     * 根据 houseId 批量查询
     * @param houseIds
     * @return
     */
    List<House> selectBatchsByHouseIds(List<Long> houseIds);

}
