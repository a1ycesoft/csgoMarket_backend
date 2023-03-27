package com.cs.csgo2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class Concern {
    @TableId(type = IdType.ASSIGN_ID)
    private String concernId;
    private Long userId;
    private Long goodsId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
