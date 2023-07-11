package com.verusins.diarial.system.service;

import com.verusins.diarial.system.entity.Diaries;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author verusins
 * @since 2023-06-15
 */
public interface IDiariesService extends IService<Diaries> {

   int checkDiary(Diaries diary);

   Diaries getDiaryInfo(int diary_id);
}
