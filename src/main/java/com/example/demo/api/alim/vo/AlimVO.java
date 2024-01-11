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
            case 1: return "회원님의 게시물을 좋아합니다<span class='font-red fw-bold'>♥</span>";
            case 2: return "게시물에 댓글을 작성했습니다<span class='font-red fw-bold'>♥</span>";
            case 3: return "회원님을 팔로우 하였습니다.";
        }
        return "";
    }
}
