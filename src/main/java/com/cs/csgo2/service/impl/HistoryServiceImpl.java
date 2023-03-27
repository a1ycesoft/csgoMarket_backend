package com.cs.csgo2.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.csgo2.entity.History;
import com.cs.csgo2.mapper.HistoryMapper;
import com.cs.csgo2.service.HistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
}
