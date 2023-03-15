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
@TableName("join_activity")
public class JoinActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer activityId;

    private Integer userId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
