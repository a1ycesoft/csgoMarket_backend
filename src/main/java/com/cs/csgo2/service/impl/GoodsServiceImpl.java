package com.cs.csgo2.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.csgo2.entity.Goods;
import com.cs.csgo2.mapper.GoodsMapper;
import com.cs.csgo2.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
