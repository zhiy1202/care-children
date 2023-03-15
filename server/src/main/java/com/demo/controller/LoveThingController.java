package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.CommonEntity;
import com.demo.entity.LoveImg;
import com.demo.entity.LoveThing;
import com.demo.service.ILoveImgService;
import com.demo.service.ILoveThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/front/love")
public class LoveThingController {

    @Autowired
    private ILoveThingService loveThingService;
    @Autowired
    private ILoveImgService loveImgService;
    //查询所有关爱事件
    @GetMapping("/all")
    public ZZYResult<List<CommonEntity>> allLoveThing(){
        List<CommonEntity> commonEntities = new ArrayList<>();
        List<LoveThing> list = loveThingService.list();
        for (LoveThing loveThing : list) {
            QueryWrapper<LoveImg> wrapper = new QueryWrapper<>();
            wrapper.eq("love_id",loveThing.getId());
            List<LoveImg> list1 = loveImgService.list(wrapper);
            CommonEntity entity = new CommonEntity();
            entity.setP1(loveThing);
            entity.setP2(list1.get(0));
            commonEntities.add(entity);
        }
        return ZZYResult.getInstance(200,"查询所有关爱事件",commonEntities);
    }

    //事件详情
    @GetMapping("/detail/{id}")
    public ZZYResult<CommonEntity> loveDetail(@PathVariable("id") Integer id){
        LoveThing loveThing = loveThingService.getById(id);
        QueryWrapper<LoveImg> wrapper = new QueryWrapper<>();
        wrapper.eq("love_id",loveThing.getId());
        List<LoveImg> list1 = loveImgService.list(wrapper);
        CommonEntity entity = new CommonEntity();
        entity.setP1(loveThing);
        entity.setP2(list1);
        return ZZYResult.getInstance(200,"查询所有关爱事件",entity);
    }


}
