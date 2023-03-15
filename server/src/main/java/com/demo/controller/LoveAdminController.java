package com.demo.controller;

import cn.hutool.core.lang.ObjectId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.LoveImg;
import com.demo.entity.LoveThing;
import com.demo.service.ILoveImgService;
import com.demo.service.ILoveThingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zzy
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/admin/love")
public class LoveAdminController {

    @Autowired
    private ILoveThingService loveThingService;
    @Autowired
    private ILoveImgService loveImgService;

    @Value("${zzy.local}")
    private String address;
    @Value("${zzy.filePath}")
    private String filePath;

    //添加关爱事件
    @Transactional
    @PostMapping("/add")
    public ZZYResult<Boolean> addLove(MultipartFile[] files , LoveThing loveThing){
        loveThingService.save(loveThing);
        File file = new File(filePath);
        for (MultipartFile multipartFile : files) {
            String filename = multipartFile.getOriginalFilename();
            String[] split = filename.split("\\.");
            String fix = split[split.length-1];
            String pre = ObjectId.next();
            String disk = filePath + pre+"."+fix;
            log.info("存储地址:"+disk);
            try {
                if (!file.exists()){
                    file.mkdirs();
                }
                multipartFile.transferTo(new File(disk));
            } catch (IOException e) {
                log.info("文件存储失败");
            }
            LoveImg img = new LoveImg();
            img.setLoveId(loveThing.getId());
            String add =address + pre + "." + fix;
            log.info(add);
            img.setImgPath(add);
            loveImgService.save(img);
        }
        return ZZYResult.getInstance(200,"存储成功",true);
    }

    //删除关爱事件
    @Transactional
    @DeleteMapping("/del/{id}")
    public ZZYResult<Boolean> delLove(@PathVariable("id") Integer id){
        loveThingService.removeById(id);
        QueryWrapper<LoveImg> wrapper = new QueryWrapper<>();
        wrapper.eq("love_id",id);
        loveImgService.remove(wrapper);
        // TODO: 2023/2/24 删除文件

        return ZZYResult.getInstance(200,"删除成功",true);
    }

    //所有关爱事件
//    @GetMapping("/all")
//    public ZZYResult allLove(){
//
//    }

}
