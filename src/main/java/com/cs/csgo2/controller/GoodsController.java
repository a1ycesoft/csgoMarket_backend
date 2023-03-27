package com.cs.csgo2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.csgo2.common.Res;
import com.cs.csgo2.entity.Goods;
import com.cs.csgo2.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/page")
    public Res<Page> page(int page, int pageSize, String name, String[] type, String[] rarity, String[] exterior, String[] quality, Long[] goodsId){

        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Goods> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.isNotEmpty(name),Goods::getFullName,name)
                .in(type!=null,Goods::getType,type)
                        .in(rarity!=null,Goods::getRarity,rarity)
                                .in(exterior!=null,Goods::getExterior,exterior)
                                        .in(quality!=null,Goods::getQuality,quality)
                                                .in(goodsId!=null,Goods::getId,goodsId);
        goodsService.page(pageInfo,queryWrapper);
        return Res.success(pageInfo);
    }
    @GetMapping("/getRelativeGoods")
    public Res<List<Goods>> getRelativeGoods(String name){
        QueryWrapper<Goods> wrapper = new QueryWrapper();
        wrapper.eq("short_name",name);
        List<Goods> list= goodsService.list(wrapper);
        return Res.success(list);
    }
}
