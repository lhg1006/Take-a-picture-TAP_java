package com.example.demo.api.member.vo;

import lombok.Data;

@Data
public class MemberFollowsVO {
    private String followerEmail;
    private String followingEmail;
}
