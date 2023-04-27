package com.example.demo.api.member.vo;

import lombok.Data;

@Data
public class MemberPostsVO {
    private int id;
    private String content;
    private String createdAt;
    private String imagePath;
    private String userMail;
}
