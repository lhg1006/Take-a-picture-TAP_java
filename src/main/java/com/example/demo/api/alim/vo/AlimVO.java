package com.example.demo.api.alim.vo;

import lombok.Data;

@Data
public class AlimVO {
    private int autoNo;
    private int sendMemNo;
    private int receiveMemNo;
    private int alimCode;    // 1: like, 2: comment, 3: follow
    private String insDate;
    private int postNo;
    private String sendName;  //상대방
    private String sendEmail; //상대방
    private String sendProfileImg;

    private String alimCodeKor;

    public String getAlimCodeKor(){
        switch (this.alimCode){
            case 1: return "회원님의 글을 좋아합니다";
            case 2: return "글에 댓글을 작성했습니다";
            case 3: return "회원님을 팔로우 하였습니다";
        }
        return "";
    }
}
