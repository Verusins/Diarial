package com.verusins.diarial.system.controller;

import com.verusins.diarial.common.vo.Response;
import com.verusins.diarial.system.entity.User;
import com.verusins.diarial.system.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody User user){
        Map<String, Object> data = userService.login(user);
        if(data != null){
            return Response.success(data);
        }
        return Response.fail(20002, "Wrong username or password!");
    }

    @GetMapping("/info")
    public Response<?> getUserInfo(@Param("token") String token){
        Map<String,Object> data = userService.getUserInfo(token);
        if(data != null){
            return Response.success(data);
        }
        return Response.fail(20003,"failed to get user info");
    }


    @PostMapping("/logout")
    public Response<?> logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Response.success("Logged out successfully!");
    }
}
