package com.verusins.diarial.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.verusins.diarial.system.entity.Diaries;
import com.verusins.diarial.system.mapper.DiariesMapper;
import com.verusins.diarial.system.service.IDiariesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author verusins
 * @since 2023-06-15
 */
@Service
@Slf4j
public class DiariesServiceImpl extends ServiceImpl<DiariesMapper, Diaries> implements IDiariesService {
    @Autowired
    private DiariesMapper mapper;

    @Override
    public int checkDiary(Diaries diary) {
        LambdaQueryWrapper<Diaries> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Diaries::getTitle, diary.getTitle());
        Diaries oneDiary = this.getOne(wrapper);
        if(oneDiary != null) return 1; // same title name;
        return 0;
    }

    @Override
    public Diaries getDiaryInfo(int diary_id) {
        QueryWrapper<Diaries> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("diary_id", diary_id);
        Diaries diary = mapper.selectOne(queryWrapper);
        log.info(diary.getTitle());
        return diary;
//        return mapper.selectById(diary_id);
    }
}
