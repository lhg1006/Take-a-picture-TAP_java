package com.example.demo.api.newFeed.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class NewFeedVO {

    private int id;
    private String title;
    private String postContent;
    private String postUserMail;
    private String postCreatedAt;
    private String imagePath = "";
    private String comments;
    private List<CommentsVO>commentsList;
    private int commentCount;

    public List<CommentsVO> getCommentsList(){
        if(!StringUtils.isEmpty(this.comments)){
            Gson gson = new Gson();
            List<CommentsVO> list = gson.fromJson(this.comments, new TypeToken<List<CommentsVO>>(){}.getType());
            return this.commentsList = list;
        }
        return commentsList = null;
    }
}
