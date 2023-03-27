package com.cs.csgo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.csgo2.entity.Users;
import com.cs.csgo2.mapper.UsersMapper;
import com.cs.csgo2.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
}
