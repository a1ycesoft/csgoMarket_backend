package com.cs.csgo2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Users {
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;
    private String userName;
    private String password;
    private String avatar;
    private String phoneNumber;
    private String email;
}
