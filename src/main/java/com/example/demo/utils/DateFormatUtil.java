package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatUtil {
    public String insDateKorea(String s){
        LocalDateTime insDate = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(insDate, now);
        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        String result;
        if (minutes < 60) {
            if (minutes == 0) {
                result = "지금";
            } else {
                result = minutes + "분 전";
            }
        } else if (hours < 24) {
            result = hours + "시간 전";
        } else if (days < 30) {
            result = days + "일 전";
        } else if (days < 60) {
            result = "1개월 전";
        } else if (days < 365) {
            result = days / 30 + "개월 전";
        } else if (days < 730) {
            result = "1년 전";
        } else {
            result = days / 365 + "년 전";
        }
        return result;
    }

}
