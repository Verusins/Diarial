package com.verusins.diarial.system.service;

import com.verusins.diarial.system.entity.User;
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
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    void logout(String token);

    Map<String, Object> getUserInfo(String token);
}
