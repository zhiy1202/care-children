package com.demo.controller;

import cn.hutool.core.lang.ObjectId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.*;
import com.demo.service.IActivityImgService;
import com.demo.service.IActivityService;
import com.demo.service.IJoinActivityService;
import com.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/admin/activity")
public class ActivityAdminController {

    @Value("${zzy.local}")
    private String address;
    @Value("${zzy.filePath}")
    private String filePath;

    @Autowired
    private IActivityService activityService;
    @Autowired
    private IActivityImgService activityImgService;
    @Autowired
    private IJoinActivityService joinActivityService;
    @Autowired
    private IUserService userService;
    //添加活动
    @Transactional
    @PostMapping("/add")
    public ZZYResult<Boolean> add(Activity activity, MultipartFile[] files){
        activityService.save(activity);
        File file = new File(filePath);
        if (files.length != 0) {
            for (MultipartFile multipartFile : files) {
                String filename = multipartFile.getOriginalFilename();
                String[] split = filename.split("\\.");
                String pre = ObjectId.next();
                String fix = split[split.length - 1];
                String disk = filePath + pre + "." + fix;
                log.info("存储地址:" + disk);
                try {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    multipartFile.transferTo(new File(disk));
                } catch (IOException e) {
                    log.info("文件存储失败");
                }
                ActivityImg activityImg = new ActivityImg();
                activityImg.setActivityId(activity.getId());
                String add = address + pre + "." + fix;
                log.info(add);
                activityImg.setActivityImg(add);
                activityImgService.save(activityImg);
            }
        }
        return ZZYResult.getInstance(200,"存储成功",true);
    }

    //删除活动
    //删除关爱事件
    @Transactional
    @DeleteMapping("/del/{id}")
    public ZZYResult<Boolean> delLove(@PathVariable("id") Integer id){
        activityService.removeById(id);
        QueryWrapper<ActivityImg> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id",id);
        activityImgService.remove(wrapper);
        // TODO: 2023/2/24 删除文件

        return ZZYResult.getInstance(200,"删除成功",true);
    }

    //报名人员
    @GetMapping("/query")
    public ZZYResult query(){
        List<CommonEntity> entities = new ArrayList<>();
        List<JoinActivity> list = joinActivityService.list();
        for (JoinActivity joinActivity : list) {
            Activity byId = activityService.getById(joinActivity.getActivityId());
            User byId1 = userService.getById(joinActivity.getUserId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(joinActivity);
            entity.setP2(byId);
            entity.setP3(byId1);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"报名人员",entities);
    }
    //删除报名人员
    @DeleteMapping("/delJoin/{id}")
    public ZZYResult delId(@PathVariable("id") Integer id){
        boolean b = joinActivityService.removeById(id);
        return ZZYResult.getInstance(200,"删除参加人员",b);
    }
}
