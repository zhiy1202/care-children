package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.CommonEntity;
import com.demo.entity.Donate;
import com.demo.entity.DonateUse;
import com.demo.service.IDonateService;
import com.demo.service.IDonateUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zzy
 * @desc 0 表示管理员发布 1 表示捐赠
 */
@RestController
@RequestMapping("/admin/donate")
public class DonateAdminController {

    @Autowired
    private IDonateService donateService;

    @Autowired
    private IDonateUseService donateUseService;

    //发布需求
    @PostMapping("/add")
    public ZZYResult<Boolean> add(Donate donate, HttpServletRequest request){
//        HttpSession session = request.getSession(false);
//        Object userId = session.getAttribute("userId");
        donate.setDonateType(0);
        donateService.save(donate);
        return ZZYResult.getInstance(200,"发布需求成功",true);
    }

    //待签收
    @GetMapping("/ready")
    public ZZYResult<List<Donate>> ready(){
        QueryWrapper<Donate> wrapper = new QueryWrapper<>();
        wrapper.eq("state",0);
        List<Donate> list = donateService.list(wrapper);
        return ZZYResult.getInstance(200,"待签收",list);
    }

    //签收
    @PutMapping("/update/{id}")
    public ZZYResult<Boolean> update(@PathVariable("id") Integer id){
        Donate byId = donateService.getById(id);
        byId.setState(1);
        donateService.updateById(byId);
        return ZZYResult.getInstance(200,"已签收",true);
    }

    //已签收
    @GetMapping("/complete")
    public ZZYResult<List<Donate>> complete(){
        QueryWrapper<Donate> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        List<Donate> list = donateService.list(wrapper);
        return ZZYResult.getInstance(200,"已签收",list);
    }

    //修改签收物品使用去向
    @PostMapping("/addUse/{id}")
    public ZZYResult<Boolean> addUse(HttpServletRequest request , @PathVariable("id") Integer id, String content){
        QueryWrapper<DonateUse> wrapper = new QueryWrapper<>();
        wrapper.eq("donate_id",id);
        DonateUse one = donateUseService.getOne(wrapper);
        if (one == null){
            Donate byId = donateService.getById(id);
            DonateUse donateUse = new DonateUse();
            donateUse.setDonateId(id);
            donateUse.setContent(content);
            donateUse.setUserId(byId.getUserId());
            donateUseService.save(donateUse);
        }else {
            one.setContent(content);
            donateUseService.updateById(one);
        }
        return ZZYResult.getInstance(200,"已签收",true);
    }
}
