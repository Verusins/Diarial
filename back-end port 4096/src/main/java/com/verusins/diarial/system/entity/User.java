package com.verusins.diarial.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author verusins
 * @since 2023-06-15
 */
@TableName("d_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String username;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId = " + userId +
            ", username = " + username +
            ", password = " + password +
        "}";
    }
}
