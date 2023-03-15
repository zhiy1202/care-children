package com.demo.service.impl;

import com.demo.entity.Donate;
import com.demo.mapper.DonateMapper;
import com.demo.service.IDonateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzy
 * @since 2023-02-24
 */
@Service
public class DonateServiceImpl extends ServiceImpl<DonateMapper, Donate> implements IDonateService {

}
