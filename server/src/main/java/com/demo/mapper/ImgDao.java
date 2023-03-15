package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Img;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zzy
 * @desc
 */
@Mapper
public interface ImgDao extends BaseMapper<Img> {
    @Select("select * from img order by id desc limit 3")
    List<Img> query();
}
