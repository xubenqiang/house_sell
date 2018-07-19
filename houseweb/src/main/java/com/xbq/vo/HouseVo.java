package com.xbq.vo;


import lombok.Data;

@Data
public class HouseVo {

    private Long id;
    private String name;
    private String address;
    private Integer price;
    private String remarks;
    private Integer area;
    private Integer beds;
    private Integer baths;
    private Double rating;
    private Integer type;


}
