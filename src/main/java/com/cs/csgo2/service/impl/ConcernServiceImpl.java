package com.cs.csgo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.csgo2.entity.Concern;
import com.cs.csgo2.mapper.ConcernMapper;
import com.cs.csgo2.service.ConcernService;
import org.springframework.stereotype.Service;

@Service
public class ConcernServiceImpl extends ServiceImpl<ConcernMapper, Concern> implements ConcernService {
}
