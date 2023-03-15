package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.CommonEntity;
import com.demo.entity.Donate;
import com.demo.entity.DonateUse;
import com.demo.entity.LoveThing;
import com.demo.service.IDonateService;
import com.demo.service.IDonateUseService;
import com.demo.service.ILoveThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * type 0 表示管理员发布 1 表示捐赠
 */
@RestController
@RequestMapping("/front/donate")
public class DonateController {

    @Autowired
    private IDonateService donateService;

    @Autowired
    private IDonateUseService donateUseService;

    @Autowired
    private ILoveThingService loveThingService;

    //直接捐赠
    @PostMapping("/addForMy")
    public ZZYResult<Boolean> addForMy(Donate donate , HttpServletRequest request){

        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        donate.setUserId((Integer) userId);
        donate.setDonateType(1);
        donate.setState(0);
        donateService.save(donate);
        return ZZYResult.getInstance(200,"捐赠成功",true);

    }

    //孩子所需
    @GetMapping("/childNeed")
    public ZZYResult<List<Donate>> childNeed(){
        QueryWrapper<Donate> wrapper = new QueryWrapper<>();
        wrapper.eq("donate_type",0);
        List<Donate> list = donateService.list(wrapper);
        return ZZYResult.getInstance(200,"孩子所需",list);
    }

    //爱心关怀捐赠
//    @PostMapping("/addForLove")
//    public ZZYResult<Boolean> addForLove(){
//
//    }

    //我捐赠的 //捐赠物品使用详情 同用
    @GetMapping("/useForMe")
    public ZZYResult<List<CommonEntity>> allForMe(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        QueryWrapper<DonateUse> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",(Integer) userId);

        List<DonateUse> list = donateUseService.list(wrapper);
        for (DonateUse donateUse : list) {
            Donate donate = donateService.getById(donateUse.getDonateId());
            LoveThing loveThing = loveThingService.getById(donate.getLoveId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(donateUse);
            entity.setP2(donate);
            entity.setP3(loveThing);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"我的捐赠",entities);
    }

    //我的捐赠
    @GetMapping("/donateForMe")
    public ZZYResult<List<CommonEntity>> used(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        QueryWrapper<Donate> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id",(Integer) userId);
        wrapper1.eq("donate_type",1);
        List<Donate> list = donateService.list(wrapper1);
        for (Donate donate : list) {
            LoveThing loveThing = loveThingService.getById(donate.getLoveId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(donate);
            entity.setP2(loveThing);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"我的捐赠",entities);
    }
}
