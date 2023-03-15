package com.demo.controller;

import cn.hutool.core.lang.ObjectId;
import com.demo.common.ZZYResult;
import com.demo.entity.Img;
import com.demo.mapper.ImgDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/admin/img")
public class ImgAdminController {

    @Value("${zzy.local}")
    private String address;
    @Value("${zzy.filePath}")
    private String filePath;

    @Autowired
    private ImgDao imgDao;
    //更换轮播
    @PostMapping("/upload")
    public ZZYResult<Boolean> upload(MultipartFile [] files){
        File file = new File(filePath);
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
            String str = address + pre + "." + fix;
            log.info(str);
            Img img = new Img();
            img.setPath(str);
            imgDao.insert(img);
        }
        return ZZYResult.getInstance(200,"",true);
    }
    @GetMapping("/get")
    public ZZYResult<List<Img>> get(){
        List<Img> query = imgDao.query();
        return ZZYResult.getInstance(200,"查询成功",query);
    }

}
