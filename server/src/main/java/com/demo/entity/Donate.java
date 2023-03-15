package com.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Donate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer loveId;

    private String content;

    private String trackingNumber;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Integer donateType;
    /**
     * 0 未签收
     * 1 已签收
     */
    private Integer state;


}
