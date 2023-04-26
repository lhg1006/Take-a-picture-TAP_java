package com.example.demo.api.newFeed.vo;

import lombok.Data;

@Data
public class LikeVO {
    private int id;
    private int postId;
    private String userMail;
    private String createdAt;
    private String profileImg;
    private String name;
}
