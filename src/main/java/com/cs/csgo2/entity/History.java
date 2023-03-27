package com.cs.csgo2.entity;

import lombok.Data;

@Data
public class History {
    private String time;
    private double price;
    private Long goodsId;
    private String source;
}
