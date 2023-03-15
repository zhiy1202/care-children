package com.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zzy
 * @since 2023-02-23
 */
@Getter
@Setter
@TableName("love_thing")
public class LoveThing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String intro;

    private String content;

    private String childNeed;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
