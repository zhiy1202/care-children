package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.Activity;
import com.demo.entity.ActivityImg;
import com.demo.entity.CommonEntity;
import com.demo.entity.JoinActivity;
import com.demo.service.IActivityImgService;
import com.demo.service.IActivityService;
import com.demo.service.IJoinActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-02-24
 */
@RestController
@RequestMapping("/front/activity")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IJoinActivityService joinActivityService;

    @Autowired
    private IActivityImgService activityImgService;

    //我的报名
    @GetMapping("/my")
    public ZZYResult<List<CommonEntity>> myActivity(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        QueryWrapper<JoinActivity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",(Integer) userId);
        List<JoinActivity> list = joinActivityService.list(wrapper);
        for (JoinActivity joinActivity : list) {
            Integer activityId = joinActivity.getActivityId();
            Activity activity = activityService.getById(activityId);
            CommonEntity entity = new CommonEntity();
            entity.setP1(activity);
            entity.setP2(joinActivity);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"我的报名查询成功",entities);

    }
    //所有活动
    //亲子陪伴 1
    //学习互助 2
    //其他 3
    @GetMapping("/all/{type}")
    public ZZYResult<List<CommonEntity>> allActivity(@PathVariable("type") Integer type){
        List<CommonEntity> entities = new ArrayList<>();
        List<Activity> list = activityService.list(new QueryWrapper<Activity>().eq("activity_type", type));
        for (Activity activity : list) {
            QueryWrapper<ActivityImg> wrapper = new QueryWrapper<>();
            wrapper.eq("activity_id",activity.getId());
            List<ActivityImg> list1 = activityImgService.list(wrapper);
            CommonEntity entity = new CommonEntity();
            entity.setP1(activity);
            if (list1 != null && list1.size() != 0){
                entity.setP2(list1.get(0));
            }
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"所有活动",entities);
    }

    //报名
    @PostMapping("/apply/{id}")
    public ZZYResult<Boolean> apply(HttpServletRequest request, @PathVariable("id") Integer id){
        JoinActivity joinActivity = new JoinActivity();
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        joinActivity.setUserId((Integer) userId);
        joinActivity.setActivityId(id);
        boolean save = joinActivityService.save(joinActivity);
        return ZZYResult.getInstance(200,"报名成功",save);
    }
}
