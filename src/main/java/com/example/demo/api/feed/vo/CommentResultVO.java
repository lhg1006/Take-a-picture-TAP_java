package com.example.demo.api.feed.vo;

import lombok.Data;

@Data
public class CommentResultVO {
    private int autoNo;
    private int postNo;
    private String rMemberEmail;
    private String comment;
}
