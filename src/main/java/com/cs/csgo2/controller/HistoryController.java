package com.cs.csgo2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cs.csgo2.common.Res;
import com.cs.csgo2.entity.History;
import com.cs.csgo2.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @GetMapping
    public Res<List<History>> getHistoryById(Long goodsId, String source){
        QueryWrapper<History> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsId).eq("source",source);
        List<History> list=historyService.list(queryWrapper);
        return Res.success(list);
    }
}
