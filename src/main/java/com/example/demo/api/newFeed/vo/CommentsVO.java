package com.example.demo.api.newFeed.vo;

import lombok.Data;

@Data
public class CommentsVO {
    private int id;
    private String user_mail;
    private String content;
    private String created_at;

    public String getCreated_at(){
        return this.created_at = this.created_at.replace(".000000", "");
    }
}
