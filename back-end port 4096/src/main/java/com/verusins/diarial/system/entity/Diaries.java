package com.verusins.diarial.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author verusins
 * @since 2023-06-15
 */
@TableName("d_diaries")
public class Diaries implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer diaryId;

    private Integer userId;

    private Date date;

    private String location;

    @NotBlank(message = "weather cannot be null!")
    private String weather;

    @NotBlank(message = "title cannot be null!")
    private String title;

    @NotBlank(message = "content cannot be null!")
    private String content;

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Diaries{" +
            "diaryId = " + diaryId +
            ", userId = " + userId +
            ", date = " + date +
            ", location = " + location +
            ", weather = " + weather +
            ", title = " + title +
            ", content = " + content +
        "}";
    }
}
