package com.example.demo.api.newFeed.vo;

import lombok.Data;

@Data
public class FollowsVO {
    private int followerAutoNo;
    private String userEmail;
    private String followerEmail;
    private String followTime;
    private String profileImg;
}
