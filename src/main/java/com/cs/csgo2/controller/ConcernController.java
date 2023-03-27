package com.cs.csgo2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.csgo2.common.JwtUtils;
import com.cs.csgo2.common.Res;
import com.cs.csgo2.entity.Concern;
import com.cs.csgo2.entity.Goods;
import com.cs.csgo2.service.ConcernService;
import com.cs.csgo2.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/concern")
public class ConcernController {

    @Autowired
    private ConcernService concernService;


    // 通过Id获得关注的商品列表
    @GetMapping("/page")
    public Res<Page> page(HttpServletRequest request,int page, int pageSize){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Concern> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Concern::getUserId,userId).orderByDesc(Concern::getCreateTime);
        concernService.page(pageInfo,queryWrapper);
        return Res.success(pageInfo);
    }
    // 是否关注
    @GetMapping("/isConcerned")
    public Res<Boolean> is(HttpServletRequest request,Long goodsId){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        LambdaQueryWrapper<Concern> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Concern::getUserId,userId).eq(Concern::getGoodsId,goodsId);
        boolean result=concernService.count(queryWrapper)>0?true:false;
        return Res.success(result);
    }
    // 新增关注
    @PostMapping("/add")
    public Res<Concern> add(HttpServletRequest request,Long goodsId){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        Concern concern = new Concern();
        concern.setUserId(Long.parseLong(userId));
        concern.setGoodsId(goodsId);
        concernService.save(concern);
        return Res.success(concern);
    }
    // 取消关注
    @PostMapping("/delete")
    public Res<Concern> delete(HttpServletRequest request,String concernId,Long goodsId){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        if(concernId!=null)
        {
            Concern concern = concernService.getById(concernId);
            concernService.removeById(concernId);
            return Res.success(concern);
        }
        else
        {
            LambdaQueryWrapper<Concern> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Concern::getUserId,userId).eq(Concern::getGoodsId,goodsId);
            Concern concern=concernService.getOne(queryWrapper);
            concernService.remove(queryWrapper);
            return Res.success(concern);
        }
    }
}
