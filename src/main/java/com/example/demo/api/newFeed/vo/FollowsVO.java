package com.example.demo.api.newFeed.vo;

import lombok.Data;

@Data
public class FollowsVO {
    private String followerEmail;
    private String followingEmail;
    private String followDate;
}
