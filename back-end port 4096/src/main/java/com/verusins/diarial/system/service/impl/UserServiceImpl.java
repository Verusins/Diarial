package com.verusins.diarial.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.verusins.diarial.system.entity.User;
import com.verusins.diarial.system.mapper.UserMapper;
import com.verusins.diarial.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User loginUser = this.getOne(wrapper);
        if(loginUser != null){
            Map<String, Object> data = new HashMap<>();
            String key = "user::" + UUID.randomUUID();
            data.put("token", key);    // 待优化，最终方案jwt
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override

    public Map<String, Object> getUserInfo(String token) {
        // look up token in redis
        Object obj = redisTemplate.opsForValue().get(token);
        // anti-ordering
        User user = JSON.parseObject(JSON.toJSONString(obj),User.class);
        if(user.getUserId() != null){
            Map<String, Object> data = new HashMap<>();
            data.put("user_id",user.getUserId());
            data.put("name",user.getUsername());
            return data;
        }
        return null;
    }
}
