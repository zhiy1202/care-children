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
@TableName("donate_use")
public class DonateUse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer donateId;

    private Integer userId;

    private String content;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
