package com.cs.csgo2.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {
    private Long id;
    private String fullName;
    private String shortName;
    private String iconSrc;
    private double sellMinPrice;
    private double buyMaxPrice;
    private double steamPrice;
    private double steamPriceCny;
    private String steamUrl;
    private String exterior;
    private String rarity;
    private String quality;
    private String type;
}
