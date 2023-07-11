package com.verusins.diarial.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.verusins.diarial.common.vo.Response;
import com.verusins.diarial.system.entity.Diaries;
import com.verusins.diarial.system.service.IDiariesService;
import com.verusins.diarial.system.service.IUserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author verusins
 * @since 2023-06-15
 */
@RestController
@RequestMapping("/diaries")
@Slf4j
public class DiariesController {
    @Autowired
    private IDiariesService diariesService;

    @Autowired
    private IUserService userService;

    @PostMapping("/upload")
    public Response<Map<String, Object>> upload(@RequestBody Diaries diary, @RequestHeader String token) {
        if(StringUtils.isEmpty(diary.getTitle()) ||
                StringUtils.isEmpty(diary.getContent()) ||
                StringUtils.isEmpty(diary.getWeather())){
            return Response.fail(20004, "not enough information");
        }

        // get current time
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        log.info(formatter.format(date) +" : "+token);

        // detect valid
        int code = diariesService.checkDiary(diary);
        if(code == 1){
            return Response.fail(20003, "same title with former diary");
        }else if(code == 0){
            Map<String, Object> userInfo = userService.getUserInfo(token);
            diary.setUserId(Integer.parseInt(userInfo.get("user_id").toString()));
            diary.setDate(date);
            log.info(diary.toString());
            // add data to db;
            diariesService.save(diary);
            return Response.success("Added diary to Database");
        }
        return null;
    }

    @PutMapping("/delete/{diary_id}")
    public Boolean delete(@PathVariable int diary_id) {
        return diariesService.removeById(diary_id);
    }

    @GetMapping("/all")
    public Response<Object> GetAllDiaries() {
        return Response.success(diariesService.list());
    }

    @GetMapping("/info/{diary_id}")
    public Response<Diaries> GetOneDiary(@PathVariable int diary_id) {
        return Response.success(diariesService.getDiaryInfo(diary_id));
    }
}
